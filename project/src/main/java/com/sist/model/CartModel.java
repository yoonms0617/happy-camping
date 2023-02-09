package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;

import com.sist.controller.annotation.Controller;
import com.sist.controller.annotation.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.CartVO;

@Controller
public class CartModel {

    private final CartDAO cartDAO;

    public CartModel() {
        this.cartDAO = new CartDAO();
    }

    // 장바구니 페이지 이동 (/cart/cart.do)
    @RequestMapping("cart/cart.do")
    public String cartList(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("mid");
        List<CartVO> list = cartDAO.goodsCartListData(id);

        int total_price = 0;
        if (list.size() > 0) {
            for (CartVO cart : list) {
                int price = cart.getPrice();
                int quantity = cart.getQuantity();
                total_price += (price * quantity);
            }
            request.setAttribute("count", list.size());
            request.setAttribute("list", list);
            request.setAttribute("total_price", total_price);
        } else {
            request.setAttribute("count", 0);
        }

        return "/happy/cart/cart.jsp";
    }

    // 장바구니에 담기
    @RequestMapping("cart/cart_insert.do")
    public String cartItem(HttpServletRequest request, HttpServletResponse response) {
        String ino = request.getParameter("ino");
        String image = request.getParameter("image");
        String name = request.getParameter("name");
        String quantity = request.getParameter("quantity");
        String price = request.getParameter("price");
        HttpSession session = request.getSession();
        String mid = (String) session.getAttribute("mid");
        CartVO vo = new CartVO();
        vo.setImage(image);
        vo.setName(name);
        vo.setPrice(Integer.parseInt(price));
        vo.setQuantity(Integer.parseInt(quantity));
        vo.setIno(Integer.parseInt(ino));
        vo.setMid(mid);
        // DAO 연동
        cartDAO.goodsCartInsert(vo);
        return "redirect:cart.do";
    }

    // 장바구니 선택 삭제
    @RequestMapping("cart/cart_cancel.do")
    public String cartSelectDelete(HttpServletRequest request, HttpServletResponse response) {
        String cno = request.getParameter("cno");
        cartDAO.goodsCartSelectDelete(Integer.parseInt(cno));
        return "redirect:cart.do";
    }

    // 장바구니 전체 삭제
    @RequestMapping("cart/cart_cancel_all.do")
    public String cartAllDelete(HttpServletRequest request, HttpServletResponse response) {
        cartDAO.goodsCartAllDelete();
        return "redirect:cart.do";
    }

    // 장바구니 수량 증가
    @RequestMapping("cart/cart_update_up.do")
    public String cartUpdateUp(HttpServletRequest request, HttpServletResponse response) {
        String cno = request.getParameter("cno");
        cartDAO.goodsCartUpdateUp(Integer.parseInt(cno));
        return "redirect:cart.do";
    }

    // 장바구니 수량 감소
    @RequestMapping("cart/cart_update_down.do")
    public String cartUpdateDown(HttpServletRequest request, HttpServletResponse response) {
        String cno = request.getParameter("cno");
        cartDAO.goodsCartUpdateDown(Integer.parseInt(cno));
        return "redirect:cart.do";
    }
}







