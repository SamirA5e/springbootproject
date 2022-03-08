package com.onlyjavatech.samir;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

@ResponseBody
public class WelcomeController {
//    @RequestMapping("/test")
//    public String firstHandler(){
//        return "Just for Testing....";
//    }
//    @RequestMapping("/Home")
//    public String homeHandler(){
//        return "Home Page !!";
//    }

    @RequestMapping("/home")
    public String home(){
        System.out.println("this is my home page");
        return "home";
    }
}
