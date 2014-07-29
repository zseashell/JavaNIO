package channel.basic;

import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ChannelBasic {

	public static void main(String[] args) throws Exception {
		SocketChannel sc = SocketChannel.open();
		sc.connect(new InetSocketAddress("somehost", 10000));

		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(10000));

		DatagramChannel dc = DatagramChannel.open();

		RandomAccessFile raf = new RandomAccessFile("somefile", "r");
		FileChannel fc = raf.getChannel();
	}

}
