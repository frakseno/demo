package com.frakseno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    @RequestMapping("/home")
    public String getHomePage() {
        return "data";
    }

    @RequestMapping("/uiboot")
    public String getMaterialPage() {
        return "uiboot";
    }
}
