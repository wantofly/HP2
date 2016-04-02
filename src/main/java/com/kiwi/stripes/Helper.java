package com.kiwi.stripes;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

public class Helper
{
	public static final String KEY_USERDATA = "USERDATA";
	public static final String KEY_STOREDATA = "STOREDATA";
	
	public static final String KEY_EX_VCODE = "exCode";
	public static final String KEY_CHEFSIGNUP_VCODE = "chefSignUpCode";
	public static final String KEY_STORESIGNUP_VCODE = "storeSignUpCode";
	
	private static Helper self = new Helper();
	private static final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();
	private static final char[] CODE_CHARS = "0123456789ABCDEFGHJKMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz".toCharArray();
	private static Random random = new Random(System.currentTimeMillis());

	protected MessageDigest MD5;
	//protected BASE64Encoder BASE64Encoder = new BASE64Encoder();
	//protected BASE64Decoder BASE64Decoder = new BASE64Decoder();
	
	public static String getNewCode()
	{
		char[] codeChars = "12345678".toCharArray();
		for(int index = 0;index < codeChars.length;index++)
		{
			codeChars[index] = CODE_CHARS[random.nextInt(CODE_CHARS.length)];
		}
		return String.valueOf(codeChars);
	}
	
	private Helper()
	{
		try
		{
			this.MD5 = MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
	}
	

	public static String toHex(byte[] buf)
	{
	    char[] chars = new char[2 * buf.length];
	    for (int i = 0; i < buf.length; ++i)
	    {
	        chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
	        chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
	    }
	    return new String(chars);
	}
	
//	public static boolean isEMail(String mail)
//	{
//		try
//		{       
//			InternetAddress emailAddr = new InternetAddress(mail);
//			emailAddr.validate();
//			return true; 
//		}
//		catch (AddressException ex)
//		{       
//			return false;
//		}    
//	}
		
	public static boolean isUserId(String id)
	{
		if(id != null && id.startsWith("USR-"))
			return true;
		return false;
	}
	
//	@SuppressWarnings("rawtypes")
//	public static Logger getLogger(Class clazz)
//	{
//		return LoggerFactory.getLogger(clazz);
//	}
//	
//	public static Logger getLogger(String name)
//	{
//		return LoggerFactory.getLogger(name);
//	}
//	
//	public static Logger getLogger()
//	{
//		return LoggerFactory.getLogger("Vesta");
//	}
//	
	public static void sleep(int time)
	{
		try{Thread.sleep(time);}
		catch (InterruptedException e){}
	}
	
	public static boolean isEmptyString(String value)
	{
		if(value == null)
			return true;
		if(value.trim().equals(""))
			return true;
		
		return false;
	}
	
	public static boolean isValidGender(String gender)
	{
		if(gender != null && (gender.equals("M") || gender.equals("F")))
			return true;
		return false;
	}
	
	// Key
	public static String getUserExchangeErrKey(String userId)
	{
		return "EXCHANGE-ERR-"+userId;
	}
	
	public static String getProvinceListKey()
	{
		return "PROVINCELIST";
	}
	
	public static String getProductCatListKey()
	{
		return "PRODUCTCATLIST";
	}

	public static String getPCatIdProductListKey(String pCatId)
	{
		return "PCATID-PRODUCTLIST-"+pCatId;
	}

	public static String getUserNameUserKey(String userName)
	{
		return "USERNAME-USER-"+userName;
	}
	
	//
	
	private static String getUUID()
	{
		return (UUID.randomUUID()).toString().toUpperCase();
	}
	
	public static String getPassWordCode(String passWord)
	{
		return passWord;
//		return Helper.toHex(self.MD5.digest(("Dragon2012"+passWord.trim()).getBytes()));
	}
	
	public static String getNewUserId()
	{
		return Helper.getNewId("USR");
	}

	private static String getNewId(String name)
	{
		return name.toUpperCase()+"-"+Helper.toHex(self.MD5.digest(Helper.getUUID().getBytes()));
	}
	
	public static boolean isPhoneNum(String num)
	{
		if(Helper.isEmptyString(num))
			return false;
		
		if(num.length() != 11)
			return false;
		
		try
		{
			 Long.parseLong(num);
		}
		catch (NumberFormatException e)
		{
			 return false;
		}

		if(!num.startsWith("1"))
			return false;
		
		return true;
	}
	
	
//	public static boolean isEMail2(String mail)
//	{
//		try
//		{       
//			InternetAddress emailAddr = new InternetAddress(mail);
//			emailAddr.validate();
//			return true; 
//		}
//		catch (AddressException ex)
//		{       
//			return false;
//		}    
//	}
	
	public static boolean isValidPassWord(String passWord)
	{
		if(Helper.isEmptyString(passWord))
			return false;
		
		if(passWord.trim().length() < 8 || passWord.trim().length() > 12)
			return false;
		
		return true;
	}
	
}
