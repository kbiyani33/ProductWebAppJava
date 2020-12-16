package com.cisco.prj.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cisco.prj.dao.DaoException;
import com.cisco.prj.dao.ProductDao;
import com.cisco.prj.dao.ProductDaoJdbcImpl;
import com.cisco.prj.entity.Product;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {

                ProductDao productDao = new ProductDaoJdbcImpl();
                List<Product> products;
                try {
                        products = productDao.getProducts();
                        
                        double total = products.stream().map(p -> p.getPrice()).reduce(0.0, (p1,p2) -> p1+ p2);
                        request.setAttribute("products", products);
                        request.setAttribute("total", total);
                        request.getRequestDispatcher("print.jsp").forward(request, response);
                } catch (DaoException e) {
                        e.printStackTrace();
                }
                
                /*
                 * response.setContentType("text/html"); // MIME type "application/json"
                 * PrintWriter out = response.getWriter(); // opens character stream to client [ Browser ] 
                 * // ServletOutputStream out = response.getOutputStream(); ProductDao
                 * productDao = new ProductDaoJdbcImpl();
                 * out.print("<html><body>"); 
                 * List<Product> products; 
                 * try { 
                 * products = productDao.getProducts();
                 *          for(Product p : products) { 
                 *                 out.print(p.getName() + ", " + p.getPrice() + "<br />"); 
                 * } 
                 * } 
                 * catch (DaoException e) { e.printStackTrace(); }
                 * out.print("</body></html>");
                 */
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
                Product p = new Product();
                p.setName(request.getParameter("name"));
                p.setPrice(Double.parseDouble(request.getParameter("price")));
                p.setQuantity(Integer.parseInt(request.getParameter("qty")));

                ProductDao productDao = new ProductDaoJdbcImpl();

                try {
                        productDao.addProduct(p);
                } catch (DaoException e) {
                        e.printStackTrace();
                }
                response.sendRedirect("index.html");
        }

}