package com.sist.model;

import com.sist.controller.annotation.Controller;
import com.sist.controller.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainModel {

    @RequestMapping("main.do")
    public String mainPage(HttpServletRequest request, HttpServletResponse response) {
        return "/happy/main/main.jsp";
    }

}
