package buffer.basic;

import java.nio.CharBuffer;

public class BufferBulk {

	private static void bulkGetWithBigArray() {
		String string = "Welcome to the real world, it sucks but you're gonna love it!";

		CharBuffer buffer = CharBuffer.allocate(100);
		for (int i = 0; i < string.length(); i++) {
			buffer.put(string.charAt(i));
		}

		buffer.flip();

		// bulk get with big array
		char[] bigArray = new char[1000];
		// get the remaining count of chars in buffer
		int length = buffer.remaining();
		buffer.get(bigArray, 0, length);
		for (int i = 0; i < bigArray.length; i++) {
			System.out.print(bigArray[i]);
		}
		System.out.println("");

		buffer.clear();
	}

	private static void bulkGetWithSmallArray() {
		String string = "Welcome to the real world, it sucks but you're gonna love it!";

		CharBuffer buffer = CharBuffer.allocate(100);
		for (int i = 0; i < string.length(); i++) {
			buffer.put(string.charAt(i));
		}

		buffer.flip();

		// bulk get with small array
		while (buffer.hasRemaining()) {
			char[] smallArray = new char[5];
			int length = Math.min(buffer.remaining(), smallArray.length);

			buffer.get(smallArray, 0, length);
			for (int i = 0; i < smallArray.length; i++) {
				System.out.print(smallArray[i]);
			}
		}
		System.out.println("");

		buffer.clear();
	}

	private static void bulkPut() {
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

	private static void stringPut() {
		String string = "Welcome to the real world, it sucks but you're gonna love it!";

		CharBuffer buffer = CharBuffer.allocate(100);
		// buffer.put(string, 0, string.length())
		buffer.put(string);

		buffer.flip();

		while (buffer.hasRemaining()) {
			System.out.print(buffer.get());
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		bulkGetWithBigArray();
		bulkGetWithSmallArray();

		bulkPut();

		stringPut();
	}

}
