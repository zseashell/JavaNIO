package buffer.create_buffer;

import java.nio.CharBuffer;

public class BufferByAllocate {
	
	public static void main(String[] args) {
		String string = "Welcome to the real world, it sucks but you're gonna love it!";
		char[] chars = string.toCharArray();
		CharBuffer buffer = CharBuffer.allocate(100);
		buffer.put(chars);

		buffer.flip();

		while (buffer.hasRemaining()) {
			System.out.print(buffer.get());
		}
		System.out.println("");
	}

}
