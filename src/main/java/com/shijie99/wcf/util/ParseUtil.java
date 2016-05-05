/**
 * 
 */
package com.shijie99.wcf.util;


/**
 * @author <a href="mailto:jermeywoo@gmail.com">Jermey.Woo</a>
 * 
 */
public class ParseUtil {
	
	/**
	 * 简单对编码转换 - 编码
	 * 
	 * @param data
	 * @return
	 */
	public static String encodeData(String data) {
		String strRtn = "";
		if (data == null)
			return strRtn;
		try {
			strRtn = Eryptogram.encryptData2(data);//URLEncoder.encode(data, Constant.ENC_UTF8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strRtn;
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
			strRtn = Eryptogram.decryptData2(data);//URLDecoder.decode(data, Constant.ENC_UTF8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strRtn;
	}

	public static void main(String[] args) {
		String oldata = "CD610348069C07066D8A470946FBAC37550CAAA569AC788A98FA4DA8531D60EF03443F47AF051155FF8C24F76D03508C1D5BB23BD3F8C37A25D0AFF8B66FF545BA27FBD87BC0C0042DD4EBC45C6E3E6CC4FF4EEE0CBAE7790381768FE5BFCAD6E429F24D8F8FD7AE3909CCCAF43EFDD22DD4EBC45C6E3E6C3F574B7B4A05BF43C24C14201F8F96111CCD34CFD8EAC17D5E80A95E982D78075C8A8F6E587ED49B7735D2138A903B6FB65F737E66E50E94B33A196DE264A9B8887B2D4BAD3BA334C099BDA44753179796D44539826D96B1100D17C36A1D0EE98E62C2958EF07FF2";
//		String data = ParseUtil.encodeData(oldata);
//		System.out.println(data);
		String data = ParseUtil.decodeData(oldata);
		System.out.println(data);
//		filterTransferData("oneway",oldata,"2015-12-23") ;
	}
}
