package buffer.basic;

import java.nio.CharBuffer;

/**
 * Buffer fill/drain. Fill and drain a buffer one element at a time
 * 
 * @author seashell
 * 
 */
public class BufferFillDrain {

	private static int index = 0;

	private static String[] strings = { "Hello world", "A random string value",
			"Help me! Help...", };

	private static boolean fillBuffer(CharBuffer buffer) {
		if (index >= strings.length) {
			return false;
		}
		String string = strings[index++];
		for (int i = 0; i < string.length(); i++) {
			buffer.put(string.charAt(i));
		}
		return true;
	}

	private static void drainBuffer(CharBuffer buffer) {
		while (buffer.hasRemaining()) {
			System.out.print(buffer.get());
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		CharBuffer buffer = CharBuffer.allocate(100);
		while (fillBuffer(buffer)) {
			buffer.flip();
			drainBuffer(buffer);
			buffer.clear();
		}
	}

}
