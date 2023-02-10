package com.sist.model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.annotation.Controller;
import com.sist.controller.annotation.RequestMapping;
import com.sist.dao.ItemLikeDAO;

@Controller
public class ItemLikeModel {

    private final ItemLikeDAO itemLikeDAO;

    public ItemLikeModel() {
        this.itemLikeDAO = new ItemLikeDAO();
    }

    @RequestMapping("itemLike.do")
    public void likeItem(HttpServletRequest request, HttpServletResponse response) {
        int ino = Integer.parseInt(request.getParameter("ino"));
        String mid = (String) request.getSession().getAttribute("mid");
        itemLikeDAO.itemLike(ino, mid);
    }

    @RequestMapping("itemUnLike.do")
    public void unLikeItem(HttpServletRequest request, HttpServletResponse response) {
        int ino = Integer.parseInt(request.getParameter("ino"));
        String mid = (String) request.getSession().getAttribute("mid");
        itemLikeDAO.itemUnLike(ino, mid);
    }

    @RequestMapping("isItemLike.do")
    public void isItemLike(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        int ino = Integer.parseInt(request.getParameter("ino"));
        String mid = (String) request.getSession().getAttribute("mid");
        if (mid == null) {
            mid = "";
        }
        String like = itemLikeDAO.isLike(ino, mid);
        response.getWriter().write(like);
    }

}
