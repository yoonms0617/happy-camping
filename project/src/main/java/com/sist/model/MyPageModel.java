package com.sist.model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sist.controller.annotation.Controller;
import com.sist.controller.annotation.RequestMapping;
import com.sist.dao.CampLikeDAO;
import com.sist.dao.ItemLikeDAO;
import com.sist.dao.MyPageDAO;
import com.sist.util.Pagination;
import com.sist.vo.MemberVO;

@Controller
public class MyPageModel {

    private final MyPageDAO myPageDAO;
    private final CampLikeDAO campLikeDAO;
    private final ItemLikeDAO itemLikeDAO;

    public MyPageModel() {
        this.myPageDAO = new MyPageDAO();
        this.campLikeDAO = new CampLikeDAO();
        this.itemLikeDAO = new ItemLikeDAO();
    }

    @RequestMapping(value = "mypage.do")
    public String myPageHome(HttpServletRequest request, HttpServletResponse response) {
        return "/happy/member/mypage/home.jsp";
    }

    @RequestMapping(value = "mypage/info.do")
    public String myPageModifyMemberInfo(HttpServletRequest request, HttpServletResponse response) {
        String mid = (String) request.getSession().getAttribute("mid");
        MemberVO member = myPageDAO.loginMember(mid);
        request.setAttribute("member", member);
        return "/happy/member/mypage/modify.jsp";
    }

    @RequestMapping(value = "mypage/address.do")
    public String myPageMemberAddress(HttpServletRequest request, HttpServletResponse response) {
        String mid = (String) request.getSession().getAttribute("mid");
        MemberVO memberAddress = myPageDAO.loginMemberAddress(mid);
        request.setAttribute("address", memberAddress);
        return "/happy/member/mypage/address.jsp";
    }

    @RequestMapping(value = "mypage/review.do")
    public String myPageReview(HttpServletRequest request, HttpServletResponse response) {
        return "/happy/member/mypage/review.jsp";
    }

    @RequestMapping(value = "mypage/post.do")
    public String myPageBoard(HttpServletRequest request, HttpServletResponse response) {
        return "/happy/member/mypage/board.jsp";
    }

    @RequestMapping(value = "mypage/like.do")
    public String myPageLike(HttpServletRequest request, HttpServletResponse response) {
        return "/happy/member/mypage/like.jsp";
    }

    @RequestMapping(value = "campReviewList.do")
    public void campReviewList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String mid = (String) request.getSession().getAttribute("mid");
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        Pagination reviews = myPageDAO.campReviewList(mid, Integer.parseInt(page));
        String json = gson.toJson(reviews);
        response.getWriter().write(json);
    }

    @RequestMapping(value = "itemReviewList.do")
    public void itemReviewList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String mid = (String) request.getSession().getAttribute("mid");
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        Pagination reviews = myPageDAO.itemReviewList(mid, Integer.parseInt(page));
        String json = gson.toJson(reviews);
        response.getWriter().write(json);
    }

    @RequestMapping(value = "campLikeList.do")
    public void campLikeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String mid = (String) request.getSession().getAttribute("mid");
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        Pagination likeList = campLikeDAO.campLikeList(mid, Integer.parseInt(page));
        String json = gson.toJson(likeList);
        response.getWriter().write(json);
    }

    @RequestMapping(value = "itemLikeList.do")
    public void itemLikeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String mid = (String) request.getSession().getAttribute("mid");
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        Pagination likeList = itemLikeDAO.itemLikeList(mid, Integer.parseInt(page));
        String json = gson.toJson(likeList);
        response.getWriter().write(json);
    }

    @RequestMapping(value = "boardList.do")
    public void boardList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String mid = (String) request.getSession().getAttribute("mid");
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        Pagination reviews = myPageDAO.boardList(mid, Integer.parseInt(page));
        String json = gson.toJson(reviews);
        response.getWriter().write(json);
    }

    @RequestMapping(value = "changePassword.do")
    public void myPageChangePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String mid = (String) request.getSession().getAttribute("mid");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String result = myPageDAO.changePassword(mid, oldPassword, newPassword);
        response.getWriter().write(result);
    }

    @RequestMapping(value = "changeAddress.do")
    public void myPageChangeAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String mid = (String) request.getSession().getAttribute("mid");
        String postCode = request.getParameter("postCode");
        String homeAddress = request.getParameter("homeAddress");
        String detailAddress = request.getParameter("detailAddress");
        myPageDAO.changeAddress(mid, postCode, homeAddress, detailAddress);
    }


    @RequestMapping(value = "out.do")
    public void myPageDeleteAccount(HttpServletRequest request, HttpServletResponse response) {
        String mid = (String) request.getSession().getAttribute("mid");
        myPageDAO.deleteAccount(mid);
    }

}
