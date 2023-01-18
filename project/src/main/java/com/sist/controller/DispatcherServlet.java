package com.sist.controller;

import com.sist.controller.annotation.Controller;
import com.sist.controller.annotation.RequestMapping;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;
import java.io.IOException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

    private List<String> models = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        initModel(config);
    }

    private void initModel(ServletConfig config) {
        ServletContext servletContext = config.getServletContext();
        String path = servletContext.getRealPath("WEB-INF") + File.separator + "application.xml";
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            NodeList bean = builder.parse(path).getElementsByTagName("bean");
            for (int i = 0; i < bean.getLength(); i++) {
                String model = ((Element) bean.item(i)).getAttribute("class");
                models.add(model);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI().substring(request.getContextPath().length() + 1);
        System.out.println("requestURI = " + requestURI);
        try {
            for (String model : models) {
                Class<?> clazz = Class.forName(model);
                Object object = clazz.getDeclaredConstructor().newInstance();
                if (!clazz.isAnnotationPresent(Controller.class)) {
                    continue;
                }
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                    if (requestMapping.value().equals(requestURI)) {
                        String view = (String) method.invoke(object, request, response);
                        System.out.println("view = " + view);
                        if (view.startsWith("redirect:")) {
                            response.sendRedirect(view.substring(view.indexOf(":")) + 1);
                        } else {
                            RequestDispatcher requestDispatcher = request.getRequestDispatcher(view);
                            requestDispatcher.forward(request, response);
                        }
                        return;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
