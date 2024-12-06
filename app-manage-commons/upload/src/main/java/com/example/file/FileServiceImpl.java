package com.example.file;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.database.po.SysFile;
import org.springframework.stereotype.Service;


@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, SysFile> implements FileService{

}
