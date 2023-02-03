package com.sist.model;

import com.sist.controller.annotation.Controller;
import com.sist.controller.annotation.RequestMapping;
import com.sist.dao.MemberDAO;
import com.sist.vo.MemberVO;

import oracle.net.ns.SessionAtts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class MemberModel {

    private final MemberDAO memberDAO;

    public MemberModel() {
        this.memberDAO = new MemberDAO();
    }
    @RequestMapping("member/login1.do")
    public String loginPage(HttpServletRequest request, HttpServletResponse response)
    {
    	return "/happy/member/login.jsp";
    }
    	
    // 로그인창
    // 
    @RequestMapping("member/login.do") // 메세지 결과값 비교
    public String loginPage1(HttpServletRequest request, HttpServletResponse response) 
    {
    	try {
			request.setCharacterEncoding("utf-8");
		} catch (Exception e) {}
    	String mid =request.getParameter("userid");
		String password=request.getParameter("password");
		MemberDAO dao= new MemberDAO();
		// 결과값
		MemberVO vo =dao.memberLogin(mid, password);
		if(vo.getMsg().equals("OK"))// 로그인
		{
			// session에 저장
			// session에 생성 
			HttpSession session =request.getSession();
			session.setAttribute("mid", vo.getMid());
			session.setAttribute("name", vo.getName());
			session.setAttribute("role", vo.getRole());
		}

		request.setAttribute("result", vo.getMsg());
    	return "/happy/member/login_result.jsp";
    }
    // 회원가입
    @RequestMapping("member/signup.do")
    public String signupPage(HttpServletRequest request, HttpServletResponse response) {
        return "/happy/member/signup.jsp";
    }
    
    @RequestMapping("check/userid.do")
    public String checkUserId(HttpServletRequest request, HttpServletResponse response){
    	String userId =request.getParameter("userId");
    	MemberDAO dao = new MemberDAO();
    	boolean result =dao.memberIdCheck(userId);
    	request.setAttribute("result", result);
    	return "/happy/member/login_result.jsp";
    }

    @RequestMapping("check/email.do") 
    public String checkEmail(HttpServletRequest request, HttpServletResponse response) {
    	String email =request.getParameter("email");
       MemberDAO dao = new MemberDAO();
       boolean result =dao.memberEmailCheck(email);
       request.setAttribute("result", result);
       return "/happy/member/login_result.jsp";
    }
    @RequestMapping("signup.do")
    public String signup(HttpServletRequest request, HttpServletResponse response) {
        // TODO (회원 저장)
    	try {
			request.setCharacterEncoding("utf-8");
		} catch (Exception e) {}
    	String mid =request.getParameter("userId");
    	String password=request.getParameter("password");
    	String name=request.getParameter("name");
    	String email=request.getParameter("email");
    	String phone=request.getParameter("phone");
    	String birth =request.getParameter("birth");
    	String postcode=request.getParameter("postcode");
    	String homeAddr =request.getParameter("homeAddress");
    	String detailAddr =request.getParameter("detailAddress");
    	String sex=request.getParameter("sex");
    	
    	MemberVO vo = new MemberVO();
    	vo.setMid(mid);
    	vo.setPassword(password);
    	vo.setName(name);
    	vo.setEmail(email);
    	vo.setTel(phone);
    	vo.setBirth(birth);
    	vo.setPostcode(postcode);
    	vo.setHomeAddr(homeAddr);
    	vo.setDetailAddr(detailAddr);
    	vo.setSex(sex);
    	
    	MemberDAO dao =new MemberDAO();
    	dao.memberInsert(vo);
    	
        return "redirect:main.do";
    }
    // 로그아웃 (/member/logout.do)
    @RequestMapping("member/logout.do")
    public String logout(HttpServletRequest request, HttpServletResponse response) 
    {
      HttpSession session =request.getSession();
  	  session.invalidate(); // 모든 정보 해제
  	  return "redirect:../main.do";
    }
    // 아이디 찾기 페이지 이동 (member/idfind.do)
    @RequestMapping("member/idfind.do")
    public String idfindPage(HttpServletRequest request, HttpServletResponse response) 
    {
    	return "/happy/member/idfind.jsp";
    }
    // 아이디  찾기 => 전화번호로 찾기
    @RequestMapping("member/idtelfind.do")
    public void idtelfindPage(HttpServletRequest request, HttpServletResponse response) 
    {
    	String name=request.getParameter("name");
    	String tel=request.getParameter("tel");
        MemberDAO dao=new MemberDAO();
        String res=dao.memberIdtelfind(name, tel);
        try
        {
           PrintWriter out=response.getWriter();
           out.println(res);
        }catch(Exception ex){}
    }
    // 아이디  찾기 => 이메일로 찾기
    @RequestMapping("member/ideamilfind.do")
    public void idemailfindPage(HttpServletRequest request, HttpServletResponse response) 
    {
    	String name=request.getParameter("name");
    	String email=request.getParameter("email");
        MemberDAO dao=new MemberDAO();
        String res=dao.memberIdemailfind(name, email);
        try
        {
           PrintWriter out=response.getWriter();
           out.println(res);
        }catch(Exception ex){}
    }
}



















