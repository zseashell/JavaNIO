package buffer.copy_buffer;

import java.nio.CharBuffer;

public class BufferSlice {

	public static void main(String[] args) {
		CharBuffer buffer = CharBuffer.allocate(100);
		buffer.put("xHellox");

		buffer.position(1).limit(6);

		CharBuffer slicedBuffer = buffer.slice();

		while (slicedBuffer.hasRemaining()) {
			System.out.print(slicedBuffer.get());
		}

		System.out.println("");

	}

}
