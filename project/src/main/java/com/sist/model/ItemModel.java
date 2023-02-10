package com.sist.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sist.controller.annotation.Controller;
import com.sist.controller.annotation.RequestMapping;
import com.sist.dao.ItemDAO;
import com.sist.dao.ItemQnaDAO;
import com.sist.dao.ItemQnaReDAO;
import com.sist.dao.ItemReviewDAO;
import com.sist.util.Pagination;
import com.sist.vo.ItemQAReplyVO;
import com.sist.vo.ItemQAVO;
import com.sist.vo.ItemVO;

@Controller
public class ItemModel {
	private ItemReviewDAO itemReviewDAO = new ItemReviewDAO();

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
										 


		String page = request.getParameter("page");
		if (page == null)
			page = "1";
		int curpage = Integer.parseInt(page);

		ItemQnaDAO idao = new ItemQnaDAO();
		List<ItemQAVO> qalist = idao.itemQnaList(curpage);
		int totalpage = idao.itemQnATotalPage();
		final int BLOCK = 10;
		int temp = (int) Math.floor((curpage - 1) / BLOCK);
		int startPage = (BLOCK * temp) + 1;
		int endPage = startPage + (BLOCK - 1);
		if (endPage > totalpage)
			endPage = totalpage;
		
  
		request.setAttribute("ino", ino);
		request.setAttribute("qalist", qalist);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return "/happy/item/item_detail.jsp";
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

