package com.boot.commons.captcha;

import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.boot.commons.core.exception.BusinessException;
import com.boot.commons.core.properties.SiteProperties;
import com.boot.commons.core.redis.RedisComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CaptchaUtil
 *
 * @author XINAN
 * @date 2020/1/2
 * @description
 */
@Component
public class CaptchaUtil {

    private final static String GROUP = "Captcha:";
    /**
     * 有效期 (单位：秒)
     */
    private final static Long TIME = 60L * 5;
    private static RedisComponent redisComponent;
    private static SiteProperties siteProperties;

    @Autowired
    public CaptchaUtil(RedisComponent redisComponent, SiteProperties siteProperties) {
        CaptchaUtil.redisComponent = redisComponent;
        CaptchaUtil.siteProperties = siteProperties;
    }

    /**
     * 校验
     *
     * @param token
     * @param userInputCode
     * @param throwException
     */
    public static boolean verify(String token, String userInputCode, boolean throwException) {
        try {
            verify(token, userInputCode);
        } catch (BusinessException e) {
            if (throwException) {
                throw e;
            }
            return false;
        }
        return true;
    }

    /**
     * 校验
     * 不通过则抛出异常
     *
     * @param token
     * @param userInputCode
     */
    public static void verify(String token, String userInputCode) {
        CaptchaErrCodeEnum.E_29901.throwIf(StrUtil.isBlank(token) || !CaptchaUtil.redisComponent.hasKey(GROUP + token));
        Object code = CaptchaUtil.redisComponent.get(GROUP + token);
        CaptchaErrCodeEnum.E_29900.throwIf(null == code || !code.toString().equalsIgnoreCase(userInputCode));
        // 校验通过, 删除token
        CaptchaUtil.redisComponent.del(GROUP + token);
    }

    /**
     * 生成一对图形验证码
     *
     * @return
     */
    public static CaptchaDTO genImgCaptcha() {
        return genImgCaptcha(TIME);
    }

    public static CaptchaDTO genImgCaptcha(Long time) {
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = cn.hutool.captcha.CaptchaUtil.createLineCaptcha(200, 100);
        String code = lineCaptcha.getCode();
        String imageBase64 = "data:image/jpg;base64," + lineCaptcha.getImageBase64();

        //生成token,
        String token = IdUtil.getSnowflake(CaptchaUtil.siteProperties.getWorkerId(), CaptchaUtil.siteProperties.getDataCenterId()).nextIdStr();

        //Redis中缓存token:code
        CaptchaUtil.redisComponent.set(GROUP + token, code, time);
        return new CaptchaDTO(token, imageBase64);
    }

    /**
     * 生成一对短信验证码
     *
     * @return
     */
    public static CaptchaDTO genSmsCaptcha(String mobile) {
        return genSmsCaptcha(mobile, TIME);
    }

    public static CaptchaDTO genSmsCaptcha(String mobile, Long time) {
        final String code = RandomUtil.randomNumbers(4);
        CaptchaUtil.redisComponent.set(GROUP + mobile, code, time);
        return new CaptchaDTO(mobile, code);
    }

}
