package com.encryt.code;

import java.io.UnsupportedEncodingException;

/**
 * 加密算法
 */
public class ByteCrypt {

    private static int para1 = 160;
    private static int para2 = 58;

    /**
     * @param strKey1
     * @param strKey2
     */
    static void setKey(int strKey1, int strKey2) {
        para1 = strKey1;
        para2 = strKey2;
    }


    public static String getString(byte[] bytes) {
        byte[] en2 = doEn(bytes, 1);
        return new String(en2);

    }

    public static byte[] doEn(byte[] bytein, int type) {//type ==0 为加密 type ==1 解密
        if (bytein == null)
            return null;
        byte[] result = null;
        try {
            String in = new String(bytein, "utf-8");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < in.length(); i++) {
                int at = in.charAt(i);
                if (i % 2 == 0) {
                    if (type == 0)
                        at += para1;
                    else
                        at -= para1;
                } else {
                    if (type == 0)
                        at += para2;
                    else
                        at -= para2;
                }
                sb.append((char) at);
            }
            result = sb.toString().getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     *@param ssoToken 字符串
     *@return String 返回加密字符串
     */
    public static String decryptString(String ssoToken)
    {
        try
        {
            String name = new String();
            java.util.StringTokenizer st=new java.util.StringTokenizer(ssoToken,"%");
            while (st.hasMoreElements()) {
                int asc =  Integer.parseInt((String)st.nextElement()) - 27;
                name = name + (char)asc;
            }
            return name;
        }catch(Exception e)
        {
            e.printStackTrace() ;
            return null;
        }
    }

    /**
     *用户名加密
     *@return String 返回加密字符串
     */
    public static String encryptString(String ssoToken)
    {
        try
        {
            byte[] _ssoToken = ssoToken.getBytes("ISO-8859-1");
            String name = new String();
            for (int i = 0; i < _ssoToken.length; i++) {
                int asc = _ssoToken[i];
                _ssoToken[i] = (byte) (asc + 27);
                name = name + (asc + 27) + "%";
            }
            return name;
        }catch(Exception e) {
            e.printStackTrace() ;
            return null;
        }
    }
}
