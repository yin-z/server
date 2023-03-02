package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.database.po.mypo.Test1;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author yin
 * @Date 2023/3/2 14:19
 */
public interface Test1Service extends IService<Test1> {
    List<Test1>  testAll();
}
