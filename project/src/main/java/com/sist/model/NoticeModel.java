package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.annotation.Controller;
import com.sist.controller.annotation.RequestMapping;
import com.sist.dao.NoticeDAO;
import com.sist.util.Pagination;
import com.sist.vo.NoticeVO;

@Controller
public class NoticeModel {

    private final NoticeDAO noticeDAO;

    public NoticeModel() {
        this.noticeDAO = new NoticeDAO();
    }

    @RequestMapping("notice/list.do")
    public String noticeListPage(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        Pagination pagination = noticeDAO.noticeList(Integer.parseInt(page));
        request.setAttribute("page", pagination);
        return "/happy/notice/notice_list.jsp";
    }

    @RequestMapping("notice/write.do")
    public String noticeWritePage(HttpServletRequest request, HttpServletResponse response) {
        return "/happy/notice/notice_write.jsp";
    }

    @RequestMapping("notice/detail.do")
    public String noticeDetailPage(HttpServletRequest request, HttpServletResponse response) {
        int no = Integer.parseInt(request.getParameter("no"));
        NoticeVO notice = noticeDAO.noticeDetail(no);
        request.setAttribute("notice", notice);
        return "/happy/notice/notice_detail.jsp";
    }

    @RequestMapping("notice/update.do")
    public String noticeUpdatePage(HttpServletRequest request, HttpServletResponse response) {
        int no = Integer.parseInt(request.getParameter("no"));
        NoticeVO notice = noticeDAO.noticeDetail(no);
        request.setAttribute("notice", notice);
        return "/happy/notice/notice_update.jsp";
    }

    @RequestMapping("noticeWrite.do")
    public void noticeWrite(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        noticeDAO.noticeWrite(title, content);
    }

    @RequestMapping("noticeUpdate.do")
    public void noticeUpdate(HttpServletRequest request, HttpServletResponse response) {
        int no = Integer.parseInt(request.getParameter("no"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        noticeDAO.noticeUpdate(no, title, content);
    }

    @RequestMapping("noticeDelete.do")
    public String noticeDelete(HttpServletRequest request, HttpServletResponse response) {
        int no = Integer.parseInt(request.getParameter("no"));
        noticeDAO.noticeDelete(no);
        return "redirect:notice/list.do";
    }

}
