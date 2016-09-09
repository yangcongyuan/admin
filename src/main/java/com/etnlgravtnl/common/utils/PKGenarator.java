package com.etnlgravtnl.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by admin on 2016/5/13.
 */
public class PKGenarator {
    public static String getId()
/*     */   {
/*  15 */     String s = UUID.randomUUID().toString();
/*     */
/*  17 */     return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) +
/*  18 */       s.substring(19, 23) + s.substring(24);
/*     */   }
    /*     */
/*     */   public static synchronized String[] getId(int number)
/*     */   {
/*  29 */     if (number < 1) {
/*  30 */       return null;
/*     */     }
/*  32 */     String[] ss = new String[number];
/*  33 */     for (int i = 0; i < number; i++) {
/*  34 */       ss[i] = getId();
/*     */     }
/*  36 */     return ss;
/*     */   }
    /*     */
/*     */   public static String getOrderId()
/*     */   {
/*  49 */     Date now = new Date();
/*  50 */     String sdate = date2Str(now, "yyyyMMdd");
/*  51 */     double random = Math.random();
/*  52 */     String sRandom = String.valueOf((int)(random * 89999999.0D + 10000000.0D));
/*  53 */     return sdate + sRandom;
/*     */   }
    /*     */
/*     */   public static String getInvestId()
/*     */   {
/*  66 */     return getOrderId();
/*     */   }
    /*     */
/*     */   public static String getLoanId()
/*     */   {
/*  79 */     return getOrderId();
/*     */   }
    /*     */
/*     */   public static String getGroupNo()
/*     */   {
/*  92 */     return getOrderId();
/*     */   }
    /*     */
/*     */   public static String getUserId()
/*     */   {
/* 106 */     String securityCode = SecurityCode.getSecurityCode();
/* 107 */     String sRandom = String.valueOf((int)(Math.random() * 899999.0D + 100000.0D));
/* 108 */     return securityCode + sRandom;
/*     */   }
    /*     */

    public static String date2Str(Date date, String pattern)
/*      */   {
/*  100 */     if (date == null) {
/*  101 */       return null;
/*      */     }
/*  103 */     if (pattern == null) {
/*  104 */       pattern = "yyyy-MM-dd";
/*      */     }
/*  106 */     SimpleDateFormat format = new SimpleDateFormat();
/*  107 */     format.applyPattern(pattern);
/*  108 */     return format.format(date);
/*      */   }

    public static void main(String[] args) {
        for (int i = 0 ;i<300;i++){
            System.out.println(getOrderId());
        }
    }
}
