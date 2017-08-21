package com.hxd.fendbzbuys.utils;

import java.io.ByteArrayOutputStream;

/**
 * Created by ${lichao} on 2015/5/8.
 */
public class Encrypt {
    public static String encrypt(String original) {
        String bin2hex = bin2hex(original);
        String end="";
        for(int i=0;i<bin2hex.length();i++ ){
            char[] chars = bin2hex.toCharArray();
            String s;
            if((int)chars[i]-43<10){
                s="0"+((int)chars[i]-43);
            }else{
                s=(int)chars[i]-43+"";
            }
            String t= s.toCharArray()[1]+""+s.toCharArray()[0]+"";
            end=end+t;
        }
        return  replaceStr(end);
    }

    private static String z="DXHTARQOWL";
    private static String hexString = "0123456789abcdef";
    private static String replaceStr(String end){
        String x="";
        char[] charArray = end.toCharArray();
        for (int i=0;i<charArray.length;i++){
            int parseInt = Integer.parseInt(charArray[i]+"");
            x=x+z.charAt(parseInt);
        }
        return x;
    }
    public static String de_encrypt(String str ){
        String x="";
        String y="";
        String l="";
        for (int i=0;i<str.length();i++){
            x=x+ z.indexOf(str.toCharArray()[i]);
            if(x.length()==2){
                char[] chars = x.toCharArray();
                y=chars[1]+""+chars[0];
                int b=Integer.parseInt(y)+43;
                l=l+(char)b;
                x="";
            }
        }
        return hextoString(l);
    }

    private static String bin2hex(String original) {


        byte[] bytes = original.getBytes();
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
        }
        return sb.toString();
    }
    public static String hextoString(String bytes)
    {
        System.out.println("byte:::::::::::"+bytes);
        ByteArrayOutputStream baos=new ByteArrayOutputStream(bytes.length()/2);
        for(int i=0;i<bytes.length();i+=2)
            baos.write((hexString.indexOf(bytes.charAt(i))<<4 |hexString.indexOf(bytes.charAt(i+1))));
        return new String(baos.toByteArray());
    }

}
