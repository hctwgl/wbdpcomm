package com.wbdp.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
/**
 * 进制转换类
 * @author 汪赛军
 *
 */
public class HexUtils {
    //******************************************************************************************
    //                                                                      参数或返回值为字符串
    //******************************************************************************************
    /**
	 * 将字符串分解成十六进制字符流
	 * @param hexString
	 * @return
	 */
	public static byte[] hexStringToBytes(String hexString) {  
        if (hexString == null || hexString.equals("")) {  
            return null;  
        }  
        // toUpperCase将字符串中的所有字符转换为大写  
        hexString = hexString.toUpperCase();  
        int length = hexString.length() / 2;  
        // toCharArray将此字符串转换为一个新的字符数组。  
        char[] hexChars = hexString.toCharArray();  
        byte[] d = new byte[length];  
        for (int i = 0; i < length; i++) {  
            int pos = i * 2;  
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));  
        }  
        return d;  
    }  
    
    //将字节数组转换为16进制字符串  
    public static String BinaryToHexString(byte[] bytes) {  
        String hexStr = "0123456789ABCDEF";  
        String result = "";  
        String hex = "";  
        for (byte b : bytes) {  
            hex = String.valueOf(hexStr.charAt((b & 0xF0) >> 4));  
            hex += String.valueOf(hexStr.charAt(b & 0x0F));  
            result += hex + " ";  
        }  
        return result;  
    }  
    /**
     * 将原始字符串转换成16进制字符串【方法一】
     */
    public static String str2HexStr(String input, String charsetName) throws UnsupportedEncodingException {
        byte buf[] = input.getBytes(charsetName);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            //以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式。
            //如果参数为负，那么无符号整数值为参数加上 2^32；否则等于该参数。将该值转换为十六进制（基数 16）的无前导 0 的 ASCII 数字字符串。
            //如果无符号数的大小值为零，则用一个零字符 '0' (’\u0030’) 表示它；否则，无符号数大小的表示形式中的第一个字符将不是零字符。
            //用以下字符作为十六进制数字【0123456789abcdef】。这些字符的范围是从【'\u0030' 到 '\u0039'】和从【'\u0061' 到 '\u0066'】。
            String hex = Integer.toHexString(buf[i] & 0xFF);//其实核心也就这一样代码
            if (hex.length() == 1) hex = '0' + hex;
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    /**
     * 将原始字符串转换成16进制字符串【方法二】
     */
    public static String str2HexStr2(String str, String charsetName) throws UnsupportedEncodingException {
        byte[] bs = str.getBytes(charsetName);
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0, bit; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString();
    }
    /**
     * 将16进制字符串转换为原始字符串
     */
    public static String hexStr2Str(String hexStr, String charsetName) throws UnsupportedEncodingException {
        if (hexStr.length() < 1) return null;
        byte[] hexbytes = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            hexbytes[i] = (byte) (high * 16 + low);
        }
        return new String(hexbytes, charsetName);
    }
    //******************************************************************************************
    //                                                                      参数或返回值为字节数组
    //******************************************************************************************
    /**
     * 将二进制转换成16进制
     */
    public static String encode(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) hex = '0' + hex;
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
   
        
      /**
       * 十六进制转二进制
       * @param hexString
       * @return
       */
        public static String hexString2binaryString(String hexString)  
        {  
            if (hexString == null || hexString.length() % 2 != 0)  
                return null;  
            String bString = "", tmp;  
            for (int i = 0; i < hexString.length(); i++)  
            {  
                tmp = "0000"  
                        + Integer.toBinaryString(Integer.parseInt(hexString  
                                .substring(i, i + 1), 16));  
                bString += tmp.substring(tmp.length() - 4);  
            }  
            return bString;  
        }  
        /**
         * 二进制转十六进制
         * @param bString
         * @return
         */
        public static String binaryString2hexString(String bString)  
        {  
            if (bString == null || bString.equals("") || bString.length() % 8 != 0)  
                return null;  
            StringBuffer tmp = new StringBuffer();  
            int iTmp = 0;  
            for (int i = 0; i < bString.length(); i += 4)  
            {  
                iTmp = 0;  
                for (int j = 0; j < 4; j++)  
                {  
                    iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);  
                }  
                tmp.append(Integer.toHexString(iTmp));  
            }  
            return tmp.toString();  
        }  
    /**
     * 将16进制转换为二进制(服务端)
     */
    public static byte[] deocde(String hexStr) {
        if (hexStr.length() < 1) return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    //******************************************************************************************
    //                          org.apache.commons.codec.binary.Hex的实现方式
    //******************************************************************************************
    private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
   //将byte数组转化为十六进制数据
    public static char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        int i = 0;
        for (int j = 0; i < l; i++) {
            out[(j++)] = DIGITS[((0xF0 & data[i]) >>> 4)];
            out[(j++)] = DIGITS[(0xF & data[i])];
        }
        return out;
    }
    public static byte[] decodeHex(char[] data) throws Exception {
        int len = data.length;
        if ((len & 0x1) != 0) throw new Exception("Odd number of characters.");
        byte[] out = new byte[len >> 1];
        int i = 0;
        for (int j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f |= toDigit(data[j], j);
            j++;
            out[i] = ((byte) (f & 0xFF));
        }
        return out;
    }
    private static int toDigit(char ch, int index) throws Exception {
        int digit = Character.digit(ch, 16);
        if (digit == -1) throw new Exception("Illegal hexadecimal charcter " + ch + " at index " + index);
        return digit;
    }
    /*
     * 字节转10进制
     */
    public static int byte2Int(byte b){
      int r = (int) b;
      return r;
    }
    /*
     * 字节数组转16进制字符串
     */
    public static String bytes2HexString(byte[] b) {
      String r = "";
       
      for (int i = 0; i < b.length; i++) {
        String hex = Integer.toHexString(b[i] & 0xFF);
        if (hex.length() == 1) {
          hex = '0' + hex;
        }
        r += hex.toUpperCase();
      }
       
      return r;
    }
    /*
     * 字符转换为字节
     */
    private static byte charToByte(char c) {
      return (byte) "0123456789ABCDEF".indexOf(c);
     }
    /*
     * 16进制字符串转字节数组
     */
    public static byte[] hexString2Bytes(String hex) {
         
        if ((hex == null) || (hex.equals(""))){
          return null;
        }
        else if (hex.length()%2 != 0){
          return null;
        }
        else{        
          hex = hex.toUpperCase();
          int len = hex.length()/2;
          byte[] b = new byte[len];
          char[] hc = hex.toCharArray();
          for (int i=0; i<len; i++){
            int p=2*i;
            b[i] = (byte) (charToByte(hc[p]) << 4 | charToByte(hc[p+1]));
          }
         return b;
        }      
         
    }
    /*
     * 字节数组转字符串
     */
    public static String bytes2String(byte[] b) throws Exception {
      String r = new String (b,"UTF-8");    
      return r;
    }
    /*
     * 字符串转字节数组
     */
    public static byte[] string2Bytes(String s){
      byte[] r = s.getBytes();
      return r;
    }
    /**
     * 十六进制字符串转字符串
     * @param hex
     * @return
     * @throws Exception
     */
    public static String hex2String(String hex) throws Exception{
      String r = bytes2String(hexString2Bytes(hex));    
      return r;
    }
    /**
     * 字符串转十六进制字符串
     * @param s
     * @return
     * @throws Exception
     */
    public static String string2HexString(String s) throws Exception{
      String r = bytes2HexString(string2Bytes(s));    
      return r;
    }
    /** 
     * 16进制字符串转换为字符串 
     *  
     * @param s 
     * @return 
     */  
    public static String hexStringToString(String s) {  
        if (s == null || s.equals("")) {  
            return null;  
        }  
        s = s.replace(" ", "");  
        byte[] baKeyword = new byte[s.length() / 2];  
        for (int i = 0; i < baKeyword.length; i++) {  
            try {  
                baKeyword[i] = (byte) (0xff & Integer.parseInt(  
                        s.substring(i * 2, i * 2 + 2), 16));  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        try {  
            s = new String(baKeyword, "gbk");  
            new String();  
        } catch (Exception e1) {  
            e1.printStackTrace();  
        }  
        return s;  
    }  
 // 转化十六进制编码为字符串
    public static String toStringHex(String s)
    {
    byte[] baKeyword = new byte[s.length()/2];
    for(int i = 0; i < baKeyword.length; i++)
    {
    try
    {
    baKeyword[i] = (byte)(0xff & Integer.parseInt(s.substring(i*2, i*2+2),16));
    }
    catch(Exception e)
    {
    e.printStackTrace();
    }
    }
    try
    {
    s = new String(baKeyword, "utf-8");//UTF-16le:Not
    }
    catch (Exception e1)
    {
    e1.printStackTrace();
    }
    return s;
    } 
    /**
     * 十进制转换为bcd码
     * @param asc
     * @return
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
}
