package com.sist.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sist.controller.annotation.Controller;
import com.sist.controller.annotation.RequestMapping;
import com.sist.dao.OrderDAO;
import com.sist.util.Pagination;
import com.sist.vo.ItemVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@Controller
public class OrderModel {

    private final OrderDAO orderDAO;

    public OrderModel() {
        this.orderDAO = new OrderDAO();
    }

    @RequestMapping("order")
    public String orderItem(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:main.do";
    }

    /* 주문 페이지 */
    @RequestMapping("order/item.do")
    public String orderPage(HttpServletRequest request, HttpServletResponse response) {
        String mid = (String) request.getSession().getAttribute("mid");
        int ino = Integer.parseInt(request.getParameter("ino"));
        ItemVO itemVO = orderDAO.orderCheck(ino);
        request.setAttribute("item", itemVO);
        return "";
    }

    /* 주문 상세 */
    @RequestMapping("order/detail.do")
    public String orderDetailPage(HttpServletRequest request, HttpServletResponse response) {
        String mid = (String) request.getSession().getAttribute("mid");
        return "";
    }

    /* 주문 목록 */
    @RequestMapping("order/list.do")
    public void orderListPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String mid = (String) request.getSession().getAttribute("mid");
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Pagination orderList = orderDAO.orderList(mid, Integer.parseInt(page));
        String json = gson.toJson(orderList);
        response.getWriter().write(json);
    }

    @RequestMapping("order/list/delete.do")
    public void orderListDelete(HttpServletRequest request, HttpServletResponse response) {
        String ono = request.getParameter("ono");

    }

}
