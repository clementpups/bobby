package fr.epsi.jeeProject.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.dao.mockImpl.MockBlogDao;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
/**
 * Servlet implementation class ListPostServlet
 */
@WebServlet("/ListPostServlet")
public class ListPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		int i = 0;
		MockBlogDao BlogDAO = new MockBlogDao();
		List<Blog> list = BlogDAO.getBlogs();
		response.setContentType("text/plain");
		request.setAttribute("list", list);
		request.getRequestDispatcher("ListPost.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
