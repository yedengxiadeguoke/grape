package com.grape.grape.doctors.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.grape.grape.doctors.service.SpringRainDoctorsService;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: SpringRainDoctorsServiceImpl
 * @Description:
 * @Author Chenjun
 * @Date 2020/12/26 11:12
 * @Version V1.0
 */
@Service
public class SpringRainDoctorsServiceImpl implements SpringRainDoctorsService {
    @Override
    public String getBusinessH5() {

        String requestUri = "https://test.chunyutianxia.com/";
        // 合作方 partner
        String partner = "pyux";
        // 合作方 partner_key， 注意不是 partner
        String partnerKey = "kwDMCj7uVbUiGpQE";
        // UNIX TIMESTAMP 最小单位为秒
        Long atime = DateUtil.currentSeconds();
        // 第三方用户唯一标识，可以为字母与数字组合的字符串。
        String userId = IdUtil.simpleUUID();
        String sign = getDoctorsSign(partnerKey, atime.toString(), userId);

        String requestPath = "cooperation/wap/business/login/"
                + "?user_id=" + userId
                + "&atime=" + atime
                + "&partner=" + partner
                + "&sign=" + sign;

        return requestUri + requestPath;
    }

    // 计算 Sign
    private String getDoctorsSign(String partnerKey, String atime, String userId) {
        String info = partnerKey + atime + userId;
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] srcBytes = info.getBytes();
        md5.update(srcBytes);
        byte[] resultBytes = md5.digest();
        String resultString = new String(new Hex().encode(resultBytes));
        return resultString.substring(8, 24);
    }

}
