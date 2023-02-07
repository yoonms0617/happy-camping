package com.sist.model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sist.controller.annotation.Controller;
import com.sist.controller.annotation.RequestMapping;
import com.sist.dao.ItemDAO;
import com.sist.dao.ItemQnaDAO;
import com.sist.dao.ItemReviewDAO;
import com.sist.util.Pagination;
import com.sist.vo.ItemQAVO;
import com.sist.vo.ItemReviewVO;
import com.sist.vo.ItemVO;

@Controller
public class ItemModel {
	private ItemReviewDAO itemReviewDAO;

	@RequestMapping("item/list.do")
	public String listPage(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page); // 현재페이지
		String type = request.getParameter("type");
		String type1 = request.getParameter("type1");
		int totalpage = 0;
		List<ItemVO> list = null;
		ItemDAO dao = new ItemDAO();
		if (type1 == "" || type1 == null) {
			list = dao.itemCategoryList(Integer.parseInt(page), Integer.parseInt(type));
			totalpage = dao.itemTotalPage(Integer.parseInt(type));
		} else {
			list = dao.itemCategoryList2(Integer.parseInt(page), Integer.parseInt(type), Integer.parseInt(type1));
			totalpage = dao.itemTotlaPage2(Integer.parseInt(type), Integer.parseInt(type1));
		}
		final int BLOCK = 10;
		int startPage = ((curpage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curpage - 1) / BLOCK * BLOCK) + BLOCK;
		if (endPage > totalpage) {
			endPage = totalpage;
		}
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("type", type);
		request.setAttribute("type1", type1);
		String[] title = { "텐트", "타프/쉘터", "폴대/펙/스트링", "테이블/체어", "베드/침구/매트", "스토브/랜턴", "식기/쿠커", "화로/BBQ", "냉방/난방",
				"수납/케이스", "나이프/공구/위생", "배낭/디팩/모자" };
		request.setAttribute("title", title[Integer.parseInt(type)]);
		return "/happy/item/item_list.jsp";
	}
	
	@RequestMapping("item/item_detail.do")
	public String itemDetailPage(HttpServletRequest request, HttpServletResponse response) {
		int ino = Integer.parseInt(request.getParameter("ino"));
		ItemDAO dao = new ItemDAO();
		ItemVO vo = dao.itemDetailData(ino);

		String description = vo.getDescription();
		String[] descript = description.split(",");

		request.setAttribute("descript", descript);
		request.setAttribute("vo", vo);
		return "/happy/item/item_detail.jsp";
	}

	///////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping("item_review_insert.do")
	public String itemReviewInsert(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("main.jsp", "/happy/item/item_review_insert.jsp");
		return "/happy/main/main.jsp";
	}
	
	
	@RequestMapping("item_review_insert_ok.do")
	public String item_review_insert_ok(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
		}
		// 사용자가 보내준 데이터를 받는다
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd"); // <input type=password name=pwd>
		// FreeBoardVO에 묶어서 오라클에 전송
		ItemReviewVO vo = new ItemReviewVO();
		vo.setWriter(name);
		vo.setContent(content);
		vo.setSubject(subject);
		vo.setPwd(pwd);

		ItemDAO dao = new ItemDAO();

//		dao.boardInsert(vo);
		// 화면이동
		return ""; // sendRedirect("../freeboard/list.do")
	}
	
	
	
	// 아이템 리뷰~!~!
		@RequestMapping("item/review/list.do")
		public void itemReviewList(HttpServletRequest request, HttpServletResponse response) throws IOException {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/jason");
			int ino = Integer.parseInt(request.getParameter("ino"));
			String page = request.getParameter("page");
			if(page == null) {
				page = "1";
			}
			Pagination reviews = itemReviewDAO.itemReview(ino, Integer.parseInt(page));
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
			String json = gson.toJson(reviews);
			response.getWriter().write(json);

		}
		 @RequestMapping("item/review/write.do")
		    public void ItemReviewWrite(HttpServletRequest request, HttpServletResponse response) {
		        String mid = request.getParameter("mid");
		        String content = request.getParameter("content");
		        int ino = Integer.parseInt(request.getParameter("ino"));
		        System.out.println("아이템ino들어옴");
		        System.out.println("mid:"+ mid);
		        System.out.println("content:"+ content);
		        System.out.println("ino:"+ ino);

		        itemReviewDAO.writeItemReview(mid, content, ino);

		    }

	    @RequestMapping("item/review/update.do")
	    public void ItemReviewUpdate(HttpServletRequest request, HttpServletResponse response) {
	        String content = request.getParameter("content");
	        int ino = Integer.parseInt(request.getParameter("rno"));
	        itemReviewDAO.updateItemReview(content, ino);
	    }

	    @RequestMapping("item/review/delete.do")
	    public void ItemReviewDelete(HttpServletRequest request, HttpServletResponse response) {
	        int ino = Integer.parseInt(request.getParameter("rno"));
	        itemReviewDAO.deleteItemReview(ino);
	    }


		/////////////////////////////////////////////////////////////////////////////

