package com.shijie99.wcf.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.codec.binary.Base64;

/**
 * Gzip压缩解压缩字符串的工具类
 * @author zhoucl 2015-11-13
 *
 */
public class GzipUtil {
	// 压缩
	public static String compress(String str) throws IOException {
		if (str == null || str.length() == 0) {
			return str;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(str.getBytes());
		gzip.close();
		return out.toString("ISO-8859-1");
	}

	// 解压缩
	public static String uncompress(String str) throws IOException {
		if (str == null || str.length() == 0) {
			return str;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(
				str.getBytes("ISO-8859-1"));
		GZIPInputStream gunzip = new GZIPInputStream(in);
		byte[] buffer = new byte[256];
		int n;
		while ((n = gunzip.read(buffer)) >= 0) {
			out.write(buffer, 0, n);
		}
		// toString()使用平台默认编码，也可以显式的指定如toString("GBK")
		return out.toString();
	}
	
	public static void main(String[] args) throws Exception {
//		String str0 = "AMAD_F-19#1566649039#HKG-XMN,XMN-PEK;MF,MF;MF8016,MF8105;2015-11-18 20:45:00/2015-11-18 22:20:00,2015-11-19 15:00:00/2015-11-19 17:50:00;S,S#1134.0^187.0^0.0^0.0#12991230545909268#0#utour#AMAD_F@HKGW63100#1119843711#HKGW63100#";
//		String str1 = "AMAD_F-19#19#经济舱#E#0^HKG^XMN^MF^MF^8016^2015-11-18 20:45:00:00^2015-11-18 22:20:00:00^9^2105^HKG^XMN^738^true^S^ ^0^0^ ^false^ ^ ^ ^ ^ ^@^0^XMN^BJS^MF^MF^8105^2015-11-19 15:00:00:00^2015-11-19 17:50:00:00^9^2105^XMN^PEK^787^true^S^ ^0^0^ ^false^ ^ ^ ^ ^ ^#1250.0^206.0^1^ADT^HKD^0^ ##HKG-XMN,XMN-PEK;MF,MF;MF8016,MF8105;2015-11-18 20:45:00/2015-11-18 22:20:00,2015-11-19 15:00:00/2015-11-19 17:50:00;S,S#public#oneWay#AMAD_F#HKG-BJS#MF#1456.0#1250.0#0.0#0.0#0#HKGW63100#SOWTFS,SOWTFS#MF#RP,RP";
//		String str2 = "AMAD_F-19&19&经济舱&E&0^HKG^XMN^MF^MF^8016^2015-11-18 20:45:00:00^2015-11-18 22:20:00:00^9^2105^HKG^XMN^738^true^S^ ^0^0^ ^false^ ^ ^ ^ ^ ^@^0^XMN^BJS^MF^MF^8105^2015-11-19 15:00:00:00^2015-11-19 17:50:00:00^9^2105^XMN^PEK^787^true^S^ ^0^0^ ^false^ ^ ^ ^ ^ ^&1250.0^206.0^1^ADT^HKD^0^ &&HKG-XMN,XMN-PEK;MF,MF;MF8016,MF8105;2015-11-18 20:45:00/2015-11-18 22:20:00,2015-11-19 15:00:00/2015-11-19 17:50:00;S,S&public&oneWay&AMAD_F&HKG-BJS&MF&1456.0&1250.0&0.0&0.0&0&HKGW63100&SOWTFS,SOWTFS&MF&RP,RP";
//		String cs1 = GzipUtil.compress(str1);
//		String encode = Base64.encodeBase64String(GzipUtil.compress(str0 + cs1).getBytes());
//		System.out.println(encode + "==>" + encode.length());
//		
//		String s = new String(Base64.decodeBase64(encode));
//		String st = GzipUtil.uncompress(s);
//		String[] sarr = st.split("\\#");
//		System.out.println(GzipUtil.uncompress(sarr[10]));
//		
//		encode = Base64.encodeBase64String(GzipUtil.compress(str0 + str2).getBytes());
//		System.out.println(encode + "==>" + encode.length());
//		s = new String(Base64.decodeBase64(encode));
//		st = GzipUtil.uncompress(s);
//		System.out.println(st);
		
		String data = "H8KLCAAAAAAAAABdwo5BCsOCQAxFwq8iZDvCo8O5acKTacKnG8KrwqJ0w5Eqw5jCisOdw6gRBMKxw7d3w6rCqsK4w4gjw78Rw4jCr3fDg8O1ecO0YgQ1wpXCkAdmasO2wp0fwrvDnsKlw7HCl8ObwqlqB8OXDsKJYkVaVENRCcODPMOgwoVXw5DCqBrCmTdLZxHCnMKcW8K6MsOiw79OOMOmwrPCq0Y3wpLDgMOKNT8kCzMXwoHDlELDoDLCvSfCpsOpw7PCmsOeVMO/em/Dr8KwAyHChwjCoMKZw5DDmTVfwpdaYRjDkgAAAA==";
		data = GzipUtil.uncompress(new String(Base64.decodeBase64(data)));
		System.out.println(data);
	}
}
