package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.user.AdminBusinessService;
import com.accenture.flowershop.be.entity.order.OrderCustomer;
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

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {


    @Autowired
    private AdminBusinessService adminBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String strExit = request.getParameter("clickExit");
        Integer statuse = Integer.parseInt(request.getParameter("editStatuse"));
        Integer orderId = Integer.parseInt(request.getParameter("OrderId"));



        if (statuse == 2) {
            adminBusinessService.editStatuse(orderId);
        }

        if ("exit".equals(strExit)) {
            HttpSession session = request.getSession();
            session = request.getSession();
            session.invalidate();
            response.sendRedirect("/login");
            return;
        }
        response.sendRedirect("/admin");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<OrderCustomer> listOrderCustomer = adminBusinessService.getOrderList();
        request.setAttribute("listOrderCustomer", listOrderCustomer);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.jsp");
        dispatcher.forward(request, response);
    }
}
