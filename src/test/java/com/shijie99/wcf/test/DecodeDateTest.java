package com.shijie99.wcf.test;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import org.junit.Test;


public class DecodeDateTest {
	private static String Algorithm = "DES";
	private static String strkey = "CB7A92E3D3491975";
	// 定义 加密算法,可用 DES,DESede,Blowfish
	static boolean debug = false;
	
	@Test
	public void tets(){
		String date = "6605B932A4325F1B326051172D5BA9C7EBE7CC2FE8341BB179F16C0B58545E62355906BA2A73037622BD7F50D02E8F018975192C92685F09A8BC5611485032EF433F399EFFAE92E5C727030ACCA7AD086C808F1CED764A64B9B46EF986F45C82E0F74E1E0D66B558EE06F794E512C49734605344BD66BE21611742870C1769B5FA50F40FC74C1E3A";
		System.out.println(decodeData(date));
		String date2 = "6605B932A4325F1B5F81D224FAAFB06917A5EF94F9742ADF79F16C0B58545E62355906BA2A73037622BD7F50D02E8F018975192C92685F09A8BC5611485032EF433F399EFFAE92E5C727030ACCA7AD08698CC8EF79E55C35B9B46EF986F45C82E0F74E1E0D66B5588964F95489F461BEA833EF0E764B47DF9CD1DD674CF2CC4D";
		System.out.println(decodeData(date2));
	}
	/**
	 * 简单对编码转换－解编码
	 * 
	 * @param data
	 * @return
	 */
	public static String decodeData(String data) {
		String strRtn = "";
		if (data == null)
			return strRtn;
		try {
			strRtn = decryptData2(data);//URLDecoder.decode(data, Constant.ENC_UTF8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strRtn;
	}
	
	public static String decryptData2(String data) {
		String de = null;
		try {
			byte[] key = hexStringToByte(strkey);
			de = new String(decryptData(hexStringToByte(data), key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return de;
	}
	/**
	 * 字符串转成字节数组.
	 * 
	 * @param hex
	 *            要转化的字符串.
	 * @return byte[] 返回转化后的字符串.
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
	 * 将给定的已加密的数据通过指定的密钥进行解密
	 * 
	 * @param input
	 *            待解密的数据
	 * @param key
	 *            密钥
	 * @return byte[] 解密后的数据
	 * @throws Exception
	 */
	public static byte[] decryptData(byte[] input, byte[] key) throws Exception {
		SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, Algorithm);
		if (debug)
			System.out.println("解密前的信息:" + byte2hex(input));
		Cipher c1 = Cipher.getInstance(Algorithm);
		c1.init(Cipher.DECRYPT_MODE, deskey);
		byte[] clearByte = c1.doFinal(input);
		if (debug) {
			System.out.println("解密后的二进串:" + byte2hex(clearByte));
			System.out.println("解密后的字符串:" + (new String(clearByte)));

		}
		return clearByte;

	}
	
	/**
	 * 字节码转换成16进制字符串
	 * 
	 * @param byte[] b 输入要转换的字节码
	 * @return String 返回转换后的16进制字符串
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + ":";

		}
		return hs.toUpperCase();

	}
}
