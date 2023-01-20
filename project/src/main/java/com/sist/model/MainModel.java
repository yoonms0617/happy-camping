package com.sist.model;

import com.sist.controller.annotation.Controller;
import com.sist.controller.annotation.RequestMapping;
import com.sist.dao.CampDAO;
import com.sist.dao.ItemDAO;
import com.sist.vo.CampVO;
import com.sist.vo.ItemVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

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
        return "/happy/main/main.jsp";
    }

}
