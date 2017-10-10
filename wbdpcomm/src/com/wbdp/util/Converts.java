package com.wbdp.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;



/**
 * @author KunBao Li
 * @version: 1.0 $, $Date: 2009/03/17 $
 */
public class Converts {
	private static final int DEF_DIV_SCALE = 10;

	/**
	 * 把16进制字符串转换成字节数组
	 * 
	 * @param hex
	 * @return
	 */
	public static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	/**
	 * 把字节数组转换成16进制字符串
	 * 
	 * @param bArray
	 * @return
	 */
	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 把字节数组转换为对象
	 * 
	 * @param bytes
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static final Object bytesToObject(byte[] bytes) throws IOException,
			ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		ObjectInputStream oi = new ObjectInputStream(in);
		Object o = oi.readObject();
		oi.close();
		return o;
	}

	/**
	 * 把可序列化对象转换成字节数组
	 * 
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public static final byte[] objectToBytes(Serializable s) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream ot = new ObjectOutputStream(out);
		ot.writeObject(s);
		ot.flush();
		ot.close();
		return out.toByteArray();
	}

	public static final String objectToHexString(Serializable s)
			throws IOException {
		return bytesToHexString(objectToBytes(s));
	}

	public static final Object hexStringToObject(String hex)
			throws IOException, ClassNotFoundException {
		return bytesToObject(hexStringToByte(hex));
	}

	/**
	 * @函数功能: BCD码转为10进制串(阿拉伯数据)
	 * @输入参数: BCD码
	 * 
	 * @输出结果: 10进制串
	 * 
	 */
	public static String bcd2Str(byte[] bytes) {
		StringBuffer temp = new StringBuffer(bytes.length * 2);

		for (int i = 0; i < bytes.length; i++) {
			temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
			temp.append((byte) (bytes[i] & 0x0f));
		}
		return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp
				.toString().substring(1) : temp.toString();
	}

	/**
	 * byte to String
	 * 
	 * @param bytes
	 * @return
	 */
	public static String byte2StrForDate(byte[] bytes) {
		StringBuffer tmp = new StringBuffer(bytes.length * 2);

		for (int i = 0; i < bytes.length; i++) {
			int temp = bytes[i];

			if (temp < 10) {
				tmp.append("0" + temp);
			} else {
				tmp.append(String.valueOf(temp));
			}
		}
		return tmp.toString();
	}

	/**
	 * @函数功能: 10进制串转为BCD码
	 * 
	 * @输入参数: 10进制串
	 * 
	 * @输出结果: BCD码
	 * 
	 */
	public static byte[] str2Bcd(String asc) {
		int len = asc.length();
		int mod = len % 2;

		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}

		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}

		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;

		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}

			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}

			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}

	/**
	 * @函数功能: BCD码转ASC码
	 * 
	 * @输入参数: BCD串
	 * 
	 * @输出结果: ASC码
	 * 
	 */
	public static String BCD2ASC(byte[] bytes) {
		StringBuffer temp = new StringBuffer(bytes.length * 2);

		for (int i = 0; i < bytes.length; i++) {
			int h = ((bytes[i] & 0xf0) >>> 4);
			int l = (bytes[i] & 0x0f);
			temp.append(BToA(h)).append(BToA(l));
		}
		return temp.toString();
	}

	public static char BToA(int h) {
		char c;
		c = (char) h;
		return c;
	}

	/**
	 * MD5加密字符串，返回加密后的16进制字符串
	 * 
	 * 
	 * @param origin
	 * @return
	 */
	public static String MD5EncodeToHex(String origin) {
		return bytesToHexString(MD5Encode(origin));
	}

	/**
	 * MD5加密字符串，返回加密后的字节数组
	 * 
	 * @param origin
	 * @return
	 */
	public static byte[] MD5Encode(String origin) {
		return MD5Encode(origin.getBytes());
	}

	/**
	 * MD5加密字节数组，返回加密后的字节数组
	 * 
	 * @param bytes
	 * @return
	 */
	public static byte[] MD5Encode(byte[] bytes) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			return md.digest(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new byte[0];
		}

	}

	/**
	 * byte[] convert to String
	 * 
	 * @param bArray
	 * @return
	 */
	public static String byteToStr(byte[] bArray) {
		String str = "";
		char[] c = new char[bArray.length];

		for (int i = 0; i < bArray.length; i++) {
			int tmp = bArray[i];
			c[i] = (char) tmp;
			str += c[i];
		}

		return str;
	}

	/**
	 * String bytes convert to String
	 * 
	 * @param bArray
	 * @return
	 */
	public static String byteToStr(String bytes) {
		String str = "";
		int len = bytes.length() / 2;
		char[] c = new char[len];

		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			int tmp = toByte(bytes.charAt(pos)) * 16
					+ toByte(bytes.charAt(pos + 1));

			c[i] = (char) tmp;
			str += c[i];
		}

		return str;
	}

	/**
	 * check Sum 校验和
	 * 
	 * @param cs
	 * @return
	 */
	public static char checkSum(char[] cs) {
		char tmp = 0x00;
		for (int i = 0; i < cs.length; i++) {
			tmp += cs[i];
		}

		int t = (int) tmp & 0xff;
		char c = (char) t;

		return c;
	}

	/**
	 * check Sum 校验和
	 * 
	 * @param cs
	 * @return
	 */
	public static char[] checkSum(char[] cs, int type) {
		int tmp = 0x00;
		for (int i = 0; i < cs.length; i++) {
			tmp += cs[i];
		}

		char[] ch = new char[2];

		int t = (int) tmp & 0xff;
		ch[0] = (char) t;

		tmp = (tmp - t) / 0xff;
		t = (int) tmp & 0xff;
		ch[1] = (char) t;

		return ch;
	}

	/**
	 * Convert char[] to string.
	 * 
	 * @param cs -char[]
	 * @return
	 */
	public static String charArrayToHexString(char[] cs) {
		String ret = "";

		if (cs != null) {
			for (int i = 0; i < cs.length; i++) {
				int tmp = (int) cs[i];
				int first = tmp / 16;
				int second = tmp % 16;

				ret += intToString(first) + intToString(second);
			}
		}

		return ret;
	}

	/**
	 * Convert char[] to string. *
	 * 
	 * @param cs
	 *            -char[]
	 * @return
	 */
	public static String charToHexString(char cs) {
		String ret = "";

		int tmp = (int) cs;
		int first = tmp / 16;
		int second = tmp % 16;

		ret += intToString(first) + intToString(second);

		return ret;
	}

	/**
	 * Convert int to hex string.
	 * 
	 * @param index
	 * @return
	 */
	public static String intToString(int index) {
		String ret = "";
		String tmp = "0123456789ABCDEF";
		ret = String.valueOf(tmp.charAt(index));
		return ret;
	}

	/**
	 * Convert hex string to char[].
	 * 
	 * @param param
	 * @return
	 */
	public static char[] stringToCharArray(String param) {
		char[] cs = null;
		int len = param.length();
		if (len > 0 && len % 2 == 0) {
			char[] temp = param.toUpperCase().toCharArray();
			int[] tempInt = new int[len];

			for (int i = 0; i < len; i++) {
				int tmp = (int) temp[i];
				if (tmp >= 65) {
					tmp = tmp - 65 + 10;
				} else {
					tmp = tmp - 48;
				}

				tempInt[i] = tmp;
			}
			cs = new char[len / 2];
			int j = 0;
			for (int i = 0; i < len; i += 2) {
				int tmp = tempInt[i] * 16 + tempInt[i + 1];
				tmp = tmp & 0xff;
				cs[j] = (char) tmp;
				j++;
			}
		} else {
			return null;
		}

		return cs;
	}

	/**
	 * Convert hex String[] to char[].
	 * 
	 * @param param
	 * @return
	 */
	public static char[] stringToCharArray(String[] params) {
		char[] cs = null;
		String param = "";

		for (int i = 0; i < params.length; i++) {
			param += params[i];
		}
		cs = stringToCharArray(param);

		return cs;
	}

	/**
	 * Convert BCD String[] to char[].
	 * 
	 * @param param
	 * @return
	 */
	public static char[] bcdToCharArray(String param) {
		char[] cs = null;
		int len = param.length() / 2;

		try {
			cs = new char[len];

			for (int i = 0; i < len; i++) {
				String temp = param.substring(i * 2, i * 2 + 2);
				int tmp = new Integer(temp).intValue();
				cs[i] = (char) tmp;
			}

		} catch (Exception e) {
			cs = null;
		}

		return cs;
	}

	/**
	 * get value of LC (数据长度)
	 * 
	 * @param param
	 * @return
	 */
	public static char[] getlengthCharArray(int len) {
		char[] cs = new char[2];
		int high = (len / 256) & 0xff;
		int low = (len - high * 256) & 0xff;

		cs[0] = (char) low;
		cs[1] = (char) high;

		return cs;
	}

	/**
	 * get value of LC (数据长度)
	 * 
	 * @param param
	 * @return
	 */
	public static int getlengthParam(char[] LC) {
		int len = 0;

		int low = LC[0];
		int high = LC[1];

		len = high * 256 + low;

		return len;
	}

	/**
	 * convert hexString To Int
	 * 
	 * @param hex
	 * @return
	 */
	public static int hexStringToInt(String hex) {
		int ret = 0;
		int len = hex.length();
		if (len > 0 && len % 2 == 0) {
			char[] temp = hex.toUpperCase().toCharArray();
			int[] tempInt = new int[len];

			for (int i = 0; i < len; i++) {
				int tmp = (int) temp[i];
				if (tmp >= 65) {
					tmp = tmp - 65 + 10;
				} else {
					tmp = tmp - 48;
				}

				tempInt[i] = tmp;
			}

			for (int i = 0; i < len; i += 2) {
				int tmp = tempInt[i] * 16 + tempInt[i + 1];
				tmp = tmp & 0xff;

				ret = ret * 256 + tmp;
			}
		} else {
			return 0;
		}

		return ret;
	}

	/**
	 * convert hexString To long
	 * 
	 * @param hex
	 * @return
	 */
	public static long hexStringTolong(String hex) {
		long ret = 0;
		int len = hex.length();
		if (len > 0 && len % 2 == 0) {
			char[] temp = hex.toUpperCase().toCharArray();
			int[] tempInt = new int[len];

			for (int i = 0; i < len; i++) {
				int tmp = (int) temp[i];
				if (tmp >= 65) {
					tmp = tmp - 65 + 10;
				} else {
					tmp = tmp - 48;
				}

				tempInt[i] = tmp;
			}

			for (int i = 0; i < len; i += 2) {
				int tmp = tempInt[i] * 16 + tempInt[i + 1];
				tmp = tmp & 0xff;

				ret = ret * 256 + tmp;
			}
		} else {
			return 0;
		}

		return ret;
	}

	/**
	 * convert Hexstr to Str
	 * 
	 * @param s
	 * @return
	 */
	public static String Hexstr2Str(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
						i * 2, i * 2 + 2), 16));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	/**
	 * convert string to Hex string
	 * 
	 * @param s
	 * @return
	 */
	public static String str2Hexstring(String s) {
		String ret = "";

		try {
			if (s != null) {
				int len = s.length();

				if (len % 2 != 0) {
					return ret;
				}

				char[] cs = new char[len / 2];

				for (int i = 0; i < len / 2; i++) {
					int tmp = Integer.valueOf(s.substring(2 * i, 2 * i + 2))
							.intValue();					
					cs[i] = (char) tmp;
				}

				ret = Converts.charArrayToHexString(cs);
			}
		} catch (Exception e) {
			ret = "";
		}

		return ret;
	}
	
	/**
	 * add33
	 * @param hex
	 * @return
	 */
	public static String add33(String s){
		String ret = "";

		try {
			if (s != null) {
				int len = s.length();

				if (len % 2 != 0) {
					return ret;
				}

				char[] cs = new char[len / 2];

				for (int i = 0; i < len / 2; i++) {
					int tmp = Converts.hexStringToInt(s.substring(2 * i, 2 * i + 2));
							
					tmp = (tmp + 51)&0xFF;
					cs[i] = (char) tmp;
				}

				ret = Converts.charArrayToHexString(cs);
			}
		} catch (Exception e) {
			ret = "";
		}

		return ret;
	}
	
	/**
	 * add33
	 * @param hex
	 * @return
	 */
	public static String sub33(String s){
		String ret = "";

		try {
			if (s != null) {
				int len = s.length();

				if (len % 2 != 0) {
					return ret;
				}

				char[] cs = new char[len / 2];

				for (int i = 0; i < len / 2; i++) {
					int tmp = Converts.hexStringToInt(s.substring(2 * i, 2 * i + 2));
							
					tmp = (tmp - 51)&0xFF;
					cs[i] = (char) tmp;
				}

				ret = Converts.charArrayToHexString(cs);
			}
		} catch (Exception e) {
			ret = "";
		}

		return ret;
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * 
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {

		BigDecimal b1 = new BigDecimal(Double.toString(v1));

		BigDecimal b2 = new BigDecimal(Double.toString(v2));

		return b1.add(b2).doubleValue();

	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * 
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {

		BigDecimal b1 = new BigDecimal(Double.toString(v1));

		BigDecimal b2 = new BigDecimal(Double.toString(v2));

		return b1.subtract(b2).doubleValue();

	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * 
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {

		BigDecimal b1 = new BigDecimal(Double.toString(v1));

		BigDecimal b2 = new BigDecimal(Double.toString(v2));

		return b1.multiply(b2).doubleValue();

	}

	/**
	 * 
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
	 * 
	 * 小数点以后10位，以后的数字四舍五入。
	 * 
	 * 
	 * @param v1
	 *            被除数
	 * 
	 * 
	 * @param v2
	 *            除数
	 * 
	 * @return 两个参数的商
	 * 
	 */

	public static double div(double v1, double v2) {

		return div(v1, v2, DEF_DIV_SCALE);

	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
	 * 
	 * 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * 
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * 
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {

		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}

		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));

		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {

		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");

		}

		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");

		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/***************************************************************************
	 * Function JBCSUnPackStep2 Author Xiaodong wang Date 2009-09-14
	 * 
	 * @param int
	 *            FormatId, int Exp, String Units, String Data
	 * @return Unpacked Result
	 **************************************************************************/
	public static String JBCSUnPackStep2(int FormatId, String iExp,
			String Units, String Data) {
		String Result = "";
		String TmpStr1 = "";
		String TmpStr2 = "";
		int Exp = 0;
		int icnt = 0;
		byte[] bArray;
		String dateStr;
		DecimalFormat df = new DecimalFormat("0.00");

		if (Data == null) {
			return Result;
		}

		if ((Units + "0").trim() != "0") {
			Units = " " + Units;
		} else {
			Units = "";
		}

		switch (FormatId) {
		case 1: // NNNNNNNN 1 uint32, eg:0xxx,1xxx,2xxx,3xxx :0xxx,1
			if (Data.length() != 8) {
				// Result = "Error Data {" + Data + "}";
				break;
			}

			if (iExp != "") {
				try {
					Exp = Integer.parseInt(iExp);
				} catch (Exception e) {
					break;
				}
			}
			Result = df.format(mul(hexStringTolong(Data.substring(6, 8)
					+ Data.substring(4, 6) + Data.substring(2, 4)
					+ Data.substring(0, 2)), Math.pow(10, Exp)))
					+ Units;
			break;
		case 2: // NNNN,nnhhDDMMYY 1 {uint16,date_time},eg:4xxx,5xxx,6xxx,7xxx
				// int16,d
			if (Data.length() != 14) {
				// Result = "Error Data {" + Data + "}";
				break;
			}

			if (iExp != "") {
				try {
					Exp = Integer.parseInt(iExp);
				} catch (Exception e) {
					break;
				}
			}

			if (Data.substring(4, 14).equals("0000000000")) {
				Result = "0";
				break;
			}

			Result = df.format(mul(hexStringTolong(Data.substring(2, 4)
					+ Data.substring(0, 2)), Math.pow(10, Exp)))
					+ Units + ", ";

			bArray = Converts.hexStringToByte(Data.substring(4));
			dateStr = Converts.byte2StrForDate(bArray);
			Result += DateFormat.format(dateStr, 3, 8);

			break;
		case 3: // NNNN 1 uint16, eg:8000
			if (Data.length() != 4) {
				// Result = "Error Data {" + Data + "}";
				break;
			}

			if (iExp != "") {
				try {
					Exp = Integer.parseInt(iExp);
				} catch (Exception e) {
					break;
				}
			}

			Result = df.format(mul(hexStringTolong(Data.substring(2, 4)
					+ Data.substring(0, 2)), Math.pow(10, Exp)))
					+ Units;
			break;
		case 4: // NN 1 uint8, eg:8020
			if (Data.length() != 2) {
				// Result = "Error Data {" + Data + "}";
				break;
			}

			if (iExp != "") {
				try {
					Exp = Integer.parseInt(iExp);
				} catch (Exception e) {
					break;
				}
			}

			Result = df.format(mul(hexStringTolong(Data), Math.pow(10, Exp)))
					+ Units;
			break;
		case 5: // SS...SS 1 string, max len=8, eg:0x9000 x len=8
			Result = Hexstr2Str(Data);
			// Result=Result.substring(0, Result.indexOf(0));
			break;
		case 6: // SS...SS 1 string, max len=16, eg:0x9013 x len=1
			Result = Hexstr2Str(Data);
			// Result=Result.substring(0, Result.indexOf(0));
			break;
		case 7: // SS...SS 1 string, max len=32, eg:0x9010 x len=3
			Result = Hexstr2Str(Data);
			// Result=Result.substring(0, Result.indexOf(0));
			break;
		case 8: // SS...SS 1 string, max len=64, eg:0x9003 x len=6
			Result = Hexstr2Str(Data);
			// Result=Result.substring(0, Result.indexOf(0));
			break;
		case 9: // ssmmhhDDMMYY 1 date_time, eg: 0x9021 eg: 0x
			if (Data.length() != 12) {
				// Result = "Error Data {" + Data + "}";
				break;
			}
			if (Data.equals("000000000000")) {
				break;
			}

			bArray = Converts.hexStringToByte(Data);
			dateStr = Converts.byte2StrForDate(bArray);
			Result = DateFormat.format(dateStr, 6, 7);
			break;
		case 10: // bbbbbbbbbbbbbbbb 1 bit string, eg:0x9100
			if (Data.length() != 4) {
				// Result = "Error Data {" + Data + "}";
				break;
			}

			Result = Integer.toBinaryString(hexStringToInt(Data.substring(2, 4)
					+ Data.substring(0, 2)));
			break;
		case 11: // DDMM 1 eg:0x9111
			if (Data.length() != 4) {
				// Result = "Error Data {" + Data + "}";
				break;
			}

			bArray = Converts.hexStringToByte(Data);
			dateStr = Converts.byte2StrForDate(bArray);
			Result = DateFormat.format(dateStr, 9, 10);
			break;
		case 12: // mmhh 1 eg:0x9114
			if (Data.length() != 4) {
				// Result = "Error Data {" + Data + "}";
				break;
			}

			bArray = Converts.hexStringToByte(Data);
			dateStr = Converts.byte2StrForDate(bArray);
			Result = DateFormat.format(dateStr, 11, 12);
			break;
		case 13: // NN,hhDDMM 1 uint8,array{hex string}, eg:0x9200
			if (Data.length() == 0) {
				// Result = "Error Data {" + Data + "}";
				break;
			}

			icnt = hexStringToInt(Data.substring(0, 2));

			if (Data.length() != (6 * icnt + 2)) {
				// Result = "Error Data {" + Data + "}";
				break;
			}

			Result = Integer.toString(icnt) + ", ";

			for (int i = 0; i < icnt; i++) {
				bArray = Converts.hexStringToByte(Data.substring(2 + i * 6,
						2 + (i + 1) * 6));
				dateStr = Converts.byte2StrForDate(bArray);

				if (i == icnt - 1) {
					Result += DateFormat.format(dateStr, 13, 14);
				} else {
					Result += DateFormat.format(dateStr, 13, 14) + ", ";
				}
			}
			break;
		case 14: // {uint8,array{hex string} NN,mmhh+rate 1 eg:0x9201
			if (Data.length() == 0) {
				// Result = "Error Data {" + Data + "}";
				break;
			}

			icnt = hexStringToInt(Data.substring(0, 2));

			if (Data.length() != (6 * icnt + 2)) {
				Result = "Error Data {" + Data + "}";
				break;
			}

			Result = Integer.toString(icnt) + " Time Zones, ";

			for (int i = 0; i < icnt; i++) {
				bArray = Converts.hexStringToByte(Data.substring(2 + i * 6,
						2 + i * 6 + 4));
				dateStr = Converts.byte2StrForDate(bArray);

				if (i == icnt - 1) {
					Result += DateFormat.format(dateStr, 11, 12)
							+ " "
							+ Integer.toString(hexStringToInt(Data.substring(
									2 + i * 6 + 4, 2 + (i + 1) * 6)));
				} else {
					Result += DateFormat.format(dateStr, 11, 12)
							+ " "
							+ Integer.toString(hexStringToInt(Data.substring(
									2 + i * 6 + 4, 2 + (i + 1) * 6))) + ", ";
				}
			}
			break;
		case 15: // NNNN,NNNN 1 {uint16, uint16}, eg:0x9400
			if (Data.length() != 8) {
				// Result = "Error Data {" + Data + "}";
				break;
			}

			Result = Long.toString(hexStringTolong(Data.substring(2, 4)
					+ Data.substring(0, 2)))
					+ Units + ", ";

			Result += Long.toString(hexStringTolong(Data.substring(6, 8)
					+ Data.substring(4, 6)))
					+ Units;
			break;
		case 16: // NN,NN 1 {uint8, uint8}, eg:0x9500
			if (Data.length() != 4) {
				// Result = "Error Data {" + Data + "}";
				break;
			}

			if (iExp != "") {
				try {
					Exp = Integer.parseInt(iExp);
				} catch (Exception e) {
					break;
				}
			}

			Result = df.format(mul(hexStringToInt(Data.substring(0, 2)), Math
					.pow(10, Exp)))
					+ Units + ", ";

			Result += df.format(mul(hexStringToInt(Data.substring(2, 4)), Math
					.pow(10, Exp)))
					+ Units;
			break;
		case 17: // b 1 boolean, eg:0x9610
			if (Data.length() != 2) {
				// Result = "Error Data {" + Data + "}";
				break;
			}

			Result = Integer
					.toBinaryString(hexStringToInt(Data.substring(0, 2)));
			break;
		case 18: // NN,NNNN ... NNNN 1 uint8,array{uint16}, eg:0x9711
			if (Data.length() == 0) {
				// Result = "Error Data {" + Data + "}";
				break;
			}

			icnt = hexStringToInt(Data.substring(0, 2));

			if (Data.length() != (4 * icnt + 2)) {
				Result = "Error Data {" + Data + "}";
				break;
			}

			Result = Integer.toString(icnt) + ", ";

			for (int i = 0; i < icnt; i++) {
				if (i == icnt - 1) {
					Result += Data.substring(2 + i * 4 + 2, 2 + i * 4 + 4)
							+ Data.substring(2 + i * 4, 2 + i * 4 + 2);
				} else {
					Result += Data.substring(2 + i * 4 + 2, 2 + i * 4 + 4)
							+ Data.substring(2 + i * 4, 2 + i * 4 + 2) + ", ";
				}
			}
			break;
		case 19: // +NN 1 int8, eg:0x9305
			if (Data.length() != 2) {
				// Result = "Error Data {" + Data + "}";
				break;
			}

			Result = Byte
					.toString((byte) hexStringTolong(Data.substring(0, 2)))
					+ Units;
			break;
		case 20: // +NNNN 1 int16, eg:0x9301
			if (Data.length() != 4) {
				// Result = "Error Data {" + Data + "}";
				break;
			}

			Result = Short.toString((short) hexStringTolong(Data
					.substring(2, 4)
					+ Data.substring(0, 2)))
					+ Units;
			break;
		case 21: // +NNNNNNNN 1 int32, eg:
			if (Data.length() != 8) {
				// Result = "Error Data {" + Data + "}";
				break;
			}

			Result = Integer.toString((int) hexStringTolong(Data
					.substring(6, 8)
					+ Data.substring(4, 6)
					+ Data.substring(2, 4)
					+ Data.substring(0, 2)))
					+ Units;
			break;
		case 22: // bbbbbbbb 1 bit string, eg:0x9302
			if (Data.length() != 2) {
				// Result = "Error Data {" + Data + "}";
				break;
			}

			Result = Integer
					.toBinaryString(hexStringToInt(Data.substring(0, 2)));
			break;
		case 23:// bbbbbbbb 1 bit string, eg:0x9004, meter type
			/*
			 * B7 0:普通 common 1:防窃电 tamper B6 保留 B5 保留 B4 保留 B3 0:single-phase
			 * 1:三相3-phase B2B1 00:两2 Line 01:三线 3 Line 10:四线 4 Line B0 0:无费率
			 * 1:有费率
			 */
			// here should get description from database
			if (Data.length() != 2) {
				// Result = "Error Data {" + Data + "}";
				break;
			}
			icnt = hexStringToInt(Data.substring(0, 2));

			Result = ((icnt & 0x08) == 0x08) ? ("3-Phase ") : ("1-Phase ");
			switch ((icnt & 0x06) >> 1) {
			case 0:
				Result += "2-Wire ";
				break;
			case 1:
				Result += "3-Wire ";
				break;
			case 2:
				Result += "4-Wire ";
				break;
			case 3:
				Result += "Not Defined Wire ";
				break;
			}
			Result += ((icnt & 0x01) == 0x01) ? ("Multi Tariff ")
					: ("Single Tariff ");
			Result += ((icnt & 0x80) == 0x80) ? ("Anti-Tamper") : ("Common");
			break;
		case 24:// bbbbbbbb 1 bit string, eg:0x9103, load status
			/**
			 * 负载状态 0 正常 1 重载 2 过载
			 */
			// here should get description from database
			if (Data.length() != 2) {
				// Result = "Error Data {" + Data + "}";
				break;
			}
			icnt = hexStringToInt(Data);
			if (icnt == 0) {
				Result = "Load normal";
			}
			if (icnt == 1) {
				Result = "Load heavy";
			}
			if (icnt == 2) {
				Result = "Overload";
			}
			if (icnt > 2) {
				Result = "not defined {" + Data + "}";
			}
			break;
		case 25:// bbbbbbbb 1 bit string, eg:0x9302, clock status
			/*
			 * 时钟状态字 B0 数值无效
			 * 
			 * B1 数值不确定 B2 时钟基准不同 B3 时钟状态无效
			 * 
			 * B4 reserve B5 reserve B6 reserve B7 允许夏令时
			 * 
			 */
			// here should get description from database
			if (Data.length() != 2) {
				// Result = "Error Data {" + Data + "}";
				break;
			}
			icnt = hexStringToInt(Data);
			Result = ((icnt & 0x01) == 0x01) ? ("invalid value, ") : (""); // 数值无效

			Result += ((icnt & 0x02) == 0x02) ? ("Uncertain value, ") : (""); // 数值不确定
			Result += ((icnt & 0x04) == 0x04) ? ("Clock benchmark differ, ")
					: (""); // 时钟基准不同
			Result += ((icnt & 0x08) == 0x08) ? ("Clock state invalid, ")
					: (""); // 时钟状态无效

			Result += ((icnt & 0x80) == 0x80) ? ("Allow daylight saving time, ")
					: (""); // 允许夏令时

			break;
		case 26:// bbbb...bbbb 1 bit string, eg:0x9100, HW statuswords
			/*
			 * 硬件状态字 B0 非锁表状态
			 * 
			 * B1 清零EEPROM状态
			 * 
			 * B2 RAM异常 B3 EEPROM异常 B4 FLASH异常 B5 计量异常 B6 电池电压低
			 * 
			 * B7 未校表
			 * 
			 * B8 时钟未补偿
			 * 
			 * B9 时钟异常 B10 reserve B11 reserve B12 reserve B13 reserve B14
			 * reserve B15 reserve
			 */
			// here should get description from database
			if (Data.length() != 4) {
				// Result = "Error Data {" + Data + "}";
				break;
			}
			icnt = hexStringToInt(Data.substring(2, 4) + Data.substring(0, 2));
			Result = ((icnt & 0x0001) == 0x0001) ? ("Not Locked, ") : (""); // 非锁表状态

			Result += ((icnt & 0x0002) == 0x0002) ? ("Clear EEPROM, ") : (""); // 清零EEPROM状态

			Result += ((icnt & 0x0004) == 0x0004) ? ("RAM Abnormal, ") : (""); // RAM异常
			Result += ((icnt & 0x0008) == 0x0008) ? ("EEPROM Abnormal, ")
					: (""); // EEPROM异常
			Result += ((icnt & 0x0010) == 0x0010) ? ("FLASH Abnormal, ") : (""); // FLASH异常
			Result += ((icnt & 0x0020) == 0x0020) ? ("Measurement Abnormal, ")
					: (""); // 计量异常
			Result += ((icnt & 0x0040) == 0x0040) ? ("Low battery voltage, ")
					: (""); // 电池电压低

			Result += ((icnt & 0x0080) == 0x0080) ? ("Not calibrate, ") : (""); // 未校表

			Result += ((icnt & 0x0100) == 0x0100) ? ("Not RTC Compensate, ")
					: (""); // 时钟未补偿

			Result += ((icnt & 0x0200) == 0x0200) ? ("RTC Abnormal") : (""); // 时钟异常
			break;
		case 27:// bbbb...bbbb 1 bit string, eg:0x9102, function active
				// statuswords
			/*
			 * 功能激活状态字 B0 反向电能计量 B1 电能绝对值相加
			 * 
			 * B2 重载功能检测
			 * 
			 * B3 紧急供电模式
			 * 
			 * B4 正常供电限载 B5 紧急供电限载
			 * 
			 * B6 合闸后功率检测
			 * 
			 * B7 窃电处罚开启
			 * 
			 * B8 reserve B9 reserve B10 reserve B11 reserve B12 reserve B13
			 * reserve B14 reserve B15 reserve
			 */
			// here should get description from database
			if (Data.length() != 4) {
				// Result = "Error Data {" + Data + "}";
				break;
			}
			icnt = hexStringToInt(Data.substring(2, 4) + Data.substring(0, 2));
			Result = ((icnt & 0x0001) == 0x0001) ? ("Export energy measurement activated, ")
					: (""); // 反向电能计量
			Result += ((icnt & 0x0002) == 0x0002) ? ("Absoluted energy accumulation, ")
					: (""); // 电能绝对值相加

			Result += ((icnt & 0x0004) == 0x0004) ? ("Overload detection, ")
					: (""); // 重载功能检测

			Result += ((icnt & 0x0008) == 0x0008) ? ("Emergent power supply, ")
					: (""); // 紧急供电模式

			Result += ((icnt & 0x0010) == 0x0010) ? ("Normal load limitation, ")
					: (""); // 正常供电限载
			Result += ((icnt & 0x0020) == 0x0020) ? ("Emergent load limitation, ")
					: (""); // 紧急供电限载

			Result += ((icnt & 0x0040) == 0x0040) ? ("Power detection, ")
					: (""); // 合闸后功率检测

			Result += ((icnt & 0x0080) == 0x0080) ? ("Punish measurement activated")
					: (""); // 窃电处罚开启

			break;
		case 28:// bbbb...bbbb 1 bit string, eg:0x9101,Tamper statuswords
			/*
			 * 单相表窃电状态字 三相表窃电状态字 B0 电流反向 B0 逆相序
			 * 
			 * B1 负载接地 B1 A相失压
			 * 
			 * B2 单线供电(断零) B2 B相失压
			 * 
			 * B3 零线干扰 B3 C相失压
			 * 
			 * B4 磁场干扰 B4 A相电流反向
			 * 
			 * B5 低电压 B5 B相电流反向
			 * 
			 * B6 开盖 B6 C相电流反向
			 * 
			 * B7 ESD攻击 B7 CT短路 B8 reserve B8 CT开路
			 * 
			 * B9 reserve B9 零线干扰 B10 reserve B10 磁场干扰 B11 reserve B11 电压不平衡
			 * 
			 * B12 reserve B12 电流不平衡
			 * 
			 * B13 reserve B13 零线电流高
			 * 
			 * B14 reserve B14 开盖
			 * 
			 * B15 reserve B15 ESD攻击
			 */
			// here should get description from database
			if (Data.length() != 4) {
				// Result = "Error Data {" + Data + "}";
				break;
			}
			icnt = hexStringToInt(Data.substring(2, 4) + Data.substring(0, 2));

			if (icnt == 0) {
				break;
			}

			// Single-phase
			TmpStr1 = ((icnt & 0x0001) == 0x0001) ? ("Current reverse, ")
					: (""); // 电流反向
			TmpStr1 += ((icnt & 0x0002) == 0x0002) ? ("Earth load, ") : (""); // 负载接地
			TmpStr1 += ((icnt & 0x0004) == 0x0004) ? ("Neutral disconnect, ")
					: (""); // 单线供电(断零)
			TmpStr1 += ((icnt & 0x0008) == 0x0008) ? ("Neutral disturbance, ")
					: (""); // 零线干扰
			TmpStr1 += ((icnt & 0x0010) == 0x0010) ? ("Magnetic field interference, ")
					: (""); // 磁场干扰
			TmpStr1 += ((icnt & 0x0020) == 0x0020) ? ("Low voltage, ") : (""); // 低电压

			TmpStr1 += ((icnt & 0x0040) == 0x0040) ? ("Cover open, ") : (""); // 开盖
																				// B6
			TmpStr1 += ((icnt & 0x0080) == 0x0080) ? ("ESD Disturbance") : (""); // ESD攻击

			// Poly-phase
			TmpStr2 = ((icnt & 0x0001) == 0x0001) ? ("Wrong phase, ") : (""); // 逆相序

			TmpStr2 += ((icnt & 0x0002) == 0x0002) ? ("R-Ph Potential missing, ")
					: (""); // A相失压

			TmpStr2 += ((icnt & 0x0004) == 0x0004) ? ("Y-Ph Potential missing, ")
					: (""); // B相失压

			TmpStr2 += ((icnt & 0x0008) == 0x0008) ? ("B-Ph Potential missing, ")
					: (""); // C相失压

			TmpStr2 += ((icnt & 0x0010) == 0x0010) ? ("R-Ph Current reversal, ")
					: (""); // A相电流反向

			TmpStr2 += ((icnt & 0x0020) == 0x0020) ? ("Y-Ph Current reversal, ")
					: (""); // B相电流反向

			TmpStr2 += ((icnt & 0x0040) == 0x0040) ? ("B-Ph Current reversal, ")
					: (""); // C相电流反向

			TmpStr2 += ((icnt & 0x0080) == 0x0080) ? ("CT short, ") : (""); // CT短路
			TmpStr2 += ((icnt & 0x0100) == 0x0100) ? ("CT cut, ") : (""); // CT开路

			TmpStr2 += ((icnt & 0x0200) == 0x0200) ? ("Neutral disturbance, ")
					: (""); // 零线干扰
			TmpStr2 += ((icnt & 0x0400) == 0x0400) ? ("Magnetic field interference, ")
					: (""); // 磁场干扰
			TmpStr2 += ((icnt & 0x0800) == 0x0800) ? ("Voltage unbalance, ")
					: (""); // 电压不平衡

			TmpStr2 += ((icnt & 0x1000) == 0x1000) ? ("Current unbalance, ")
					: (""); // 电流不平衡

			TmpStr2 += ((icnt & 0x2000) == 0x2000) ? ("High Neutral current, ")
					: (""); // 零线电流高
			TmpStr2 += ((icnt & 0x4000) == 0x4000) ? ("Cover open, ") : (""); // 开盖

			TmpStr2 += ((icnt & 0x8000) == 0x8000) ? ("ESD Disturbance") : (""); // ESD攻击

			Result = TmpStr1 + "/" + TmpStr2;
			break;
		case 29:// eg:9120
			/*
			 * 1 A相失压(2. A相失压结束) 3 B相失压 5 C相失压 7 零线断开(单相表断零线) 9 A相电流反向 11 B相电流反向
			 * 13 C相电流反向 15 CT旁路(单相表负载接地) 17 CT开路 19 零线电流高(仅三相表) 21 保留 23
			 * 电流不平衡(仅三相表) 25 电压不平衡(仅三相表) 27 相分(失流) 29 电压异常 31 磁场干扰 33 开顶盖 35
			 * 开底盖 37 HV , 35kV干扰 39 错相序(仅三相表) 41 零线干扰 43 有功负载过载 45 视在负载过载 47 过压
			 * 49 欠压 51 时钟故障 52 电池电压低 53 存储器错误 54 No of interruption(power ups)
			 * exceeding a predefined threshold 55 POH less than a predefined
			 * threshold. 56 电源供电异常
			 */
			// here should get description from database
			if (Data.length() != 2) {
				// Result = "Error Data {" + Data + "}";
				break;
			}
			icnt = hexStringToInt(Data);

			switch (icnt) {
			case 0:
				Result = "not restored/not restored"; // "";
				break;
			case 1:
				Result = "R-Ph Potential missing Occured/R-Ph Potential missing Occured"; // "A相失压(2.
																							// A相失压结束)";
				break;
			case 2:
				Result = "R-Ph Potential missing Restored/R-Ph Potential missing Restored"; // "A相失压(2.
																							// A相失压结束)";
				break;
			case 3:
				Result = "Y-Ph Potential missing Occured/Y-Ph Potential missing Occured"; // "B相失压";
				break;
			case 4:
				Result = "Y-Ph Potential missing Restored/Y-Ph Potential missing Restored"; // "B相失压";
				break;
			case 5:
				Result = "B-Ph Potential missing Occured/B-Ph Potential missing Occured"; // "C相失压";
				break;
			case 6:
				Result = "B-Ph Potential missing Restored/B-Ph Potential missing Restored"; // "C相失压";
				break;
			case 7:
				Result = "Neutral cut Occured/Neutral cut Occured"; // "零线断开(单相表断零线)";
				break;
			case 8:
				Result = "Neutral cut Restored/Neutral cut Restored"; // "零线断开(单相表断零线)";
				break;
			case 9:
				Result = "R-Ph Current reversal Occured/R-Ph Current reversal Occured"; // "A相电流反向";
				break;
			case 10:
				Result = "R-Ph Current reversal Restored/R-Ph Current reversal Restored"; // "A相电流反向";
				break;
			case 11:
				Result = "Y-Ph Current reversal Occured/Y-Ph Current reversal Occured"; // "B相电流反向";
				break;
			case 12:
				Result = "Y-Ph Current reversal Restored/Y-Ph Current reversal Restored"; // "B相电流反向";
				break;
			case 13:
				Result = "B-Ph Current reversal Occured/B-Ph Current reversal Occured"; // "C相电流反向";
				break;
			case 14:
				Result = "B-Ph Current reversal Restored/B-Ph Current reversal Restored"; // "C相电流反向";
				break;
			case 15:
				Result = "Earth Load Occured/CT short Occured"; // "CT旁路(单相表负载接地)";
				break;
			case 16:
				Result = "Earth Load Restored/CT short Restored"; // "CT旁路(单相表负载接地)";
				break;
			case 17:
				// 20091222 modi by xiaodongwang "CT cut Occured"; //"CT开路",
				// 从协议上改成 Two phase measure
				// Result = "CT cut Occured"; //"CT开路";
				Result = "Two phase measure Occured/Two phase measure Occured"; // "两相计量";
				break;
			case 18:
				// 20091222 modi by xiaodongwang "CT cut Occured"; //"CT开路",
				// 从协议上改成 Two phase measure
				// Result = "CT cut Restored"; //"CT开路";
				Result = "Two phase measure Restored/Two phase measure Restored"; // "两相计量";
				break;
			case 19:
				Result = "High Neutral current Occured/High Neutral current Occured"; // "零线电流高(仅三相表)";
				break;
			case 20:
				Result = "High Neutral current Restored/High Neutral current Restored"; // "零线电流高(仅三相表)";
				break;
			case 21:
				Result = "/"; // "保留";
			case 22:
				Result = "/"; // "保留";
				break;
			case 23:
				Result = "Current unbalance Occured/Current unbalance Occured"; // "电流不平衡(仅三相表)";
				break;
			case 24:
				Result = "Current unbalance Restored/Current unbalance Restored"; // "电流不平衡(仅三相表)";
				break;
			case 25:
				Result = "Voltage unbalance Occured/Voltage unbalance Occured"; // "电压不平衡(仅三相表)";
				break;
			case 26:
				Result = "Voltage unbalance Restored/Voltage unbalance Restored"; // "电压不平衡(仅三相表)";
				break;
			case 27:
				Result = "Phase Split （Current Missing) Occured/Phase Split （Current Missing) Occured"; // "相分(失流)";
				break;
			case 28:
				Result = "Phase Split （Current Missing) Restored/Phase Split （Current Missing) Restored"; // "相分(失流)";
				break;
			case 29:
				Result = "Voltage abnormal Occured/Voltage abnormal Occured"; // "电压异常";
				break;
			case 30:
				Result = "Voltage abnormal Restored/Voltage abnormal Restored"; // "电压异常";
				break;
			case 31:
				Result = "Magnetic field interference Occured/Magnetic field interference Occured"; // "磁场干扰";
				break;
			case 32:
				Result = "Magnetic field interference Restored/Magnetic field interference Restored"; // "磁场干扰";
				break;
			case 33:
				Result = "Top cover open Occured/Top cover open Occured"; // "开顶盖";
				break;
			case 34:
				Result = "Top cover open Restored/Top cover open Restored"; // "开顶盖";
				break;
			case 35:
				Result = "Bottom cover open Occured/Bottom cover open Occured"; // "开底盖";
				break;
			case 36:
				Result = "Bottom cover open Restored/Bottom cover open Restored"; // "开底盖";
				break;
			case 37:
				Result = "HV , 35kV Occured/HV , 35kV Occured"; // "HV ,
																// 35kV干扰";
				break;
			case 38:
				Result = "HV , 35kV Restored/HV , 35kV Restored"; // "HV ,
																	// 35kV干扰";
				break;
			case 39:
				Result = "Wrong phase Occured/Wrong phase Occured"; // "错相序(仅三相表)";
				break;
			case 40:
				Result = "Wrong phase Restored/Wrong phase Restored"; // "错相序(仅三相表)";
				break;
			case 41:
				Result = "Neutral disturbance Occured/Neutral disturbance Occured"; // "零线干扰";
				break;
			case 42:
				Result = "Neutral disturbance Restored/Neutral disturbance Restored"; // "零线干扰";
				break;
			case 43:
				Result = "Active overload Occured/Active overload Occured"; // "有功负载过载";
				break;
			case 44:
				Result = "Active overload Restored/Active overload Restored"; // "有功负载过载";
				break;
			case 45:
				Result = "Apparent overload Occured/Apparent overload Occured"; // "视在负载过载";
				break;
			case 46:
				Result = "Apparent overload Restored/Apparent overload Restored"; // "视在负载过载";
				break;
			case 47:
				Result = "Overvoltage Occured/Overvoltage Occured"; // "过压";
				break;
			case 48:
				Result = "Overvoltage Restored/Overvoltage Restored"; // "过压";
				break;
			case 49:
				Result = "Undervoltage Occured/Undervoltage Occured"; // "欠压";
				break;
			case 50:
				Result = "Undervoltage Restored/Undervoltage Restored"; // "欠压";
				break;
			case 51:
				Result = "RTC error/RTC error"; // "时钟故障";
				break;
			case 52:
				Result = "Low battery voltage/Low battery voltage"; // "电池电压低";
				break;
			case 53:
				Result = "NVM error/NVM error"; // "存储器错误";
				break;
			case 54:
				Result = "No of interruption(power ups) exceeding a predefined threshold/No of interruption(power ups) exceeding a predefined threshold";
				break;
			case 55:
				Result = "POH less than a predefined threshold./POH less than a predefined threshold.";
				break;
			case 56:
				Result = "Abnormal power supply/Abnormal power supply"; // "电源供电异常";
				break;
			default:
				Result = "not defined!/not defined!";
				break;
			}
			break;
		case 30:// eg:9121
			/*
			 * 错误消息代号 1 电池故障 2 时钟丢失 3 EEPROM故障 4 FLASH故障 5 计量故障 6 表被解锁 7 8 9 10
			 */
			// here should get description from database
			if (Data.length() != 2) {
				// Result = "Error Data {" + Data + "}";
				break;
			}
			icnt = hexStringToInt(Data);
			switch (icnt) {
			case 1:
				Result = "Battery fault"; // "电池故障";
				break;
			case 2:
				Result = "RTC Reset"; // "时钟丢失";
				break;
			case 3:
				Result = "EEPROM fault"; // "EEPROM故障";
				break;
			// case 4:
			// Result = "FLASH fault"; //"FLASH故障";
			// break;
			// case 5:
			// Result = "Measuring fault"; //"计量故障";
			// break;
			// case 6:
			// Result = "The meter is unlocked"; //"表被解锁";
			// break;
			default:
				Result = "not defined!";
				break;
			}
			break;
		case 31:// eg:9122
			/*
			 * 编程消息代号 1 更改时钟 2 清零状态
			 * 
			 * 3 清需（手动结算） 4 设置电量底度 5 更改费率表
			 * 
			 * 6 更改显示 7 更改设置参数 8 激活使能功能
			 * 
			 * 9 本地合断闸
			 * 
			 * 10 远程合断闸
			 * 
			 */
			// here should get description from database
			if (Data.length() != 2) {
				// Result = "Error Data {" + Data + "}";
				break;
			}
			icnt = hexStringToInt(Data);
			switch (icnt) {
			case 1:
				Result = "Change RTC"; // "更改时钟";
				break;
			case 2:
				Result = "Clear EEPROM"; // "清零状态";
				break;
			case 3:
				Result = "Reset MD"; // "清需（手动结算）";
				break;
			case 4:
				Result = "Set initial energy"; // "设置电量底度";
				break;
			case 5:
				Result = "Change TOD table"; // "更改费率表";
				break;
			case 6:
				Result = "Change display options"; // "更改显示";
				break;
			case 7:
				Result = "Change setting parameters"; // "更改设置参数";
				break;
			case 8:
				Result = "Activate disnabled functions"; // "激活使能功能";
				break;
			case 9:
				Result = "Local load control"; // "本地合断闸";
				break;
			case 10:
				Result = "Remote load control"; // "远程合断闸";
				break;
			default:
				Result = "not defined!";
				break;
			}
			break;
		case 32:// 0x9002 生产受控状态
			/*
			 * 0 产品 1 样品 255 内部测试
			 */
			if (Data.length() != 2) {
				// Result = "Error Data {" + Data + "}";
				break;
			}
			icnt = hexStringToInt(Data);
			switch (icnt) {
			case 0:
				Result = "Manufacture"; // "产品";
				break;
			case 1:
				Result = "Specimen"; // "样品";
				break;
			case 255:
				Result = "Internal testing"; // ;"内部测试";
				break;
			}
			break;
		case 33:// NN 1 uint8, 整数
			if (Data.length() != 2) {
				// Result = "Error Data {" + Data + "}";
				break;
			}

			Result = Long.toString(hexStringTolong(Data.substring(0, 2)))
					+ Units;
			break;
		case 34:// NNNN 1 uint16, 整数
			if (Data.length() != 4) {
				// Result = "Error Data {" + Data + "}";
				break;
			}

			Result = Long.toString(hexStringTolong(Data.substring(2, 4)
					+ Data.substring(0, 2)))
					+ Units;
			break;
		case 35:// NNNNNNNN 1 uint32, 整数
			if (Data.length() != 8) {
				// Result = "Error Data {" + Data + "}";
				break;
			}

			Result = Long.toString(hexStringTolong(Data.substring(6, 8)
					+ Data.substring(4, 6) + Data.substring(2, 4)
					+ Data.substring(0, 2)))
					+ Units;
			break;
		case 36: // NNNN,ssmmhhDDMMYY 1 date_time, eg: 0xA200
			if (Data.length() != 16) {
				// Result = "Error Data {" + Data + "}";
				break;
			}
			if (Data.equals("0000000000000000")) {
				break;
			}

			Result = Long.toString(hexStringTolong(Data.substring(2, 4)
					+ Data.substring(0, 2)))
					+ ", ";

			bArray = Converts.hexStringToByte(Data.substring(4, 16));
			dateStr = Converts.byte2StrForDate(bArray);
			Result = DateFormat.format(dateStr, 6, 7);
			break;
		case 1000: // Block Read 1 eg:A0FF,A1FF
			// QueryInfoDAOImpl QueryInfoDAOImpl= new QueryInfoDAOImpl();
			// String param="0000";
			// Result = QueryInfoDAOImpl.getParam(param);

			Result = "Block Data! {" + Result + "}";
			break;
		case 10000: // Set Command 1 eg:0x9700
			Result = "Set Command!";
			break;
		default:
			break;
		}
		return Result;
	}

	public static String JBCSUnPackStep3(String Data, String[] idstr) {
		String Result = "";
		String formatid = "";
		String description = "";
		String bytelen = "";
		String zExp = "";
		String param_hex = "";
		String param_hex_chk = "";
		int compstd = 255;
		int compsrc = 0;
		int flag = 0;
		double f1 = 0.0;

		String unit = "";

		for (int i = 0; i < idstr.length; i++) {
			String temp = idstr[i];

			formatid = GetIdstrItem(temp, 1);
			description = GetIdstrItem(temp, 2);
			bytelen = GetIdstrItem(temp, 3);
			zExp = GetIdstrItem(temp, 4);
			param_hex = GetIdstrItem(temp, 5);
			unit = GetIdstrItem(temp, 6);

			String TmpData = Data.substring(0, Integer.parseInt(bytelen) * 2);

			/*
			 * filter the 9120 value = 1,3,5 ,for the 8011, 8012, 8013, >255
			 * alarm ****begin***
			 */
			if (param_hex.equals("9120")) {
				if (i == 0) {
					flag = 1;
				} else {
					flag = 0;
				}

				compsrc = hexStringToInt(TmpData);

				switch (compsrc) {
				case 1:
					param_hex_chk = "8011";
					break;
				case 3:
					param_hex_chk = "8012";
					break;
				case 5:
					param_hex_chk = "8013";
					break;
				default:
					break;
				}
			}

			if (param_hex.equals(param_hex_chk)) {
				f1 = mul(hexStringTolong(TmpData.substring(2, 4)
						+ TmpData.substring(0, 2)), Math.pow(10, Integer
						.parseInt(zExp)));
				if ((f1 > compstd) && (flag == 1)) {
					Result = "";
					return Result;
				}
			}

			/*
			 * filter the 9120 value = 1,3,5 ,for the 8011, 8012, 8013, >255
			 * alarm ****end***
			 */

			if (idstr.length == 1) {
				Result += description
						+ "="
						+ JBCSUnPackStep2(Integer.parseInt(formatid), zExp,
								unit, TmpData);
			} else {
				Result += description
						+ "="
						+ JBCSUnPackStep2(Integer.parseInt(formatid), zExp,
								unit, TmpData) + " ; ";
			}
			Data = Data.substring(Integer.parseInt(bytelen) * 2, Data.length());
		}
		return Result;
	}

	public static String JBCSUnPackStep4(String Data, String idstr) {
		String Result = "";
		String formatid = "";
		String description = "";
		String bytelen = "";
		String zExp = "";
		String unit = "";

		formatid = GetIdstrItem(idstr, 1);
		description = GetIdstrItem(idstr, 2);
		bytelen = GetIdstrItem(idstr, 3);
		zExp = GetIdstrItem(idstr, 4);
		unit = GetIdstrItem(idstr, 6);

		Result = JBCSUnPackStep2(Integer.parseInt(formatid), zExp, unit, Data);
		return Result;
	}

	public static String GetIdstrItem(String Idstr, int Index) {
		String Result = "";
		int k = 0;
		for (int i = 0; i < Idstr.length(); i++) {
			if (Idstr.charAt(i) != ',') {
				Result += Idstr.charAt(i);
			} else {
				if (k == Index) {
					return Result;
				} else {
					k++;
					Result = "";
				}
			}
		}
		return Result;
	}

	/**
	 * 完成 窃电事件代号(奇数为开始,偶数为结束) 单相表窃电状态字 三相表窃电状态字, 单相表/三相表 的区分
	 * 
	 * @param Data
	 * @return -1 都有；0：单项表；1：三相表
	 */
	public static int GetMeterTypeIndex(String Data) {
		int Result = -1;

		if (Data.length() != 2) {
			return Result;
		}
		int icnt = hexStringToInt(Data.substring(0, 2));

		Result = ((icnt & 0x08) == 0x08) ? 1 : 0;
		return Result;
	}

	public static void main(String[] args) {

		String alarm = "283014041400010008000201013e29";
		System.out.println(bytesToHexString(str2Bcd(alarm)));
		bytesToHexString(str2Bcd(alarm));
		String conid = "192";
		// byte[] bb=ByteUtil.ltoc(conid);
		String ss = Converts.str2Hexstring(conid);
		// String str = "h1";
		// byte[] temp = str.getBytes();
		//     
		// for(int i=0;i<temp.length; i++){
		// System.out.print(temp[i]);
		// System.out.print(" ");
		// }
		// System.out.println("");
		//		
		// String tmp = bytesToHexString(temp);
		//		
		// System.out.println(tmp);
		//
		// str ="683100310068C900001600020279000001005D16";
		// // byte[] hex = hexStringToByte(str);
		// byte[] hex = hexStringToByte(tmp);
		//		
		// for(int i=0;i<hex.length; i++){
		// System.out.print(hex[i]);
		// System.out.print(" ");
		// }
		// System.out.println("");
		//		
		// System.out.println(str);
		// str = byteToStr(str);
		// System.out.println(str);
		//		
		// temp = str.getBytes();
		//     
		// for(int i=0;i<temp.length; i++){
		// System.out.print(temp[i]);
		// System.out.print(" ");
		// }
		// System.out.println("");
		//		
		// tmp = bytesToHexString(temp);
		//		
		// System.out.println(tmp);
		//		
		 System.out.println("----------------------------------------");
		 
		 byte []bb=Converts.hexStringToByte("0003");
		 int coutn= ByteUtil.bctos(bb);

		// String temp = "0002";
		// System.out.println(Converts.hexStringToInt(temp));

//		 System.out.println(Converts.JBCSUnPackStep2(1, "-2", "kWh",
//		 "11111111",""));
//		 System.out.println(Converts.JBCSUnPackStep2(2, "1", "",
//		 "000101020F0A09",""));
		// System.out.println(Converts.Hexstr2Str("3031323334350036373839"));
		// System.out.println(Converts.JBCSUnPackStep2(5, "", "",
		// "3031323334350036",""));
		// System.out.println(Converts.JBCSUnPackStep2(2, "", "",
		// "10000F0A010203",""));
		// System.out.println(Converts.JBCSUnPackStep2(11, "", "", "0F0A",""));
		// System.out.println(Converts.JBCSUnPackStep2(12, "", "", "0F0A",""));
		// System.out.println(Converts.JBCSUnPackStep2(14, "", "",
		// "030C0F090C0F080C0F07",""));
		// System.out.println(Converts.JBCSUnPackStep2(15, "0", "s",
		// "12345678",""));
		// System.out.println(Converts.JBCSUnPackStep2(16, "0", "s",
		// "1234",""));
		// System.out.println(Converts.JBCSUnPackStep2(18, "0", "",
		//		 "000000000000",""));
		// System.out.println(Converts.JBCSUnPackStep2(9, "", "",
		// "000000000000"));
		// System.out.println(Converts.JBCSUnPackStep2(1000, "", "",
		// "FE04",""));

		// // char []chs = Converts.bcdToCharArray("0702000001301302");
		// // System.out.println(Converts.charArrayToHexString(chs));
		// // System.out.println(mul(12345678, Math.pow(10,-2)) );
		// // System.out.println(Double.toString(123456789.0 /100));
		// // QueryInfoDAOImpl QueryInfoDAOImpl= new QueryInfoDAOImpl();
		//
		// // System.out.println(QueryInfoDAOImpl.getParam("0000"));
		// // System.out.println(GetIdstrItem("01r3,4yyy,5,ttt6,",4));
		// // String []strArr = new String[5];
		// // System.out.println(JBCSUnPackStep3("12345678", strArr));// 注意颠倒

		// System.out.println("111"+"\r\n\r\n"+"222");
		// System.out.println(myPow(-2));
		// System.out.println(Converts.JBCSUnPackStep4("12345678",
		// "0,1,2,3,4,5,6"));
		// System.out.println(Converts.str2Hexstring("101112"));

		// byte []bArray = Converts.hexStringToByte("121B000101A9");
		// String dateStr = Converts.byte2StrForDate(bArray);
		// System.out.println(dateStr);
		// System.out.println(DateFormat.format(dateStr, 6, 1));
		 //String str = "7EA0216100020C5773";
		 //char []cs = Converts.stringToCharArray(str);
		 //char[] ch = Converts.checkSum(cs,1);
		 //String CSStr = Converts.charArrayToHexString(ch);
		 //System.out.println(CSStr);
		 
		 System.out.println(Converts.add33("CD"));
		 System.out.println(Converts.sub33("00"));
	}
	
	/**
	 * function:电笔编号在打包时要按照低字节在前的规则进行转换
	 * @param meterNo
	 * @return
	 */
	public static String inverseLowHightByte(String meterNo){
		int len = meterNo.length()/2;
		int raminder = meterNo.length()%2;
		
		String rtnMeterNo = "";
		
		if(raminder==0){
			String[] meterNoByte = new String[len];
			
			for(int i=0;i<len;i++){
				meterNoByte[i] = meterNo.substring(i*2,i*2+2);
			}
			
			for(int j=len-1;j>=0;j--){
				rtnMeterNo = rtnMeterNo + meterNoByte[j];
			}
		}
		
		
		return rtnMeterNo;
	}
	
	/**
	 * 645协议中的数据部分字节信息要高低位倒置翻转,并且每个字节部分都要加/减十六进制33
	 * @param meterNo
	 * @return
	 */
	public static String inverseLowHightByteData645(String data,int shiftNumber){
		int len = data.length()/2;
		int raminder = data.length()%2;
		
		String rtnData = "";
		String oneByteHexStr = "";
		int oneByteInt = 0;
		
		if(raminder==0){
			String[] dataByte = new String[len];
			
			for(int i=0;i<len;i++){
				dataByte[i] = data.substring(i*2,i*2+2);
			}
			
			for(int j=len-1;j>=0;j--){
				oneByteHexStr = dataByte[j];
				oneByteInt = hexStringToInt(oneByteHexStr);
				oneByteInt += shiftNumber;//十六进制的33对应十进制51
				
				oneByteInt = oneByteInt & 0xff;
				
				oneByteHexStr =Integer.toHexString(oneByteInt);
				oneByteHexStr = oneByteHexStr.length()==1?"0"+oneByteHexStr:oneByteHexStr;
				
				rtnData = rtnData + oneByteHexStr;
			}
		}
		
		return rtnData.toUpperCase();
	}
	
	/**
	 * 日期压缩为十六进制，两字节格式
	 *  <param name="dt">日期 例如:2010-8-1</param>
	 */
    public static String dateTimeToCompressHex(Date date) 
    {
    	int year = 0;
    	int month = 0;
    	int day = 0;
    	Calendar calendar = null;
    	
    	if(date==null){
    		calendar = GregorianCalendar.getInstance(); 
    	}else{
    		calendar = Calendar.getInstance();
    		calendar.setTime(date);
    	}
    	
    	year = calendar.get(Calendar.YEAR);
    	month =  calendar.get(Calendar.MONTH)+1;
    	day = calendar.get(Calendar.DATE);
    	
        byte bYear = 0;
        
        if (year >= 2000){
        	bYear = (byte)(year - 2000);
        }else{
        	bYear = (byte)(year - 1999);
        }
       
        byte bMonth = (byte)month;
        byte bDay = (byte)day;


        int tmpint = bYear << 9;
        tmpint += bMonth << 5;
        tmpint += bDay;
        String tmpstr = String.format("%x", tmpint).toUpperCase();

        return tmpstr;
    }
    
    public static String CompressHexToDateTime(String HexStr) 
    {
        int tmpint = Integer.valueOf(HexStr, 16);
        
        byte bDay = (byte)(tmpint & 31);
        byte bMonth = (byte)((tmpint >> 5) & 15);
        byte bYear = (byte)((tmpint >> 9) & 127);
        
        String dayStr = String.valueOf(bDay);
        String monStr = String.valueOf(bMonth);
        String yearStr = String.valueOf(bYear);
        
        dayStr = dayStr.length() ==1?"0"+dayStr:dayStr;
        monStr = monStr.length() ==1?"0"+monStr:monStr;
        yearStr = yearStr.length()==1?"0"+yearStr:yearStr;
        
//        String DateStr = Convert.ToString(bYear).PadLeft(2, '0') +
//                         Convert.ToString(bMonth).PadLeft(2, '0') +
//                         Convert.ToString(bDay).PadLeft(2, '0');
        
        String DateStr = yearStr + monStr + dayStr;
        return DateStr;
    }

    
   /* public static JSONArray makeSelectHtmlJson(List<ParamModel> paramModelList){
		ParamModel paramModel = null;
		JSONArray jarray = new JSONArray();
		
		for(int i=0;i<paramModelList.size();i++){
			paramModel = paramModelList.get(i);
			JSONObject item = new JSONObject();
			item.put("id",paramModel.getKey());
		    item.put("globalName", paramModel.getValue());
		    jarray.add(item);
		}
		
		return jarray;
    }*/
    
    public static String convertZipDateToStand(String dateZip){
    	String standardDate = "";
    	
    	String yyyyHex = dateZip.substring(0,4);
		String mmHex = dateZip.substring(4,6);
		String ddHex = dateZip.substring(6,8);
		String hhHex = dateZip.substring(10,12);
		String miHex = dateZip.substring(12,14);
		String ssHex = dateZip.substring(14,16);
		
		hhHex = "FF".equals(hhHex)?"00":hhHex;
		miHex = "FF".equals(miHex)?"00":miHex;
		ssHex = "FF".equals(ssHex)?"00":ssHex;
		
		String yyyy = String.valueOf(Converts.hexStringToInt(yyyyHex));
		String mm = String.valueOf(Converts.hexStringToInt(mmHex)>12?0:Converts.hexStringToInt(mmHex));
		String dd = String.valueOf(Converts.hexStringToInt(ddHex)>31?0:Converts.hexStringToInt(ddHex));
		String hh = String.valueOf(Converts.hexStringToInt(hhHex)>24?0:Converts.hexStringToInt(hhHex));
		String mi = String.valueOf(Converts.hexStringToInt(miHex)>60?0:Converts.hexStringToInt(miHex));
		String ss = String.valueOf(Converts.hexStringToInt(ssHex)>60?0:Converts.hexStringToInt(ssHex));
		
		mm = mm.length()==1 ?("0"+mm):mm;
		dd = dd.length()==1 ?("0"+dd):dd;
		hh = hh.length()==1 ?("0"+hh):hh;
		mi = mi.length()==1 ?("0"+mi):mi;
		ss = ss.length()==1 ?("0"+ss):ss;
		
		standardDate = yyyy+"/" + mm + "/" + dd + " " + hh + ":" + mi + ":" + ss;
    	
    	return standardDate;
    }
    
    /**
     * 将日期格式转换为十六进制日期
     * @param dateTime
     * @return HEX
     */
    public static String convertDateTimeToHex(Date date){
    	//Date date = DateFormat.convertToDate(dateTime,"MM/dd/yyyy");
    	
    	Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		int yyyyInt = calendar.get(Calendar.YEAR);
		int mmInt = calendar.get(Calendar.MONTH)+1;
		int ddInt = calendar.get(Calendar.DAY_OF_MONTH);
//	    int hourInt = calendar.get(Calendar.HOUR_OF_DAY);
//	    int minuteInt = calendar.get(Calendar.MINUTE);
//	    
//    	String yyyy= dateTime.substring(6,10);
//    	String mm = dateTime.substring(0,2);
//    	String dd = dateTime.substring(3,5);
    	
//    	int yyyyInt = Integer.parseInt(yyyy);
//    	int mmInt = Integer.parseInt(mm);
//    	int ddInt = Integer.parseInt(dd);
    	
    	String yyyyHex = Integer.toString(yyyyInt, 16).toUpperCase();
    	String mmHex = Integer.toString(mmInt, 16).toLowerCase();
    	String ddHex = Integer.toString(ddInt, 16).toUpperCase();
    	
    	yyyyHex=yyyyHex.length()<4?"0"+yyyyHex:yyyyHex;
    	mmHex=mmHex.length()<2?"0"+mmHex:mmHex;
    	ddHex=ddHex.length()<2?"0"+ddHex:ddHex;
    	
    	return yyyyHex +mmHex + ddHex + "0000";
    }
    
    public static String convertHex2Binary(String hex){
    	String binary = "";
    	
    	try{
    		int hexInt = Integer.parseInt(hex, 16);
    		binary = Integer.toBinaryString(hexInt);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return binary;
    }
    
    /**
     * 二进制字符串转换为十进制字符串
     * @param binary
     * @return
     */
    public static String binaryToDecimal(String binary) {
		   int result=0;
		   int j=0;
		  
		   for (int i = binary.length(); i >0 ; i--) {
		     result+=Integer.parseInt(binary.substring(i-1, i))*Math.pow(2, j);
		     j++;
		   }
		  
		   return ""+result;
	}
    
    /**
     * 字符串翻转函数
     * @param source
     * @return
     */
    public static  String strReserve(String source){
		 String a=source;
        char[] b=a.toCharArray();
        char temp;
        for(int i=0;i<b.length/2;i++){
          temp=b[i];
          b[i]=b[b.length-i-1];
          b[b.length-i-1]=temp;
       }
        
       a=new String(b);	
       
       return a;
	}
    
    /**
     * 电表档案中存储的电表特征字是十进制数,但是协议下发是十进制对应二进制串反转后的时候进制数
     * @param meterCharacteristic
     * @return
     */
    public static String revertInt(int meterCharacteristic){
    	String meterCharacteristicHex = Integer.toHexString(meterCharacteristic);
		
		String binanry = Converts.convertHex2Binary(meterCharacteristicHex);
		
		String revertBinanry = Converts.strReserve(binanry);
		int revertBinanryLen = revertBinanry.length();
		
		for(int i=0;i<24-revertBinanryLen;i++){
			revertBinanry = revertBinanry + "0";
		}
		
		String revertIntStr = Converts.binaryToDecimal(revertBinanry);
		int revertInt = Integer.parseInt(revertIntStr);
		
	
		String revertHex = Integer.toHexString(revertInt);
		
		int revertHexLen = revertHex.length();
		
		for(int i=0;i<6-revertHexLen;i++){
			revertHex =   revertHex + "0";
		}
		
		return revertHex;
    }
    
    /**
     * 检查服务器的串口与外设连接是否能够通信
     * @return true:connect  false:disconnect
     */
   /* public static boolean checkComPortConnStatus(){
    	Encryption_Sub serialCommManager = new Encryption_Sub();
    	boolean rtnResult = false;
    	
    	try{
    		String comPort = "";
			String baudRate = "";
			PropertiesConfiguration properties;
			
			String path = Thread.currentThread()
			    .getContextClassLoader().getResource("").getPath();

			properties = new PropertiesConfiguration(path
					+ Constants.CONSTANT_FILE);
			properties
					.setReloadingStrategy(new FileChangedReloadingStrategy());

			comPort = (String) properties
					.getProperty(Constants.COM_PORT_KEY);
			baudRate = (String) properties
					.getProperty(Constants.COM_BAUD_RATE_KEY);

			String port = "COM" + comPort;
			rtnResult = serialCommManager.setup("11111111", port,Integer.parseInt(baudRate));
    	}catch(Exception e){
    		rtnResult = false;
    		e.printStackTrace();
    	}finally{
    		serialCommManager.disconnect();
    	}
    	
    	return rtnResult;
    }*/
    
    /**
     * 将十六进制的字符串转换为整形的字符串,并且其字符个数为基数时，自动在高位补零,
     * 主要是在转换日期型的数据时得到应用
     * @param hex
     * @return
     */
    public static String hex2IntStr(String hex){
		int hexInt = Integer.parseInt(hex, 16);
		
		String intStr = String.valueOf(hexInt);
		
		intStr = intStr.length()%2 ==0?intStr:"0"+intStr;
		
		return intStr;
	}
    
    /**
	 * 短信下发时，不够偶数位是要补"F"凑够偶数个数,并且每个字节信息的高地位要倒置
	 * @param data
	 * @return
	 */
	public static String processPhoneNumber(String data){
		String rtnData = "";
		String temp = "";
		StringBuffer buffer = new StringBuffer();
		
		int times = 0;
		
		try{
			if(data.length()%2 !=0){
				rtnData = data + "F";
				times = rtnData.length() /2 ;
			}
			
			for(int i=0;i<times;i++){
				temp = rtnData.substring(0,2);
				buffer.append(temp.substring(1,2) + temp.substring(0,1));
				rtnData = rtnData.substring(2,rtnData.length());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return buffer.toString();
	}
	
	/**
	 * 将十进制数转换为十六进制数（大写字母）,不够偶数为的高位补0
	 * @param number
	 * @return
	 */
	public static String int2Hex(int number){
		String hex = "";
		
		try{
			hex = Integer.toHexString(number).toUpperCase();
			hex = hex.length()%2!=0?"0"+hex:hex;
		}catch(Exception e){
			e.printStackTrace();
		}
		return hex;
	}

}
