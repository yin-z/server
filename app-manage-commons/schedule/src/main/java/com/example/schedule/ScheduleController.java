package com.example.schedule;

import com.example.database.po.SysTimer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@RestController
@RequestMapping("/scheduled")
public class ScheduleController {

   @Resource
    private ScheduledService service;

    @PostConstruct
    @PostMapping("/start")
    public void start(){
        service.start();
    }

    @PostMapping("/add-or-edit")
    public void addOrEdit(SysTimer sysTimer){
        service.addOrEdit(sysTimer);
    }

    @PutMapping("/disabled/{id}")
    public void disabled(@PathVariable String id){
        service.disabled(id);
    }

    @PutMapping("/enable/{id}")
    public void enable(@PathVariable String id){
        service.enable(id);
    }
}
