package com.sist.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.annotation.Controller;
import com.sist.controller.annotation.RequestMapping;
import com.sist.dao.ItemDAO;
import com.sist.vo.ItemVO;

@Controller
public class ItemModel {
	@RequestMapping("list.do")
	public String listPage(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		System.out.println("page : "+page);
		if(page == null)
			page = "1";
		int curpage = Integer.parseInt(page); // 현재페이지
		String type = request.getParameter("type");
		System.out.println("type : "+type);
		String type1 = request.getParameter("type1");
		
		System.out.println("type1 : "+type1);
		int totalpage = 0;
		List<ItemVO> list = null;
		
		// 오라클 연결
		ItemDAO dao = new ItemDAO();
		//데이터 읽기
		if(type1 == "" || type1 == null) {
			//System.out.println("null들엄옴");
			list = dao.itemCategoryList(Integer.parseInt(page), Integer.parseInt(type));
			totalpage = dao.itemTotlaPage(Integer.parseInt(type));
		}
		else {
			//System.out.println("else들어옴");
			list = dao.itemCategoryList2(Integer.parseInt(page), Integer.parseInt(type), Integer.parseInt(type1));
			totalpage = dao.itemTotlaPage2(Integer.parseInt(type), Integer.parseInt(type1));
		}
		

		final int BLOCK=10;
        int startPage=((curpage-1)/BLOCK*BLOCK)+1;
        int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
        if(endPage>totalpage)
         endPage=totalpage;

        // 화면에 출력할 모든 데이터를 JSP로 전송
        // 페이지 나누고 목록 출력...........
        request.setAttribute("list", list);
        request.setAttribute("curpage", curpage);
        request.setAttribute("totalpage", totalpage);
        
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("type", type);
        request.setAttribute("type1", type1);
        String[] title = {"텐트","타프/쉘터","폴대/펙/스트링","테이블/체어","베드/침구/매트",
        				"스토브/랜턴","식기/쿠커","화로/BBQ","냉방/난방","수납/케이스",
        				"나이프/공구/위생","배낭/디팩/모자"};
        request.setAttribute("title", title[Integer.parseInt(type)]);


		return "/happy/item/item_list.jsp";
	}

}
