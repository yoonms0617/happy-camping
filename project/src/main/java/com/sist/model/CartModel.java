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
	public String cartList(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("mid");
		CartDAO dao = new CartDAO();
		// DAO 연동
		List<CartVO> list = dao.goodsCartListData(id);
		
		if(list.size()>0)
		{
			request.setAttribute("count", list.size());
			request.setAttribute("list", list);
		}
		else
			request.setAttribute("count", 0);
		
		request.setAttribute("main.jsp", "/happy/cart/cart.jsp");
		return "/happy/cart/cart.jsp";
	}
    
    // 장바구니에 담기
    @RequestMapping("cart/cart_insert.do")
    public String cartItem(HttpServletRequest request, HttpServletResponse response)
    {
    	String iNo = request.getParameter("cIno");
    	String cAccount = request.getParameter("cAccount");
    	String cPrice = request.getParameter("cPrice");
    	String cSale = request.getParameter("cSale");
    	HttpSession session = request.getSession();
    	String mid = (String)session.getAttribute("mid");
    	CartVO vo = new CartVO();
    	vo.setPrice(Integer.parseInt(cPrice));
    	vo.setQuantity(Integer.parseInt(cAccount));
    	vo.setIno(Integer.parseInt(iNo));
    	vo.setMid(mid);
    	// DAO 연동
    	CartDAO dao = new CartDAO();
    	dao.goodsCartInsert(vo);
    	return "redirect:cart.do";
    }
    
    // 장바구니 선택 삭제
    @RequestMapping("cart/cart_cancel.do")
    public String cartSelectDelete(HttpServletRequest request, HttpServletResponse response)
    {
    	String cno=request.getParameter("cno");
    	CartDAO dao = new CartDAO();
    	dao.goodsCartSelectDelete(Integer.parseInt(cno));
    	return "redirect:cart.do";
    }
    
    /*
    @RequestMapping("goods/cart_cancel.do")
    public String goods_cart_cancel(HttpServletRequest request, HttpServletResponse response)
    {
   		String bno=request.getParameter("bno");
   		CartDAO dao = new CartDAO();
   		dao.goodsCartDelete(Integer.parseInt(bno));
   		return "redirect:cart_list.do";
    }
    */
    
    // 장바구니 전체 삭제
    @RequestMapping("cart/cart_cancel_all.do")
    public String cartAllDelete(HttpServletRequest request, HttpServletResponse response)
    {
    	CartDAO dao = new CartDAO();
    	dao.goodsCartAllDelete();
    	return "redirect:cart.do";
    }
    
}
