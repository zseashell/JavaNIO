package buffer.copy_buffer;

import java.nio.CharBuffer;

public class BufferCopy {
	
	public static void main(String[] args) {
		CharBuffer buffer = CharBuffer.allocate(100);
		buffer.put("HiHelloWorld!");
		
		buffer.position(7).limit(12).mark().position(2);
		
		// duplicate
		CharBuffer dupBuffer = buffer.duplicate();
		buffer.clear();
		
		dupBuffer.put(2, 'h');
		while(dupBuffer.hasRemaining()) {
			System.out.print(dupBuffer.get());
		}
		System.out.print("");
	}

}
