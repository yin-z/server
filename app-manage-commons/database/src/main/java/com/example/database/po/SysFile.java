package com.example.database.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_file")
public class SysFile {

    @TableId
    private Long id;

    @TableField("file_name")
    private String fileName;

    @TableField("size")
    private String size;

    @TableField("url")
    private String url;

    @TableField("path")
    private String path;
}
