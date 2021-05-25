package com.zw.javaapi.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Test")
public class TestController {
    @RequestMapping("/ceshi")
    public String shouye(Model model){
        try {

        }catch (Exception e){

        }
        return "aaaaa";
    }
}
