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
        String[] title = {"텐트", "타프/쉘터", "폴대/펙/스트링", "테이블/체어", "베드/침구/매트",
                "스토브/랜턴", "식기/쿠커", "화로/BBQ", "냉방/난방", "수납/케이스",
                "나이프/공구/위생", "배낭/디팩/모자"};
        request.setAttribute("title", title[Integer.parseInt(type)]);
        return "/happy/item/item_list.jsp";
    }

}
