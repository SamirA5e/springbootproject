package com.onlyjavatech.samir.controller.TestingController;
import com.onlyjavatech.samir.model.TestingModel.TestingRequestModel;
import com.onlyjavatech.samir.model.TestingModel.TestingResponseModel;
import com.onlyjavatech.samir.service.TestingService.TestingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testers")
public class TestingController {
    @Autowired
    private TestingService testingService;

    @PostMapping
    public TestingResponseModel registerTester(@RequestBody TestingRequestModel request) {
        return testingService.registerTester(request);
    }
    @GetMapping()
    public List<TestingResponseModel> getTesters()
    {
        return testingService.getTesters();
    }
    @GetMapping("/testersLog")
    public List<TestingResponseModel> getTestersLog()
    {
        return testingService.getTestersLog();
    }

    @GetMapping("/getTestApi")
    public String getTestApi(){return testingService.consumeApi();}

    @GetMapping("/testingException")
    public String testingException() {
        return testingService.testingException();
    }
}
