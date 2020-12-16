package com.cisco.prj.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
        private static final long serialVersionUID = 1L;
        private static HashMap<String, String> users = new HashMap<String, String>();
        static {
                users.put("Gavin", "King");
                users.put("Rod","Laver");
                users.put("Raj", "Kumar");
        }
        
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String uri = request.getRequestURI();
                if(uri.endsWith("login.do")) {
                        String username = request.getParameter("username");
                        String pwd = request.getParameter("password");
                        boolean valid = false;
                        if(users.containsKey(username)) {
                                if(pwd.equals(users.get(username))) {
                                        valid = true;
                                }
                        }
                        if(valid) {
                                HttpSession session = request.getSession();
                                session.setAttribute("user", username);
                                response.sendRedirect("index.html");
                        } else {
                                response.sendRedirect("login.jsp?msg=invalid user/pwd");
                        }
                } else if(uri.endsWith("getProducts.do")) {
                        
                } else if (uri.endsWith("addProducts.do")) {
                        
                } else if(uri.endsWith("logout.do")) {
                        HttpSession session = request.getSession();
                        session.invalidate();
                        response.sendRedirect("login.jsp");
                }
                
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                doGet(request, response);
        }

}
