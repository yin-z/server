package com.example.schedule;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.database.po.SysTimer;

public interface ScheduledService extends IService<SysTimer> {

    void start();
    void addOrEdit(SysTimer sysTimer);
    void disabled(String id);
    void enable(String id);
}
