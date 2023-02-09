package com.sist.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.annotation.Controller;
import com.sist.controller.annotation.RequestMapping;
import com.sist.dao.CampDAO;
import com.sist.dao.ItemDAO;
import com.sist.vo.CampVO;
import com.sist.vo.ItemVO;

@Controller
public class MainModel {

    private final ItemDAO itemDAO;
    private final CampDAO campDAO;

    public MainModel() {
        this.itemDAO = new ItemDAO();
        this.campDAO = new CampDAO();
    }

    @RequestMapping("main.do")
    public String mainPage(HttpServletRequest request, HttpServletResponse response) {
        List<ItemVO> hotSaleItems = itemDAO.hotSaleItem();
        List<ItemVO> newItems = itemDAO.newItemList();
        List<CampVO> campItems = campDAO.campItems();
        request.setAttribute("hotSaleItems", hotSaleItems);
        request.setAttribute("newItems", newItems);
        request.setAttribute("campItems", campItems);
        
        // 쿠키 전송
		Cookie[] cookies=request.getCookies();
		List<ItemVO> iList =new ArrayList<ItemVO>();
		HttpSession session =request.getSession();
		String mid=(String)session.getAttribute("mid");
		ItemDAO dao=new ItemDAO();
		if(cookies!=null)
		{
			for(int i=cookies.length-1;i>=0;i--)
			{
				if(cookies[i].getName().startsWith("item"))
	    		{
				String ino=cookies[i].getValue();
				System.out.println("i:"+i);
				System.out.println("ino:"+ino);
				ItemVO vo= dao.itemDetailData(Integer.parseInt(ino));
				iList.add(vo);
				System.out.println("mid"+mid);
	    		}
			}
		}
		// 최근 본 상품 (메인 페이지 퀵메뉴)
		request.setAttribute("iList", iList);
		request.setAttribute("i2List", iList);
		request.setAttribute("count", iList.size());
        return "/happy/main/main.jsp";
    }

}
