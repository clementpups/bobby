package fr.epsi.jeeProject.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Reponse;
import fr.epsi.jeeProject.beans.Statut;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.IBlogDao;
import fr.epsi.jeeProject.dao.mockImpl.MockBlogDao;

/**
 * Servlet implementation class BlogServlet
 */
@WebServlet("/BlogServlet")
public class BlogServlet extends HttpServlet {
	

	Blog RenderBlog = null;
	List<Reponse> reps = new ArrayList();
	HttpSession session = null;
	IBlogDao blogDao = new MockBlogDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BlogServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub;
		String param = request.getParameter("ShowBlog");
		if (param != null) {
			RenderBlog = blogDao.getBlog(Integer.parseInt(param));

		} 
		
		response.setContentType("text/plain");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("Param ").append(param);
		request.setAttribute("postName", param);
		request.setAttribute("Blog", RenderBlog);
		request.getRequestDispatcher("BlogPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			doGet(request, response);
		}
	
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doDelete(request, response);

		String param = request.getParameter("BlogId");
		Blog DeleteBlog = null;
		if (param != null) {
			DeleteBlog = blogDao.getBlog(Integer.parseInt(param));
		}
		MockBlogDao BlogDAO = new MockBlogDao();
		
        try {
			BlogDAO.deleteBlog(DeleteBlog);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	
	}
	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doPut(request, response);
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur)session.getAttribute("User");
		
		String param = request.getParameter("BlogId");
		Blog EditBlog = null;
		if (param != null) {
			EditBlog = blogDao.getBlog(Integer.parseInt(param));
		}
		if(EditBlog!= null && (EditBlog.getCreateur().getEmail == user.getEmail() || user.getAdmin())) {
		param = request.getParameter("titre");
		if(param != "")
		EditBlog.setTitre(param);
		
		param = request.getParameter("texte" );
		if(param != "")
		EditBlog.setDescription(param);
		
		EditBlog.setDateEdition(new Date(new java.util.Date().getTime()));
		}
		MockBlogDao BlogDAO = new MockBlogDao();
		try {
			BlogDAO.updateBlog(EditBlog);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	
	}
		
}


