package com.example.message.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping("test/123")
    @ResponseBody
    public String test(){
        return "hello world!";
    }

}