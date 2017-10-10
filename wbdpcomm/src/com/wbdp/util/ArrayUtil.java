package com.wbdp.util;

public class ArrayUtil {
	public static final int sum(final byte[] buf, int offset, int len) {
		int sum = 0;
		while (len-- > 0) {
			sum += buf[offset++];
		}
		return sum;
	}

	public static final int sum(final byte[] buf) {
		return sum(buf, 0, buf.length);
	}

	public static final int repeat(byte[] buf, int offset, byte b) {
		int count = 0;
		while (offset < buf.length) {
			if (buf[offset] == b) {
				count++;
				offset++;
			} else {
				break;
			}
		}
		return count;
	}

	public static final void reverse(byte[] buf) {
		reverse(buf, 0, buf.length);
	}

	public static final void reverse(byte[] buf, int offset, int len) {
		byte b = 0x00;
		int start = offset;
		int end = start + len - 1;

		while (start < end) {
			b = buf[start];
			buf[start] = buf[end];
			buf[end] = b;
			start++;
			end--;
		}
	}

	public static final int find(final byte[] buf, int begin, int length, byte b) {
		int end = Math.min(buf.length, begin + length);

		for (int i = begin; i < end; i++) {
			if (buf[i] == b) {
				return i;
			}
		}
		return -1;
	}

	public static final String sprintf(final byte[] buf, String format) {
		return sprintf(buf, 0, buf.length, format);
	}

	public static final String sprintf(final byte[] buf, int offset, int len,
			String format) {
		StringBuilder sb = new StringBuilder();
		while (len-- > 0) {
			sb.append(String.format(format, buf[offset++]));
		}
		return sb.toString();
	}
}
