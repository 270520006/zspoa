package com.zsp.utils;

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
	public static String generateJsonWebToken(Users user){
		
		if(user.getId() == null || user.getUsername()==null||user.getFaceImage()==null){
			return null;
		}
		
		String token = Jwts.builder().setSubject(SUBJECT)
			.claim("id", user.getId())
			.claim("name",user.getUsername())
			.claim("img", user.getFaceImage())
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
	
	/**
	 * eyJhbGciOiJIUzI1NiJ9.
	 * eyJzdWIiOiJjb25nZ2UiLCJpZCI6IjExMDExIiwibmFtZSI6Im51b3dlaXNpa2kiLCJpbWciOiJ3d3cudW9rby5jb20vMS5wbmciLCJpYXQiOjE1NTQ5OTI1NzksImV4cCI6MTU1NTU5NzM3OX0.
	 * 6DJ9En-UBcTiMRldZeevJq3e1NxJgOWryUyim4_-tEE

	 * @param args
	 */
	
	public static void main(String[] args) {
		
		Users user = new Users();
		user.setId("11011");
		user.setUsername("nuoweisiki");
		user.setFaceImage("www.uoko.com/1.png");
		String token = generateJsonWebToken(user);
		
		System.out.println(token);
		
		System.out.println("解密 ====================");
		
		Claims claims = checkJWT(token);
		if(claims != null){
			String id = claims.get("id").toString();
			String name = claims.get("name").toString();
			String img = claims.get("img").toString();
			
			System.out.println("id:" + id);
			System.out.println("name:" + name);
			System.out.println("img:" + img);
		}
		
	}
	
}
————————————————
版权声明：本文为CSDN博主「小码农叔叔」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/zhangcongyi420/article/details/89222307