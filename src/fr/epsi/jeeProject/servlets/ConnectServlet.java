package fr.epsi.jeeProject.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.epsi.jeeProject.dao.mockImpl.MockUtilisateurDao;
import fr.epsi.jeeProject.beans.Utilisateur;

/**
 * Servlet implementation class ConnectServlet
 */
@WebServlet("/Connect")
public class ConnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String password= request.getParameter("password");
	    String name = request.getParameter("email");
	    
	    MockUtilisateurDao userDAO = new MockUtilisateurDao();
	    
	    try {
			Utilisateur user = userDAO.connect(name, password);
			if (user != null)
			{
				HttpSession session = request.getSession();
				session.setAttribute("User", user);
				response.sendRedirect("CreateUser.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	   System.out.println(password);
	   System.out.println(name);
            
            
	}

}
