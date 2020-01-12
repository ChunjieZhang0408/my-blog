package com.example.util;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jwk.PublicJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * JSON Web令牌是一种紧凑的url安全方法，表示在两方之间传输的声明/属性。 这个例子演示了生产和消费一个签名的JWT
 */
public class RSAUtils {
    private static final Logger log = LoggerFactory.getLogger(RSAUtils.class);
    static RsaJsonWebKey instance = null;

    /**
     * 根据publicKey字符串生成publicKey
     *
     * @param publicKeyString publicKey字符串
     * @return
     * @throws JoseException
     */
    public static PublicJsonWebKey getPublicJsonWebKey(String publicKeyString) throws JoseException {
        return RsaJsonWebKey.Factory.newPublicJwk(publicKeyString);
    }

    /**
     * @param jwt jwt
     * @throws JoseException
     * @throws InvalidJwtException
     */
    public static void checkJwt(String jwt) throws InvalidJwtException {
        /*
         * 使用JwtConsumer builder构建适当的JwtConsumer，它将 用于验证和处理JWT。 JWT的具体验证需求是上下文相关的， 然而,
         * 通常建议需要一个（合理的）过期时间，一个受信任的时间 发行人, 以及将你的系统定义为预期接收者的受众。
         * 如果JWT也被加密，您只需要提供一个解密密钥对构建器进行解密密钥解析器。
         */
        JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireExpirationTime() //// JWT必须有一个有效期时间
                .setAllowedClockSkewInSeconds(30) // 允许在验证基于时间的令牌时留有一定的余地，以计算时钟偏差。单位/秒
                .setRequireSubject() // 主题声明
//                .setExpectedIssuer(Oauth2Config.ACCESS_TOKEN_SERVER_URL) // JWT需要由谁来发布,用来验证 发布人
//                .setExpectedAudience(Oauth2Config.CLIENT_ID) // JWT的目的是给谁, 用来验证观众
                .setVerificationKey(instance.getRsaPrivateKey()) // 用公钥验证签名 ,验证秘钥
                .setJwsAlgorithmConstraints( // 只允许在给定上下文中预期的签名算法,使用指定的算法验证
                        new AlgorithmConstraints(ConstraintType.WHITELIST, // 白名单
                                AlgorithmIdentifiers.RSA_USING_SHA256))
                .build(); // 创建JwtConsumer实例
        // 验证JWT并将其处理为jwtClaims
        JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
        log.info("JWT validation succeeded! {}", jwtClaims);
    }

    public static RsaJsonWebKey getInstanceRsaJsonWebKey(String kid) {
        if (instance == null) {
            try {
                instance = RsaJwkGenerator.generateJwk(2048);
                if (!StringUtils.isEmpty(kid)) {
                    instance.setKeyId(kid);
                }
            } catch (JoseException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     * 生成jwt
     *
     * @param rsaJsonWebKey rsaJsonWebKey
     * @return
     * @throws JoseException
     */
    public static String signJwt(RsaJsonWebKey rsaJsonWebKey) throws JoseException {
        // 创建claims，这将是JWT的内容 B部分
        JwtClaims claims = new JwtClaims();
//        claims.setIssuer(Oauth2Config.ACCESS_TOKEN_SERVER_URL); // 谁创建了令牌并签署了它
        // 令牌将被发送给谁
        claims.setAudience("client");
        // 令牌失效的时间长（从现在开始10分钟）
        claims.setExpirationTimeMinutesInTheFuture(10);
        claims.setGeneratedJwtId(); // 令牌的唯一标识符
        claims.setIssuedAtToNow(); // 当令牌被发布/创建时（现在）
        claims.setNotBeforeMinutesInThePast(2); // 在此之前，令牌无效（2分钟前）
        claims.setSubject("subject"); // 主题 ,是令牌的对象

        // JWT是一个JWS和/或一个带有JSON声明的JWE作为有效负载。
        // 在这个例子中，它是一个JWS，所以我们创建一个JsonWebSignature对象。
        JsonWebSignature jws = new JsonWebSignature();

        // JWS的有效负载是JWT声明的JSON内容
        jws.setPayload(claims.toJson());
        // JWT使用私钥签署
        jws.setKey(rsaJsonWebKey.getPrivateKey());
        /*
         * 设置关键ID（kid）头，因为这是一种礼貌的做法。 在这个例子中，我们只有一个键但是使用键ID可以帮助 促进平稳的关键滚动过程
         */
        jws.setKeyIdHeaderValue(rsaJsonWebKey.getKeyId());

        // 在jw/jws上设置签名算法，该算法将完整性保护声明
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        /*
         * 签署JWS并生成紧凑的序列化或完整的jw/JWS 表示，它是由三个点（'.'）分隔的字符串
         * 在表单头.payload.签名中使用base64url编码的部件 如果你想对它进行加密，你可以简单地将这个jwt设置为有效负载
         * 在JsonWebEncryption对象中，并将cty（内容类型）头设置为“jwt”。
         */
        String jwt = jws.getCompactSerialization();

        // 现在你可以用JWT做点什么了。比如把它寄给其他的派对
        // 越过云层，穿过网络。
        log.info("JWT:{} ", jwt);
        return jwt;

    }
}