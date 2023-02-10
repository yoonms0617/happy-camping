package com.sist.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sist.controller.annotation.Controller;
import com.sist.controller.annotation.RequestMapping;
import com.sist.dao.OrderDAO;
import com.sist.util.Pagination;
import com.sist.vo.CartVO;
import com.sist.vo.ItemVO;
import com.sist.vo.MemberVO;
import com.sist.vo.OrderItemVO;
import com.sist.vo.OrderVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@Controller
public class OrderModel {

    private final OrderDAO orderDAO;

    public OrderModel() {
        this.orderDAO = new OrderDAO();
    }

    /* 상품 주문 */
    @RequestMapping("order.do")
    public String orderItem(HttpServletRequest request, HttpServletResponse response) {
        String mid = (String) request.getSession().getAttribute("mid");
        String name = (String) request.getSession().getAttribute("name");
        String tel = (String) request.getSession().getAttribute("tel");
        String postCode = (String) request.getSession().getAttribute("postcode");
        String homeAddr = (String) request.getSession().getAttribute("homeAddr");
        String detailAddr = (String) request.getSession().getAttribute("detailAddr");
        String itemName = request.getParameter("name");
        String itemImage = request.getParameter("image");
        int cno = Integer.parseInt(request.getParameter("cno"));
        int ino = Integer.parseInt(request.getParameter("ino"));
        int itemQuantity = Integer.parseInt(request.getParameter("quantity"));
        int itemPrice = Integer.parseInt(request.getParameter("price"));
        OrderItemVO orderItemVO = new OrderItemVO();
        orderItemVO.setQuantity(itemQuantity);
        orderItemVO.setName(itemName);
        orderItemVO.setImage(itemImage);
        orderItemVO.setPrice(itemPrice);
        orderItemVO.setIno(ino);
        MemberVO memberVO = new MemberVO();
        memberVO.setMid(mid);
        memberVO.setName(name);
        memberVO.setTel(tel);
        memberVO.setPostcode(postCode);
        memberVO.setHomeAddr(homeAddr);
        memberVO.setDetailAddr(detailAddr);
        orderDAO.orderItem(memberVO, orderItemVO, cno);
        return "redirect:main.do";
    }

    /* 주문 페이지 */
    @RequestMapping("order/item.do")
    public String orderPage(HttpServletRequest request, HttpServletResponse response) {
        String mid = (String) request.getSession().getAttribute("mid");
        int cno = Integer.parseInt(request.getParameter("cno"));
        CartVO cartVO = orderDAO.orderCheck(cno);
        request.setAttribute("item", cartVO);
        return "/happy/cart/order.jsp";
    }

    /* 주문 상세 */
    @RequestMapping("order/detail.do")
    public String orderDetailPage(HttpServletRequest request, HttpServletResponse response) {
        int ono = Integer.parseInt(request.getParameter("ono"));
        OrderVO orderVO = orderDAO.orderDetail(ono);
        System.out.println("orderVO = " + orderVO);
        request.setAttribute("order", orderVO) ;
        return "/happy/cart/order_detail.jsp";
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
        orderDAO.orderListDelete(Integer.parseInt(ono));
    }

}
