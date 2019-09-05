package com.tgpms.web.war.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
@Controller
@RequestMapping(value = "/war")
@Slf4j
public class WarController {

    private final static String WAR_LOCATION = "C:\\Users\\May\\Downloads\\apache-tomcat-9.0.22-windows-x64";

    /**
     * war 包相关页面
     *
     * @return null
     */
    @ApiOperation(value = "war 包相关页面", notes = "war 包相关页面", httpMethod = "POST")
    @GetMapping(value = "/warPage")
    public String warPage(Model model) {
        model.addAttribute("location", WAR_LOCATION);
        return "war";
    }

    /**
     * msg页面
     *
     * @return null
     */
    @ApiOperation(value = "msg页面", notes = "msg页面", httpMethod = "POST")
    @GetMapping(value = "/msg")
    public String msg() {
        return "msg";
    }

    /**
     * War 包的上传
     *
     * @param file war包
     * @return null
     */
    @ApiOperation(value = "War 包的上传", notes = "War 包的上传", httpMethod = "POST")
    @PostMapping(value = "/uploadWar")
    @ResponseBody
    public String uploadWar(@RequestParam("file") MultipartFile file, @RequestParam("warPath") String warPath) {
        if (!file.isEmpty()) {
            Map<String, String> resObj = new HashMap<>();
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(warPath, Objects.requireNonNull(file.getOriginalFilename()))));
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