		itemReviewDAO.writeItemReview(mid, content, ino);

	}

	@RequestMapping("item/review/update.do")
	public void ItemReviewUpdate(HttpServletRequest request, HttpServletResponse response) {
		String content = request.getParameter("content");
		int rno = Integer.parseInt(request.getParameter("rno"));
		itemReviewDAO.updateItemReview(content, rno);
								  
	}

	@RequestMapping("item/review/delete.do")
	public void ItemReviewDelete(HttpServletRequest request, HttpServletResponse response) {
		int rno = Integer.parseInt(request.getParameter("rno"));
		itemReviewDAO.deleteItemReview(rno);
	}


	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////

	// 아이템 qna //

	@RequestMapping("item/item_qna_list.do")

	public String itme_qna_list(HttpServletRequest request, HttpServletResponse response) {

		String page = request.getParameter("page");
		if (page == null)
			page = "1";
		int curpage = Integer.parseInt(page);

		ItemQnaDAO dao = new ItemQnaDAO();
		List<ItemQAVO> qalist = dao.itemQnaList(curpage);
		int totalpage = dao.itemQnATotalPage();
		final int BLOCK = 10;
		int temp = (int) Math.floor((curpage - 1) / BLOCK);
		int startPage = (BLOCK * temp) + 1;
		int endPage = startPage + (BLOCK - 1);
		if (endPage > totalpage)
			endPage = totalpage;

		int ino=Integer.parseInt(request.getParameter("ino"));
								 
		request.setAttribute("ino", ino);
		request.setAttribute("qalist", qalist);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return "/happy/item/item_qna_list.jsp";
	}

	@RequestMapping("item/item_qna_insert.do")
	public String item_qna_insert(HttpServletRequest request, HttpServletResponse response) {
        int ino=Integer.parseInt(request.getParameter("ino"));

        request.setAttribute("ino", ino);
		return "/happy/item/item_qna_insert.jsp";
	}

	@RequestMapping("item/item_qna_insert_ok.do")
	public String item_qna_insert_ok(HttpServletRequest request, HttpServletResponse response) {

		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception ex) {
		}
		HttpSession session = request.getSession();
		String mid = (String)session.getAttribute("mid");

		int ino = Integer.parseInt(request.getParameter("ino"));
									  
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String password = request.getParameter("password");
		ItemQAVO vo = new ItemQAVO();


		vo.setMid(mid);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setPassword(password);
		vo.setIno(ino);
		ItemQnaDAO dao = new ItemQnaDAO();
		dao.itemQnAInsert(vo);

		return "redirect:item_qna_list.do?ino="+ino;
	}

	@RequestMapping("item/item_qna_detail.do")
	public String item_qna_detail(HttpServletRequest request, HttpServletResponse response) {
		String qano = request.getParameter("qano");
		String dbday = request.getParameter("dbday");
		ItemQnaDAO dao = new ItemQnaDAO();
		ItemQAVO vo = dao.itemQnADetail(Integer.parseInt(qano));
		request.setAttribute("vo", vo);
		request.setAttribute("qano", qano);



		// 댓글 보내기
			String qarno = request.getParameter("qano");
			ItemQnaReDAO rdao = new ItemQnaReDAO();
			List<ItemQAReplyVO> list = rdao.IqReplyList(Integer.parseInt(qarno));
			request.setAttribute("list", list);
			request.setAttribute("count", list.size());


		return "/happy/item/item_qna_detail.jsp?qano="+qano;
	}

	@RequestMapping("item/item_qna_update.do")
	public String item_qna_update(HttpServletRequest request, HttpServletResponse response) {
		int qano = Integer.parseInt(request.getParameter("qano"));


		ItemQnaDAO dao = new ItemQnaDAO();
		ItemQAVO qvo = dao.item_qna_updateData(qano);
		String mid = qvo.getMid();

		request.setAttribute("qvo", qvo);
		request.setAttribute("qano", qano);
		return "/happy/item/item_qna_update.jsp";
	}


	@RequestMapping("item/item_qna_update_ok.do")
	public String item_qna_update_ok(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {}
		String qano = request.getParameter("qano");
		String mid = request.getParameter("mid");
		int ino = Integer.parseInt(request.getParameter("ino"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String password = request.getParameter("password");

		ItemQAVO qvo = new ItemQAVO();
		qvo.setQano(Integer.parseInt(qano));
		qvo.setMid(mid);
		qvo.setTitle(title);
		qvo.setContent(content);
		qvo.setPassword(password);
		qvo.setIno(ino);

		ItemQnaDAO dao = new ItemQnaDAO();
		boolean bCheck = dao.item_qna_update(qvo);
		if(bCheck) {
			request.setAttribute("res", "yes");
		}else {
			request.setAttribute("res", "no");
		}

		return "redirect:item_qna_detail.do?qano="+qano;

	}




	@RequestMapping("item/item_qna_delete.do")
	public void item_qna_delete(HttpServletRequest request, HttpServletResponse response) {

		String ino = request.getParameter("ino");
		String qano = request.getParameter("qano");
		String pwd = request.getParameter("pwd");

		ItemQnaDAO dao = new ItemQnaDAO();


		boolean bCheck = dao.item_Qna_Delete(Integer.parseInt(qano), pwd);
		String s="";
		if(bCheck) {
			s="yes";
		}else {
			s="no";
		}
		try
		{
			PrintWriter out=response.getWriter();
			out.println(s+","+ino);
		}catch(Exception ex) {}
		}

	@RequestMapping("item/item_qna_reply_insert.do")
	public String item_qna_reply_insert(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {}
		String qano = request.getParameter("qano");
		String ino = request.getParameter("ino");
		String content = request.getParameter("content");
		String mid = request.getParameter("mid");
		String dbday = request.getParameter("dbday");
		ItemQAReplyVO vo = new ItemQAReplyVO();
		vo.setQano(Integer.parseInt(qano));
		vo.setContent(content);
		vo.setMid(mid);
		vo.setDbday(dbday);

		ItemQnaReDAO dao = new ItemQnaReDAO();
		dao.IqReplyInsert(vo);

		return "redirect:item_qna_detail.do?qano="+qano;
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
	public String itemSearchDetailPage(HttpServletRequest request, HttpServletResponse response) {
		int ino = Integer.parseInt(request.getParameter("ino"));
		ItemDAO dao = new ItemDAO();
		ItemVO vo = dao.itemDetailData(ino);
		String description = vo.getDescription();
		String[] descript = description.split(",");

		request.setAttribute("descript", descript);
		request.setAttribute("vo", vo);
		return "/happy/item/item_detail.jsp";
	}
	// 최근 본 상품 출력하는 부분 (메인페이지 퀵메뉴)
	@RequestMapping("item/item_before_detail.do")
	public String itemDetail(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		String mid=(String)session.getAttribute("mid");
		String user="";
	    String ino=request.getParameter("ino");
		try
		{
		    Cookie cookie =new Cookie("item"+ino, ino);
		    cookie.setPath("/");
		    cookie.setMaxAge(60*60*24);
		    response.addCookie(cookie);
		}catch (Exception e) {}
  
		return "redirect:../item/item_detail.do?ino="+Integer.parseInt(ino);
	}

}