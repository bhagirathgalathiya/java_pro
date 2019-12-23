/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package first;

import pojos.Student;
import DAO.StudentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bhagi
 */
public class Registration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        
        String name,email,pass;
        name = req.getParameter("fname");
        email = req.getParameter("email");
        pass = req.getParameter("pswr");
        
        Student st =new Student(name,email,pass);
        StudentDAO sd = new StudentDAO();
        
    }

  
}
