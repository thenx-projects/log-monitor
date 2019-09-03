package com.tgpms.web.war.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.sun.xml.internal.fastinfoset.util.DuplicateAttributeVerifier.MAP_SIZE;

/**
 * @author May
 * <p>
 * War 包相关操作对外接口
 */
@Api(value = "WarController", tags = "War 包相关操作对外接口")
@RestController
@RequestMapping(value = "/war")
@Slf4j
public class WarController {

    private final static String UPLOAD_FILE_PATH = "C:\\Users\\May\\Downloads\\apache-tomcat-9.0.22-windows-x64";

    /**
     * War 包的上传
     *
     * @param file war包
     * @return null
     */
    @ApiOperation(value = "War 包的上传", notes = "War 包的上传", httpMethod = "POST")
    @PostMapping(value = "/uploadWar")
    public String uploadWar(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            Map<String, String> resObj = new HashMap<>(MAP_SIZE);
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(UPLOAD_FILE_PATH, Objects.requireNonNull(file.getOriginalFilename()))));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                resObj.put("msg", "error");
                resObj.put("code", "1");
                return JSONObject.toJSONString(resObj);
            }
            resObj.put("msg", "ok");
            resObj.put("code", "0");
            return JSONObject.toJSONString(resObj);
        } else {
            return null;
        }
    }

}
