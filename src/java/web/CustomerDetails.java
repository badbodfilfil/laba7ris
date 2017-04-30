package web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.CustomerBeanLocal;
import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/CustomerDetails"})
public class CustomerDetails extends HttpServlet {

    CustomerBeanLocal customerBean = lookupCustomerBeanLocal();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet CustomerDetails</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Search Customer Information</h1>");
        String customerNr = request.getParameter("customer_nr");
        if((customerNr != null) && !(customerNr.equals(""))){
          Customer customer = customerBean.get(new Integer(customerNr));
            if(customer != null){
                out.println("Customer's info for nr. " + customerNr + ":<br/> State: " + customer.getState()+ "<br/>CreditLimit: " + customer.getCreditLimit());
            }else{
                out.println("Customer not found.");
            }
        }
        out.println("<form>");
        out.println("Customer number: <input type='text' name='customer_nr'/>");
        out.println("<input type=submit value=Select />");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private CustomerBeanLocal lookupCustomerBeanLocal() {
        try {
            Context c = new InitialContext();
            return (CustomerBeanLocal) c.lookup("java:global/WebApplication1/CustomerBean!ejb.CustomerBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
