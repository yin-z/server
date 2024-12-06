package com.example.server.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.database.mapper.Test1Mapper;
import com.example.database.po.Test1;
import com.example.server.service.Test1Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author yin
 * @Date 2023/3/2 14:31
 */
@Service
public class Tes1ServiceImpl extends ServiceImpl<Test1Mapper,Test1> implements Test1Service{

    @Resource
    private Test1Mapper test1Mapper;

    @Override
    public List<Test1> testAll(String name) {
        List<Test1> test1s = test1Mapper.queryAll(name);
        return test1s;
    }

}
