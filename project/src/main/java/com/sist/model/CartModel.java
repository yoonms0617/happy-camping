package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.annotation.Controller;
import com.sist.controller.annotation.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.CartVO;

@Controller
public class CartModel {
	
//	private final CartDAO cartDAO;
//
//    public CartModel() {
//        this.cartDAO = new CartDAO();
//    }
    
	// 장바구니 페이지 이동 (/cart/cart.do)
    @RequestMapping("cart/cart.do")
	public String cartList(HttpServletRequest request, HttpServletResponse response)
	{
//		HttpSession session = request.getSession();
//		String id=(String)session.getAttribute("mid");
//		CartDAO dao = new CartDAO();
		// DAO 연동
//		List<CartVO> list = dao.
//		if(list.size()>0)
//		{
//			
//		}
		
		return "/happy/cart/cart.jsp";
	}
    
    // 장바구니에 담기
    @RequestMapping("cart/cart_insert.do")
    public String cartItem(HttpServletRequest request, HttpServletResponse response)
    {
    	String iNo = request.getParameter("cNo");
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
    
    
}
