package com.tgpms.web.monitor.service.impl;

import com.tgpms.web.monitor.service.CatLogsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class CatLogsServiceImplTest {

    @Resource
    private CatLogsService catLogsService;

    @Test
    public void catCatalina() {
        System.out.println("========tetete");
    }

    @Test
    public void catHostManager() {
    }

    @Test
    public void catLocalhost() {
    }

    @Test
    public void catLocalhostAccessLog() {
    }

    @Test
    public void catManager() {
    }

    @Test
    public void findAll() {
        log.info("\n --> " +
                catLogsService.findAll("C:\\Users\\May\\Downloads\\apache-tomcat-9.0.22-windows-x64\\apache-tomcat-9.0.22\\logs"));
    }
}