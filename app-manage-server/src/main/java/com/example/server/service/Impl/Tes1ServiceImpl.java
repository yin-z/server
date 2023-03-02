package com.example.server.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.database.mapper.mymapper.Test1Mapper;
import com.example.database.po.mypo.Test1;
import com.example.server.service.Test1Service;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author yin
 * @Date 2023/3/2 14:31
 */
@Service
public class Tes1ServiceImpl extends ServiceImpl<Test1Mapper,Test1> implements Test1Service{

    @Override
    public List<Test1> testAll() {
        return this.list();
    }

}
