package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.order.BuyBusinessService;
import com.accenture.flowershop.be.business.order.CatalogBusinessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/catalog")
public class CatalogServlet extends HttpServlet {


    @Autowired
    private BuyBusinessService buyBusinessService;

    @Autowired
    private CatalogBusinessService catalogBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String findName = request.getParameter("findName");
        String minStr = request.getParameter("min");
        String maxStr = request.getParameter("max");
        Integer min = null;
        Integer max = null;

        String findMessage = "";
        if(!minStr.equals("")) {
            try {
                min = Integer.parseInt(minStr);
            } catch (NumberFormatException e) {
                findMessage = "Некорректное значение";
                session.setAttribute("findMessage", findMessage);
                response.sendRedirect("/catalog");
                return;
            }
        }
        if(!maxStr.equals("")){
            try {
                max = Integer.parseInt(maxStr);
            } catch (NumberFormatException e) {
                findMessage = "Некорректное значение";
                session.setAttribute("findMessage", findMessage);
                response.sendRedirect("/catalog");
                return;
            }
        }



        List<Flower> flowers = catalogBusinessService.findListFlower(findName, min, max);
        session.setAttribute("flowers", flowers);

        response.sendRedirect("/catalog");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        if (session.getAttribute("Username") == null){
            response.sendRedirect("/login");
            return;
        }

        request.setAttribute("firstName", session.getAttribute("firstName"));
        request.setAttribute("lastName", session.getAttribute("lastName"));

        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");

        List<Flower> flowers = (List<Flower>) session.getAttribute("flowers");
        if(flowers == null){
            flowers = catalogBusinessService.findAllFlower();
            session.setAttribute("flowers", flowers);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/catalog.jsp");
        dispatcher.forward(request, response);
    }
}
