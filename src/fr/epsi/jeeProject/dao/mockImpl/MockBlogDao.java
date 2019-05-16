package fr.epsi.jeeProject.dao.mockImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.sun.org.apache.xerces.internal.util.Status;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Reponse;
import fr.epsi.jeeProject.beans.Statut;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.IBlogDao;
import fr.epsi.jeeProject.dao.IStatutDao;
import fr.epsi.jeeProject.dao.IUtilisateurDao;
import fr.epsi.jeeProject.dao.PercistenceManager;

public class MockBlogDao implements IBlogDao {

	private static List<Blog> listOfBlogs;
	private IUtilisateurDao utilisateurDao = new MockUtilisateurDao();
	private IStatutDao statutDao = new MockStatutDao();
	
	@Override
	public Blog getBlog(Integer id) {
		for (Blog b : getBlogs()) {
			if (b.getId().intValue() == id.intValue()) {
				return b;
			}
		}
		return null;
	}

	@Override
	public List<Blog> getBlogs(Utilisateur utilisateur) {
		List<Blog> myBlogs = new ArrayList<Blog>();
		for (Blog b : getBlogs()) {
			if (b.getCreateur().getEmail().equals(utilisateur.getEmail())) {
				myBlogs.add(b);
			} else if (b.getStatut().getId().intValue() == IStatutDao.PUBLIE) {
				myBlogs.add(b);
			}
		}
		return myBlogs;
	}

	@Override
	public Integer createBlog(Blog blog) throws SQLException {
		int max = 0;
		for (Blog b : getBlogs()) {
			if (b.getId().intValue() > max) {
				max = b.getId();
			}
		}
		max+=1;
		blog.setId(max);
		
		InsertBlog(blog, max);
		
		return max;
	}
	
	private void InsertBlog(Blog blog, int max) throws SQLException {
		Connection connection = PercistenceManager.getConnection();
		PreparedStatement con = connection.prepareStatement("INSERT INTO BLOG ( ID, TITRE, DESCRIPTION, EMAIL,DATE_CREATION, DATE_MODIFICATION, STATUT ) VALUES (?, ?, ?, ?, ?, ?, ?)");
		con.setInt(1, max);
		con.setString(2, blog.getTitre());
		con.setString(3, blog.getDescription());
		con.setString(4, blog.getCreateur().getEmail());
		long millis=System.currentTimeMillis();
		Date dateCreation = new java.sql.Date(millis);
		con.setDate(5, dateCreation);
		con.setDate(6, null);
		Statut status = new Statut();
		status.setId(max);
		status.setDescription("PUBLIE");
		con.setInt(7, max);
		con.executeUpdate();
	}

	@Override
	public void updateBlog(Blog blog) throws SQLException {
		for (Blog b : getBlogs()) {
			if (b.getId().intValue() == blog.getId().intValue()) {
				b.setTitre(blog.getTitre());
				b.setDescription(blog.getDescription());
				long millis=System.currentTimeMillis();
				blog.setDateModification(new java.sql.Date(millis));
			}
		}
	}

	@Override
	public void deleteBlog(Blog blog) throws SQLException {
		for (Blog b : getBlogs()) {
			if (b.getId().intValue() == blog.getId().intValue()) {
				getBlogs().remove(b);
				return;
			}
		}
	}

	@Override
	public void addReponse(Blog blog, Reponse reponse) throws SQLException {
		for (Blog b : getBlogs()) {
			if (b.getId().intValue() == blog.getId().intValue()) {
				if (b.getListOfReponses() == null) {
					b.setListOfReponses(new ArrayList<Reponse>());
				}
				b.getListOfReponses().add(reponse);
				return;
			}
		}
	}
	
	private List<Reponse> getReponse(Blog blog) throws SQLException
	{	
		List<Reponse> Reponses = new ArrayList<Reponse>();
		Connection connection = PercistenceManager.getConnection();
		PreparedStatement st= connection.prepareStatement("SELECT * FROM BLOG_COMMENTAIRES where id=?");
		st.setInt(1, blog.getId());
		ResultSet rs2 = st.executeQuery();
		while(rs2.next()) {
			Reponse rep = new Reponse();
			rep.setBlog(blog);
			rep.setBlogger(utilisateurDao.getUtilisateur(rs2.getString("email")));
			rep.setCommentaire(rs2.getString("commentaire"));
			rep.setPublication(rs2.getDate("date_creation"));
			Reponses.add(rep);
		}
		return Reponses;
	}
	
	public List<Blog> getBlogs() {
		
		listOfBlogs = new ArrayList<Blog>();
		try {
			Connection connection = PercistenceManager.getConnection();
			PreparedStatement st= connection.prepareStatement("SELECT * FROM BLOG");
			ResultSet rs = st.executeQuery();
    
			while(rs.next()) {
				Blog blog = new Blog();
				blog.setId(rs.getInt("id"));
				blog.setTitre(rs.getString("titre"));
				blog.setDescription(rs.getString("description"));
				blog.setDateCreation(rs.getDate("date_creation"));
				blog.setDateModification(rs.getDate("date_modification"));
				Utilisateur utilisateur = utilisateurDao.getUtilisateur(rs.getString("email"));
	    		blog.setCreateur(utilisateur);
				Statut statut = statutDao.getStatut(rs.getInt("statut"));
				blog.setStatut(statut);
				
				List<Reponse> Reponses = getReponse(blog);
				blog.setListOfReponses(Reponses);
				listOfBlogs.add(blog);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		return listOfBlogs;
	}

}
