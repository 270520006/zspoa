package com.zsp.utils;

import com.zsp.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * jwt工具类封装
 * @author asus
 *
 */
public class JwtUtils {
	
	public static final String SUBJECT = "congge";
	
	/**
	 * 设置token的过期时间
	 */
	public static final long EXPIRITION = 1000 * 24 * 60 * 60 * 7;
	
	/**
	 * 秘钥，不同的环境应该配置不同的秘钥，注意保存好，不要泄露
	 */
	public static final String APPSECRET_KEY = "congge_secret";
	
	/**
	 * 加密生成token
	 * @param user
	 * @return
	 */
	public static String generateJsonWebToken(User user){
		
//		if(user.getId() == null || user.getUsername()==null||user.getFaceImage()==null){
//			return null;
//		}
		
		String token = Jwts.builder().setSubject(SUBJECT)
			.claim("username", user.getUsername())
			.claim("password",user.getPassword())
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + EXPIRITION ))
			.signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
		return token;
	}
	
	/**
	 * 解密token获取用户信息
	 * @param token
	 * @return
	 */
	public static Claims checkJWT(String token){
		try {
			final Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
			return claims;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	

	

		

	
}
 