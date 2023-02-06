package com.sist.model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sist.controller.annotation.Controller;
import com.sist.controller.annotation.RequestMapping;
import com.sist.dao.CampDAO;
import com.sist.dao.CampReviewDAO;
import com.sist.util.Pagination;
import com.sist.vo.CampVO;

@Controller
public class CampModel {

    private final CampDAO campDAO;
    private final CampReviewDAO campReviewDAO;

    public CampModel() {
        this.campDAO = new CampDAO();
        this.campReviewDAO = new CampReviewDAO();
    }

    @RequestMapping("camp/list.do")
    public String campList(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        String orderType = request.getParameter("orderType");
        if (orderType == null) {
            orderType = "new";
        }
        String searchType = request.getParameter("searchType");
        if (searchType == null) {
            searchType = "all";
        }
        String searchKeyword = request.getParameter("searchKeyword");
        if (searchKeyword == null) {
            searchKeyword = "";
        }
        Pagination pagination = campDAO.campList(Integer.parseInt(page), orderType, searchType, searchKeyword);
        request.setAttribute("page", pagination);
        request.setAttribute("orderType", orderType);
        request.setAttribute("searchType", searchType);
        request.setAttribute("searchKeyword", searchKeyword);
        return "/happy/camp/camp_list.jsp";
    }

    @RequestMapping("camp/detail.do")
    public String campDetail(HttpServletRequest request, HttpServletResponse response) {
        int cno = Integer.parseInt(request.getParameter("cno"));
        CampVO campVO = campDAO.campDetail(cno);
        request.setAttribute("camp", campVO);
        return "/happy/camp/camp_detail.jsp";
    }

    @RequestMapping("camp/review/list.do")
    public void campReviewList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        int cno = Integer.parseInt(request.getParameter("cno"));
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        Pagination reviews = campReviewDAO.campReview(cno, Integer.parseInt(page));
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        String json = gson.toJson(reviews);
        response.getWriter().write(json);
    }

    @RequestMapping("camp/review/write.do")
    public void campReviewWrite(HttpServletRequest request, HttpServletResponse response) {
        String mid = request.getParameter("mid");
        String content = request.getParameter("content");
        int cno = Integer.parseInt(request.getParameter("cno"));
        campReviewDAO.writeCampReview(mid, content, cno);
    }

    @RequestMapping("camp/review/update.do")
    public void campReviewUpdate(HttpServletRequest request, HttpServletResponse response) {
        String content = request.getParameter("content");
        int cno = Integer.parseInt(request.getParameter("clno"));
        campReviewDAO.updateCampReview(content, cno);
    }

    @RequestMapping("camp/review/delete.do")
    public void campReviewDelete(HttpServletRequest request, HttpServletResponse response) {
        int cno = Integer.parseInt(request.getParameter("clno"));
        campReviewDAO.deleteCampReview(cno);
    }

}
