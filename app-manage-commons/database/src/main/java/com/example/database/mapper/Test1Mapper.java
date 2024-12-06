package com.example.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.database.po.Test1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author yin
 * @Date 2023/3/1 14:30
 */

@Mapper
public interface Test1Mapper extends BaseMapper<Test1> {

    List<Test1> queryAll(@Param("name")String name);

}
