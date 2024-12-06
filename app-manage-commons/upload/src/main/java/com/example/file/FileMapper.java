package com.example.file;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.database.po.SysFile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper extends BaseMapper<SysFile> {
}
