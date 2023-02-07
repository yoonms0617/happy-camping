package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.annotation.Controller;
import com.sist.controller.annotation.RequestMapping;
import com.sist.dao.BoardDAO;
import com.sist.util.Pagination;
import com.sist.vo.BoardVO;

@Controller
public class BoardModel {

    private final BoardDAO boardDAO;

    public BoardModel() {
        this.boardDAO = new BoardDAO();
    }

    @RequestMapping("board/list.do")
    public String boardListPage(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        Pagination pagination = boardDAO.boardList(Integer.parseInt(page));
        request.setAttribute("page", pagination);
        return "/happy/board/board_list.jsp";
    }

    @RequestMapping("board/write.do")
    public String boardWritePage(HttpServletRequest request, HttpServletResponse response) {
        return "/happy/board/board_write.jsp";
    }

    @RequestMapping("board/detail.do")
    public String boardDetailPage(HttpServletRequest request, HttpServletResponse response) {
        int no = Integer.parseInt(request.getParameter("no"));
        BoardVO board = boardDAO.boardDetail(no);
        request.setAttribute("board", board);
        return "/happy/board/board_detail.jsp";
    }

    @RequestMapping("board/update.do")
    public String boardUpdatePage(HttpServletRequest request, HttpServletResponse response) {
        int no = Integer.parseInt(request.getParameter("no"));
        BoardVO board = boardDAO.boardDetail(no);
        request.setAttribute("board", board);
        return "/happy/board/board_update.jsp";
    }

    @RequestMapping("boardWrite.do")
    public void boardWrite(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String mid = (String) request.getSession().getAttribute("mid");
        boardDAO.boardWrite(title, content, mid);
    }

    @RequestMapping("boardUpdate.do")
    public void boardUpdate(HttpServletRequest request, HttpServletResponse response) {
        int no = Integer.parseInt(request.getParameter("no"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        boardDAO.boardUpdate(no, title, content);
    }

    @RequestMapping("boardDelete.do")
    public String boardDelete(HttpServletRequest request, HttpServletResponse response) {
        int no = Integer.parseInt(request.getParameter("no"));
        boardDAO.boardDelete(no);
        return "redirect:board/list.do";
    }

}
