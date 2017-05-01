package cc.before30.controller;

import cc.before30.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by before30 on 01/05/2017.
 */
@RestController
public class HelloController {
    @Autowired
    private SchedulerService schedulerService;

    @GetMapping("/hello")
    public String hello() {
        return "world";
    }

    @GetMapping("/hello2")
    public String hello2() {
        schedulerService.requestToWriteWhatDidUDo();

        return "hello2";
    }

    @GetMapping("/hello3")
    public String hello3() {
        schedulerService.requestToDdulSchedulerInHeroku();

        return "hello3";
    }
}
