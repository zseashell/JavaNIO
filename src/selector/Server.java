package selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 
 * SimpleE echo-back server which listens for incoming stream connections and
 * echoes back whatever it reads. A single Seletor object is used to listen to
 * the server socket (to accept new connections) and all the active socket
 * channels.
 * 
 * @author seashell
 * 
 */
public class Server {

	private static final int PORT_NUMBER = 9991;

	private ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

	public void go() throws Exception {
		System.out.println("server listening on port " + PORT_NUMBER + "...");

		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);

		ServerSocket serverSocket = serverSocketChannel.socket();
		serverSocket.bind(new InetSocketAddress(PORT_NUMBER));

		Selector selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

		while (true) {
			// blocking, select a set of keys whose channel is ready for I/O
			int n = selector.select();
			if (n == 0) {
				continue; // nothing to do
			}

			Iterator<SelectionKey> iterator = selector.selectedKeys()
					.iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();

				// Is a new connection coming in?
				if (key.isAcceptable()) {
					ServerSocketChannel server = (ServerSocketChannel) key
							.channel();
					SocketChannel channel = server.accept();

					// register channel for read
					registerChannel(selector, channel, SelectionKey.OP_READ);
					
					// say hello to client
					buffer.clear();
					buffer.put("Hi from server".getBytes());
					buffer.flip();
					channel.write(buffer);
				}
				if (key.isReadable()) {
					readDataFromSocket(key);
				}
				// remove the key for it has been handled
				iterator.remove();
			}
		}
	}

	private void registerChannel(Selector selector, SelectableChannel channel,
			int ops) throws Exception {
		if (channel == null) {
			return;
		}
		// set the new channel non-blocking
		channel.configureBlocking(false);
		// register it with the selector
		channel.register(selector, ops);
	}

	private void readDataFromSocket(SelectionKey key) throws Exception {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		int count;

		buffer.clear(); // empty buffer
		while ((count = socketChannel.read(buffer)) > 0) {
			buffer.flip(); // make buffer readable

			while (buffer.hasRemaining()) {
				socketChannel.write(buffer);
			}

			buffer.clear();
		}

		if (count < 0) {
			socketChannel.close();
		}
	}

	public static void main(String[] args) throws Exception {
		// start server and listen for incoming client
		new Server().go();
	}

}
