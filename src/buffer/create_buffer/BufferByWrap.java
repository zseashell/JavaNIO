package buffer.create_buffer;

import java.nio.CharBuffer;

public class BufferByWrap {

	private static void wrapArray() {
		char[] array = new char[100];
		CharBuffer charBuffer = CharBuffer.wrap(array);
		charBuffer.put("hello");
		array[0] = 'H';

		charBuffer.flip();

		System.out.println("char buffer has array? " + charBuffer.hasArray());
		System.out
				.println("char buffer offset is: " + charBuffer.arrayOffset());
		array = charBuffer.array();
		System.out.println(array);
	}
	
	private static void wrapReadOnly() {
		// CharSequence: StringBuffer, CharBuffer, String
		CharBuffer charBuffer = CharBuffer.wrap("Hello");
		System.out.println("ReadOnly? " + charBuffer.isReadOnly());
		
	}
	
 	public static void main(String[] args) {
		wrapArray();
		wrapReadOnly();
	}

}
