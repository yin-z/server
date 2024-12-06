package com.example.server.controller;

import com.example.admin.config.ProjectConfig;
import com.example.database.po.Test1;
import com.example.server.service.Test1Service;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测试
 * @Author yin
 * @Date 2023/3/5 11:31
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @Resource
    private Test1Service test1Service;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * a接口
     * @return
     */
    @GetMapping("/a")
    public List<Test1> test1List(String name){
        return test1Service.testAll(name);
    }

    /**
     * b接口
     * @param str 随便
     * @return
     */
    @GetMapping("/b")
    public String str(String str){
        redisTemplate.opsForValue().set("1414", "552552");
        return "连接成功:"+str;
    }


}
