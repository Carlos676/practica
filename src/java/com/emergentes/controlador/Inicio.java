package com.emergentes.controlador;

import com.emergentes.dao.ProdDAO;
import com.emergentes.dao.ProdDAOimpl;
import com.emergentes.modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Inicio", urlPatterns = {"/Inicio"})
public class Inicio extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ProdDAO dao = new ProdDAOimpl();
            //para resivir el numero de id
            int id;
            //para gestionar registros
            Producto pro = new Producto();

            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    //nuevo registro
                    request.setAttribute("aviso", pro);
                    request.getRequestDispatcher("frmaviso.jsp").forward(request, response);
                    break;
                case "edit":
                    //para editar
                    id = Integer.parseInt(request.getParameter("id"));
                    pro = dao.getById(id);
                    request.setAttribute("aviso", pro);
                    request.getRequestDispatcher("frmaviso.jsp").forward(request, response);
                    break;
                case "delete":
                    //para eliminar
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    //request.getRequestDispatcher("Inicio").forward(request, response);
                    response.sendRedirect("Inicio");

                    //response.sendRedirect(request.getContextPath()+"Inicio");
                    break;
                default:
                    //listar los registro o avisos

                    List<Producto> lista = dao.getAll();
                    request.setAttribute("avisos", lista);
                    request.getRequestDispatcher("listado.jsp").forward(request, response);
                    break;

            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProdDAO dao = new ProdDAOimpl();

        int id = Integer.parseInt(request.getParameter("id"));
        String descripcion = request.getParameter("descripcion");
        int stock = Integer.parseInt(request.getParameter("stock"));

        Producto pro = new Producto();
        pro.setId(id);
        pro.setDescripcion(descripcion);
        pro.setStock(stock);

        if (id == 0) {
            //nuevo registro
            try {
                dao.isert(pro);
                response.sendRedirect("Inicio");
            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
            }
        } else {
            //actualizacion
            try {
                dao.update(pro);
                response.sendRedirect("Inicio");
            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
            }
        }

    }

}
