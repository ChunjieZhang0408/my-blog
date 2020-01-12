package com.example.util;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jwk.PublicJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.ErrorCodes;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.PublicKey;


/**
 * JSON Web令牌是一种紧凑的url安全方法，表示在两方之间传输的声明/属性。 这个例子演示了生产和消费一个签名的JWT
 */
public class JwtUtils {

    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

    public static void checkJwt(String jwt, String publicKeyString) throws MalformedClaimException, JoseException {
        /*
         * 使用JwtConsumer builder构建适当的JwtConsumer，它将 用于验证和处理JWT。 JWT的具体验证需求是上下文相关的， 然而,
         * 通常建议需要一个（合理的）过期时间，一个受信任的时间 发行人, 以及将你的系统定义为预期接收者的受众。
         * 如果JWT也被加密，您只需要提供一个解密密钥对构建器进行解密密钥解析器。
         */
        PublicJsonWebKey publicJwk = RsaJsonWebKey.Factory.newPublicJwk(publicKeyString);
//		publicJwk.setKeyId("rsa2");
        PublicKey publicKey = publicJwk.getPublicKey();
        JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireExpirationTime() //// JWT必须有一个有效期时间
                .setAllowedClockSkewInSeconds(30) // 允许在验证基于时间的令牌时留有一定的余地，以计算时钟偏差。单位/秒
                .setRequireSubject() // 主题声明
//                .setExpectedIssuer(Oauth2Config.ACCESS_TOKEN_SERVER_URL) // JWT需要由谁来发布,用来验证 发布人
//                .setExpectedAudience(Oauth2Config.CLIENT_ID) // JWT的目的是给谁, 用来验证观众
                .setVerificationKey(publicKey) // 用公钥验证签名 ,验证秘钥
                .setJwsAlgorithmConstraints( // 只允许在给定上下文中预期的签名算法,使用指定的算法验证
                        new AlgorithmConstraints(ConstraintType.WHITELIST, // 白名单
                                AlgorithmIdentifiers.RSA_USING_SHA256))
                .build(); // 创建JwtConsumer实例

        try {
            // 验证JWT并将其处理为jwtClaims
            JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
//			如果JWT失败的处理或验证，将会抛出InvalidJwtException。
//			希望能有一些有意义的解释（s）关于哪里出了问题。
            log.info("JWT validation succeeded! {}", jwtClaims);
        } catch (InvalidJwtException e) {
            log.info("Invalid JWT! " + e);
            // 对JWT无效的（某些）特定原因的编程访问也是可能的
            // 在某些情况下，您是否需要不同的错误处理行为。
            // JWT是否已经过期是无效的一个常见原因
            if (e.hasExpired()) {
                log.info("JWT expired at {}", e.getJwtContext().getJwtClaims().getExpirationTime());
            }
            // 或者观众是无效的
            if (e.hasErrorCode(ErrorCodes.AUDIENCE_INVALID)) {
                log.info("JWT had wrong audience: " + e.getJwtContext().getJwtClaims().getAudience());
            }
        }
    }

}