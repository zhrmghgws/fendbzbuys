package com.hxd.fendbzbuys.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String getMD5Str2(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();

    }
    public static String getMD5Str(String str) {
		 MessageDigest md5 = null;  

         try  

         {  

             md5 = MessageDigest.getInstance("MD5"); 

         }catch(Exception e)  

         {  

             e.printStackTrace();  

             return "";  

         }  

           

         char[] charArray = str.toCharArray();  

         byte[] byteArray = new byte[charArray.length];  

           

         for(int i = 0; i < charArray.length; i++)  

         {  

             byteArray[i] = (byte)charArray[i];  

         }  

         byte[] md5Bytes = md5.digest(byteArray);  

           

         StringBuffer hexValue = new StringBuffer();  

         for( int i = 0; i < md5Bytes.length; i++)  

         {  

             int val = ((int)md5Bytes[i])&0xff;  

             if(val < 16)  

             {  

                 hexValue.append("0");  

             }  

             hexValue.append(Integer.toHexString(val));  

         }  

         return hexValue.toString(); 
   
	  }
    public static String encodeSelf(String s){
        return s.replace("*","%2A");
    }
}
