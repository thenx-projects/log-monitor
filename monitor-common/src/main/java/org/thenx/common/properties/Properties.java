package org.thenx.common.properties;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * @author May
 *
 * 公共参数类
 */
@Slf4j
public class Properties implements Serializable {

    /**
     * 线上请求接口
     */
    public static String BASE_REQUEST_PROD = "";

    static {
        java.util.Properties properties = new java.util.Properties();
        InputStream resourceAsStream = Object.class.getResourceAsStream("/request.properties");
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE_REQUEST_PROD = (String) properties.get("base_request");
    }
}
