package com.wbdp.util;

public class ByteUtil {
	public static final int unsigned(byte b) {
		return b & 0xFF;
	}

	public static final int unsigned(short s) {
		return s & 0xFFFF;
	}

	public static final long unsigned(int i) {
		return i & 0xFFFFFFFFl; // must be long
	}

	public static final byte bcdToBin(byte val) {

		return (byte) ((val >> 4) * 10 + (val & 0x0f));

	}

	public static final byte binToBcd(byte val) {
		return (byte) (((val / 10) << 4) + (val % 10));
	}

	public static final long bcdToLong(byte[] buf, int offset, int len) {
		long val = 0l;
		for (int i = offset; i < offset + len; i++) {
			val *= 100;
			val += bcdToBin(buf[i]);
		}
		return val;
	}

	public static final long bcdToLong(byte[] buf) {
		return bcdToLong(buf, 0, buf.length);
	}

	public static final int longToBcd(byte[] buf, long val) {
		int idx = 0;
		while (val != 0) {
			buf[idx++] = binToBcd((byte) (val % 100));
			val /= 100;
		}
		ArrayUtil.reverse(buf, 0, idx);
		return idx;
	}

	public static final byte[] longToBcd(long val) {
		byte[] max = new byte[19]; // max long = 9223372036854775807
		int len = longToBcd(max, val);
		byte[] buf = new byte[len];
		System.arraycopy(max, 0, buf, 0, len);

		return buf;
	}

	public static final short ctos(byte[] buf, int offset) {
		return (short) ((buf[offset] & 0xFF) + (buf[offset + 1] << 8));
	}

	public static final short ctos(byte[] buf) {
		return ctos(buf, 0);
	}

	public static final void stoc(byte[] buf, int offset, short val) {
		buf[offset] = (byte) val;
		buf[offset + 1] = (byte) (val >> 8);
	}

	public static final byte[] stoc(short val) {
		byte[] buf = new byte[2];
		stoc(buf, 0, val);
		return buf;
	}

	public static final int ctol(byte[] buf, int offset) {
		return (buf[offset] & 0xFF) + (buf[offset + 1] << 8)
				+ (buf[offset + 2] << 16) + (buf[offset + 3] << 24);
	}

	public static final int ctol(byte[] buf) {
		return ctol(buf, 0);
	}

	public static final void ltoc(byte[] buf, int offset, int val) {
		buf[offset] = (byte) val;
		buf[offset + 1] = (byte) (val >> 8);
		buf[offset + 2] = (byte) (val >> 16);
		buf[offset + 3] = (byte) (val >> 24);
	}

	public static final byte[] ltoc(int val) {
		byte[] buf = new byte[4];
		ltoc(buf, 0, val);
		return buf;
	}

	public static final short bctos(byte[] buf, int offset) {
		return (short) (buf[offset] << 8 + (buf[offset]));
	}

	public static final short bctos(byte[] buf) {
		return bctos(buf, 0);
	}

	public static final void bstoc(byte[] buf, int offset, short val) {
		buf[offset] = (byte) (val >> 8);
		buf[offset + 1] = (byte) val;
	}

	public static final byte[] bstoc(short val) {
		byte[] buf = new byte[2];
		bstoc(buf, 0, val);
		return buf;
	}

	public static final int bctol(byte[] buf, int offset) {
		return (buf[offset] << 24) + (buf[offset + 1] << 16)
				+ (buf[offset + 2] << 8) + buf[offset + 3];
	}

	public static final int bctol(byte[] buf) {
		return ctol(buf, 0);
	}

	public static final void bltoc(byte[] buf, int offset, int val) {
		buf[offset] = (byte) (val >> 24);
		buf[offset + 1] = (byte) (val >> 16);
		buf[offset + 2] = (byte) (val >> 8);
		buf[offset + 3] = (byte) val;
	}

	public static final byte[] bltoc(int val) {
		byte[] buf = new byte[4];
		bltoc(buf, 0, val);
		return buf;
	}

	public static final char atoc(byte c) {
		return String.format("%X", c).charAt(0);
	}

	public static final byte ctoa(char c) {
		return (byte) Integer.parseInt(Character.toString(c), 16);
	}

	public static final char[] atocc(byte b) {
		return String.format("%02X", b).toCharArray();
	}

	public static final void atocc(char[] array, int offset, byte b) {
		System.arraycopy(atocc(b), 0, array, offset, 2);
	}

	public static final byte cctoa(char[] cc) {
		return cctoa(cc, 0);
	}

	public static final byte cctoa(char[] cc, int offset) {
		return (byte) Integer.parseInt(new String(cc, offset, 2), 16);
	}

	public static final char[] atos(byte[] buf, final String format) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < buf.length; i++) {
			sb.append(String.format(format, buf[i]));
		}
		return sb.toString().toCharArray();
	}

	public static final byte[] stoa(char[] s) {
		byte[] buf = new byte[s.length / 2];
		for (int i = 0, j = 0; i < buf.length; i++, j = j + 2) {
			buf[i] = cctoa(new char[] { s[j], s[j + 1] });
		}
		return buf;
	}

	public static final int ffs(int n) {
		for (int i = 0; i < 32; i++) {
			if ((n & 0x01) != 0) {
				return i;
			}
			n = n >> 1;
		}
		return -1;
	}

	public static final byte[] Int2Byte(int a) {
		byte b[] = new byte[4];
		b[0] = (byte) a;
		b[1] = (byte) (a >> 8);
		b[2] = (byte) (a >> 16);
		b[3] = (byte) (a >> 24);
		return b;
	}

	public static void main(String args[]) {
		Int2Byte(192);
		String str = "192";
		String str0 = Integer.toHexString(520);

	}
}
