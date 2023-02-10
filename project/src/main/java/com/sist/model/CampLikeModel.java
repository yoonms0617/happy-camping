package com.sist.model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.annotation.Controller;
import com.sist.controller.annotation.RequestMapping;
import com.sist.dao.CampLikeDAO;

@Controller
public class CampLikeModel {

    private final CampLikeDAO campLikeDAO;

    public CampLikeModel() {
        this.campLikeDAO = new CampLikeDAO();
    }

    @RequestMapping("campLike.do")
    public void campLike(HttpServletRequest request, HttpServletResponse response) {
        int cno = Integer.parseInt(request.getParameter("cno"));
        String mid = (String) request.getSession().getAttribute("mid");
        campLikeDAO.campLike(cno, mid);
    }

    @RequestMapping("campUnLike.do")
    public void campUnLike(HttpServletRequest request, HttpServletResponse response) {
        int cno = Integer.parseInt(request.getParameter("cno"));
        String mid = (String) request.getSession().getAttribute("mid");
        campLikeDAO.campUnlike(cno, mid);
    }

    @RequestMapping("isCampLike.do")
    public void isLike(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        int cno = Integer.parseInt(request.getParameter("cno"));
        String mid = (String) request.getSession().getAttribute("mid");
        if (mid == null) {
            mid = "";
        }
        String like = campLikeDAO.isLike(cno, mid);
        response.getWriter().write(like);
    }

}
