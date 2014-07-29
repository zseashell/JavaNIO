package selector;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Client {

	private static final int PORT_NUMBER = 9991;

	private static final String HOST = "localhost";

	public void go() throws Exception {
		SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(
				HOST, PORT_NUMBER));
		socketChannel.configureBlocking(false);

		Selector selector = Selector.open();
		socketChannel.register(selector, SelectionKey.OP_CONNECT);

		System.out.println("Client connecting Server...");

		while (true) {
			selector.select();
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			while (it.hasNext()) {
				SelectionKey key = it.next();
				it.remove();
				if (key.isConnectable()) {
					SocketChannel channel = (SocketChannel) key.channel();

					// check if it's under connecting
					if (channel.isConnectionPending()) {
						channel.finishConnect();
					}

					channel.configureBlocking(false);

					// send message to server
					channel.write(ByteBuffer.wrap(new String("hello")
							.getBytes()));

					// register for read from server
					channel.register(selector, SelectionKey.OP_READ);
				} else if (key.isReadable()) {
					SocketChannel sChannel = (SocketChannel) key.channel();

					ByteBuffer buffer = ByteBuffer.allocate(10);
					sChannel.read(buffer);
					byte[] data = buffer.array();
					String message = new String(data);

					System.out.println("receive message from server: "
							+ message);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		new Client().go();
	}
}
