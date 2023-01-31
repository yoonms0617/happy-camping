package com.sist.model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.annotation.Controller;
import com.sist.controller.annotation.RequestMapping;
import com.sist.dao.MemberDAO;

@Controller
public class MemberModel {

    private final MemberDAO memberDAO;

    public MemberModel() {
        this.memberDAO = new MemberDAO();
    }

    @RequestMapping("member/login.do")
    public String loginPage(HttpServletRequest request, HttpServletResponse response) {
        return "/happy/member/login.jsp";
    }

    @RequestMapping("member/signup.do")
    public String signupPage(HttpServletRequest request, HttpServletResponse response) {
        return "/happy/member/signup.jsp";
    }

    @RequestMapping("check/userid.do")
    public void checkUserId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        String userId = request.getParameter("userId");
        boolean flag = memberDAO.isDuplicateUserId(userId);
        response.getWriter().write(String.valueOf(flag));
    }

    @RequestMapping("check/email.do")
    public void checkEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        boolean flag = memberDAO.isDuplicateEmail(email);
        response.getWriter().write(String.valueOf(flag));
    }

    @RequestMapping("signup.do")
    public String signup(HttpServletRequest request, HttpServletResponse response) {
        // TODO (회원 저장)
        return "redirect:main.do";
    }

}
