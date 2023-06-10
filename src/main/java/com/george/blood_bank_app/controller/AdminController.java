package com.george.blood_bank_app.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({
        "/admin_login", "/contact_us", "/about_us"
})
public class AdminController extends HttpServlet {

    @Override
    public void init(){

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/admin_login":
                    adminLogin(request, response);
                    break;
                case "/contact_us":
                    contactUs(request, response);
                    break;
                case "/about_us":
                    aboutUs(request, response);
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/login.jsp");
                    dispatcher.forward(request, response);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/login.jsp");
        dispatcher.forward(request, response);
    }

    private void contactUs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/web/contact_us.jsp");
        dispatcher.forward(request, response);
    }

    private void aboutUs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/web/about_us.jsp");
        dispatcher.forward(request, response);
    }
}
