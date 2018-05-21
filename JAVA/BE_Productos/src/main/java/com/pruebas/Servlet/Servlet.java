package com.pruebas.Servlet;

import com.pruebas.be_productos.Control.Control;
import com.pruebas.be_productos.entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param req servlet request
     * @param res servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/json;charset=UTF-8");
        try (PrintWriter out = res.getWriter()) {
            String accion = req.getParameter("action");
            String val1,val2,val3,val4;
            Integer int1,int2,int3;
            float float1;
            Control control = new Control();
            switch(accion){
                case "login":
                    val1 = req.getParameter("usuario");
                    val2 = req.getParameter("contrasenna");
                    val3 = control.login(val1, val2);
                    System.err.println(val3);
                    out.write(val3);
                break;
                case "addU":
                    val1 = req.getParameter("usuario");
                    val2 = req.getParameter("contrasenna");
                    val3 = req.getParameter("isAdmin");
                    val1 = control.addUser(val1, val2, val3);
                    System.err.println(val1);
                    out.write(val1);
                break;
                case "updateU":
                    val1 = req.getParameter("usuario");
                    val2 = req.getParameter("pswrd");
                    val3 = req.getParameter("isA");
                    val1 = control.update(val1,val2,val3);
                    System.err.println(val1);
                    out.write(val1);
                break;
                case "deleteU":
                    val1 = req.getParameter("arg");
                    val1 = control.deleteUser(val1);
                    System.err.println(val1);
                    out.write(val1);
                break;     
                case "deleteP":
                    val1 = req.getParameter("codigo");
                    val1 = control.deleteProducto(val1);
                    System.err.println(val1);
                    out.write(val1);
                break;   
                case "listaU":
                    val1 = req.getParameter("arg");
                    val1 = control.listarUsuarios(val1);
                    System.err.println(val1);
                    out.write(val1);
                break;    
                ////////////////////////////////////////////////////////////////////7
                case "listarP":
                    val1 = req.getParameter("limite");
                    int1 = Integer.valueOf(val1);
                    val1 = control.listaProductos(int1);
                    System.err.println(val1);
                    out.write(val1);
                break;    
                case "updateP":
                    val1 = req.getParameter("codigo");
                    val2 = req.getParameter("nombre");
                    val3 = req.getParameter("desc");
                    int1 = Integer.valueOf(req.getParameter("precio"));
                    val1 = control.Update(val1, val2,val3,int1);
                    System.err.println(val1);
                    out.write(val1);
                break;    
                case "addP":
                    val1 = req.getParameter("codigo");
                    val2 = req.getParameter("nombre");
                    val3 = req.getParameter("desc");
                    val4 = req.getParameter("precio");
                    int1 = Integer.parseInt(val4);
                    val1 = control.addProducto(val1, val2, val3, int1);
                    System.err.println(val1);
                    out.write(val1);
                break;    
                ///////////////////////////////////////////////////
                case "addC":
                    val1 = req.getParameter("usuario");
                    val2 = req.getParameter("producto");
                    val3 = req.getParameter("cant");
                    int1 = Integer.valueOf(val3);
                    val1 = control.addCompra(val1, val2, int1);
                    System.err.println(val1);
                    out.write(val1);
                break;    
                ///////////////////////////////////////////////////
                case "historial":
                    val1 = req.getParameter("usuario");
                    val1 = control.listarH(val1);
                    System.err.println(val1);
                    out.write(val1);
                break;    
            }    
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