//	    // 아이템 qna
		@RequestMapping("item/itme_qna_list.do")
		public String itme_qna_list(HttpServletRequest request, HttpServletResponse response) {
			String page = request.getParameter("page");
			if (page == null)
				page = "1";
			int curpage = Integer.parseInt(page);

			ItemDAO dao = new ItemDAO();
			List<ItemQAVO> qnlist = dao.itemQnaList(curpage);
			int totalpage = dao.itemQnATotalPage();
			final int BLOCK = 10;
			int temp = (int) Math.floor((curpage - 1) / BLOCK);
			int startPage = (BLOCK * temp) + 1;
			int endPage = startPage + (BLOCK - 1);
			if (endPage > totalpage)
				endPage = totalpage;

			request.setAttribute("qnlist", qnlist);
			request.setAttribute("curpage", curpage);
			request.setAttribute("totalpage", totalpage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			return "/happy/item/item_detail.jsp";
		}

		@RequestMapping("item/item_qna_insert.do")
		public String item_qna_insert(HttpServletRequest request, HttpServletResponse response) {
			System.out.println("인서트 잘나옴");

			return "/happy/item/item_qna_insert.jsp";
		}

		@RequestMapping("item/item_qna_insert_ok.do")
		public String item_qna_insert_ok(HttpServletRequest request, HttpServletResponse response) {
			System.out.println("인서트오케 잘나옴");
			try {
				request.setCharacterEncoding("UTF-8");
			} catch (Exception ex) {
			}
			String mid = request.getParameter("mid");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String password = request.getParameter("password");
			ItemQAVO vo = new ItemQAVO();
			// ItemVO vo =new ItemVO();
			// String ino=request.getParameter("ino");
			vo.setMid(mid);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setPassword(password);
			ItemDAO dao = new ItemDAO();
			dao.itemQnAInsert(vo);
			/* return "redirect:/happy/item/item_detail.jsp"; */
			 return "redirect:item/item_qna_list.do";
		}

		@RequestMapping("item_qna_detail.do")
		public String item_qna_detail(HttpServletRequest request, HttpServletResponse response) {
			String no = request.getParameter("no");
			ItemQnaDAO dao = new ItemQnaDAO();
			return "redirect:/happy/item/item_detail.do";
		}
	
	
	/////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////
	
 // 검색창 목록 출력
    @RequestMapping("search/list.do")
    public String searchlistPage(HttpServletRequest request, HttpServletResponse response) {
        try {
        	request.setCharacterEncoding("utf-8");
        }catch(Exception ex) {}
        String ss=request.getParameter("ss");
        String page=request.getParameter("page");
        if(page==null)
        	page="1";
        String searchType = request.getParameter("searchType");
        if (searchType == null) {
            searchType = "all";
        }
        int curpage=Integer.parseInt(page);
        ItemDAO dao=new ItemDAO();
        List<ItemVO> list=dao.itemsearchData(curpage, ss);
        int itemtotal=dao.itemsearchTotal(ss);
        int totalpage=dao.itemsearchTotalPage(ss);
        
        final int BLOCK=10;
        int temp = (int) Math.floor((curpage - 1) / BLOCK);
        int startPage=(BLOCK * temp) + 1;
        int endPage = startPage + (BLOCK - 1);
        if(endPage>totalpage)
           endPage=totalpage;
       
        request.setAttribute("searchType", searchType);
        request.setAttribute("ss", ss);
        request.setAttribute("list", list);
        request.setAttribute("curpage", curpage);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("itemtotal", itemtotal);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);      
      	return "/happy/search/search.jsp"; // 아이템 관련 파일 추가시 변경 예정
    }
    // 서치후 디테일
    @RequestMapping("search/detail.do")
    public String itemDetailPage(HttpServletRequest request, HttpServletResponse response) {
       int ino = Integer.parseInt(request.getParameter("ino"));
       ItemDAO dao = new ItemDAO();
       ItemVO vo = dao.itemDetailData(ino);
       String description = vo.getDescription();
       String[] descript = description.split(",");

       request.setAttribute("descript", descript);
       request.setAttribute("vo", vo);
       return "/happy/search/search_detail.jsp";
    }

}
