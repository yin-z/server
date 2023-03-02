package com.example.server.controller;

import com.example.database.po.mypo.Test1;
import com.example.server.service.Impl.Tes1ServiceImpl;
import com.example.server.service.Test1Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author yin
 * @Date 2023/3/5 11:31
 */


@RequestMapping("/test")
@RestController
public class TestController {

    @Resource
    private Test1Service test1Service;

    @GetMapping("/a")
    public List<Test1> test1List(){
        return test1Service.testAll();
    }


    @GetMapping("/b")
    public String str(){
        return "连接成功";
    }

}
