package com.sist.model;

import com.sist.controller.annotation.Controller;
import com.sist.controller.annotation.RequestMapping;
import com.sist.dao.CampDAO;
import com.sist.vo.CampVO;
import com.sist.util.Pagination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CampModel {

    private final CampDAO campDAO;

    public CampModel() {
        this.campDAO = new CampDAO();
    }

    @RequestMapping("camp_list.do")
    public String campList(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        Pagination pagination = campDAO.campList(Integer.parseInt(page));
        request.setAttribute("page", pagination);
        return "/happy/camp/camp_list.jsp";
    }

    @RequestMapping("camp_detail.do")
    public String campDetail(HttpServletRequest request, HttpServletResponse response) {
        int cno = Integer.parseInt(request.getParameter("cno"));
        CampVO campVO = campDAO.campDetail(cno);
        request.setAttribute("camp", campVO);
        return "/happy/camp/camp_detail.jsp";
    }

}
