package com.etnlgravtnl.common.security;

import com.etnlgravtnl.common.config.Global;
import com.etnlgravtnl.common.utils.PropertiesLoader;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hongtao.
 * Update by  junz
 * 按照HS512 加密规则生产token令牌
 */
public class JwtUtil {

    //单利模式 线程安全保证唯一性
    private  static Key key =null;
    public synchronized  static Key getKeyInstance() {
        if (key == null) {
            key =  MacProvider.generateKey();
        }
        return key;
    }
    static{
		// TODO Auto-generated method stub
    	getKeyInstance();
	}

    /**
     * @Name:
     * @Author: junz（作者）
     * @Version: V1.00 （版本号）
     * @Create Date: 2016-06-19（创建日期）
     * @param  userId 登录用户的id mainToken 主token
     * @Description:
     * 生成token  用于访问token 保持用户粘性
     */
    public static String generateAccessToken(String userId,String mainToken) {
        String token = Jwts.builder()
                .setSubject(userId)
                .setIssuer(Global.getConfig("productName"))
                .signWith(SignatureAlgorithm.HS512, getKeyInstance())
                .setIssuedAt(new Date())
                .claim("mainToken", mainToken)
                .compact();
        return token;
    }

    /**
     * @Name:
     * @Author: junz（作者）
     * @Version: V1.00 （版本号）
     * @Create Date: 2016-06-19（创建日期）
     * @param userId 登录用户的id
     * @Description:
     * 生成认证token 并设置token失效时间
     * 认证token用以获取Accesstoken，防止持续攻击
     */
    public static String generateMainToken(String userId) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(Global.loader.getProperty("maintoken_expire_days"))); // 目前時間加n天
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)
                ,Integer.parseInt(Global.loader.getProperty("maintoken_expire_hours"))
                ,Integer.parseInt(Global.loader.getProperty("maintoken_expire_minute"))
        );
        String token = Jwts.builder()
                .setSubject(userId)
                .setIssuer(Global.getConfig("productName"))
                .setExpiration(getExpiryDate(Integer.valueOf(Global.getConfig("accesstoken_expiry"))))
                .signWith(SignatureAlgorithm.HS512, getKeyInstance())
                .setIssuedAt(calendar.getTime())
                .compact();
        return token;
    }
    /**
     * @Name:
     * @Author: junz（作者）
     * @Version: V1.00 （版本号）
     * @Create Date: 2016-06-19（创建日期）
     * @param auth 认证token串
     * @Description:
     * Replacing "Bearer Token" to "Token" directly
     */
    public static String extractJwtTokenFromAuthorizationHeader(String auth) {
        //Replacing "Bearer Token" to "Token" directly
        return auth.replaceFirst("[B|b][E|e][A|a][R|r][E|e][R|r] ", "").replace(" ", "");
    }

    /**
     * @Name:
     * @Author: junz（作者）
     * @Version: V1.00 （版本号）
     * @Create Date: 2016-06-19（创建日期）
     * @param minutes 有效时间 单位为分钟
     * @Description:
     * 获得token失效时间
     */
    public static Date getExpiryDate(int minutes) {

        // 根据当前日期，来得到到期日期
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, minutes);

        return calendar.getTime();
    }

    public static boolean isValid(String token) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token.trim());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static String getUserId(String jwsToken) {
        if (isValid(jwsToken)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
            return claimsJws.getBody().getSubject();
        }
        return null;
    }
    public static String[] getRoles(String jwsToken) {
        if (isValid(jwsToken))
        {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
            return claimsJws.getBody().getAudience().split(",");
        }
        return new String[]{};
    }
    public static int getVersion(String jwsToken) {
        if (isValid(jwsToken)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
            return Integer.parseInt(claimsJws.getBody().getId());
        }
        return -1;
    }

    public static String getMainTokenInAccessToken(String accessToken) {
        if (isValid(accessToken)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(accessToken);
            return claimsJws.getBody().get("mainToken").toString();
        }
        return null;
    }

    public static Date getTokenExpire(String token) {
        if (isValid(token)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return claimsJws.getBody().get("exp",Date.class);
        }
        return null;
    }


    public static void main(String[] args) {
        System.out.println(new Date(1467393259l));
        System.out.println(new Date().getTime());
    }
}
