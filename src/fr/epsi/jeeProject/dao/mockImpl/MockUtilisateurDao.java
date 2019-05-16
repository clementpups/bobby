package fr.epsi.jeeProject.dao.mockImpl;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.IUtilisateurDao;
import fr.epsi.jeeProject.dao.PercistenceManager;

public class MockUtilisateurDao implements IUtilisateurDao {

	private static List<Utilisateur> listOfUtilisateurs;
	
	@Override
	public Utilisateur getUtilisateur(String email) throws SQLException {
		Connection connection = PercistenceManager.getConnection();
		PreparedStatement logIn = connection.prepareStatement("select * from users where email=?");
		logIn.setString(1, email);
		ResultSet rs = logIn.executeQuery();
		rs.next();
		if(rs.getFetchSize() == 0)
		{
			Utilisateur user = new Utilisateur(rs.getString("email"),rs.getString("nom"),rs.getString("password"));
			if(rs.getBoolean("IS_ADMIN") == true)
			{
				user.GrantAdminPrivilege(user);
			}
			return user;
		}
		return null;
	}
	
	public Utilisateur connect(String email, String password) throws SQLException
	{
		
		Connection connection = PercistenceManager.getConnection();
		PreparedStatement logIn = connection.prepareStatement("select * from users where email=? and password =?");
		logIn.setString(1, email);
		logIn.setString(2, password);
		ResultSet rs = logIn.executeQuery();
		rs.next();
		if(rs.getFetchSize() == 0)
		{
			Utilisateur user = new Utilisateur(rs.getString("email"),rs.getString("nom"),rs.getString("password"));
			if(rs.getBoolean("IS_ADMIN") == true)
			{
				user.GrantAdminPrivilege(user);
			}
			return user;
		}
		return null;
	}

	@Override
	public void createUtilisateur(Utilisateur utilisateur) throws SQLException {
		Connection connection = PercistenceManager.getConnection();
    	PreparedStatement st = connection.prepareStatement(
    			"INSERT INTO USERS (email, nom, date_creation, password , is_admin) values (?,?,?,?,?)");
    	
    	st.setString( 1, utilisateur.getEmail() );
		st.setString( 2, utilisateur.getNom());
		st.setDate( 3, utilisateur.getDateCreation());
		st.setString( 4, utilisateur.getPassword() );
		st.setBoolean( 5, utilisateur.getAdmin() );
		st.executeUpdate();
	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws SQLException {
		for (Utilisateur u : getListOfUtilisateur()) {
			if (u.getEmail().equals(utilisateur.getEmail())) {
				u.setAdmin(utilisateur.getAdmin());
				u.setNom(utilisateur.getNom());
			}
		}
	}

	@Override
	public void deleteUtilisateur(Utilisateur utilisateur) throws SQLException {
		for (Utilisateur u : getListOfUtilisateur()) {
			if (u.getEmail().equals(utilisateur.getEmail())) {
				getListOfUtilisateur().remove(u);
				return;
			}
		}
	}

	private List<Utilisateur> getListOfUtilisateur() {
		listOfUtilisateurs = new ArrayList<Utilisateur>();
		
			try {
	        	Connection connection = PercistenceManager.getConnection();
	        	Statement con = connection.createStatement();
	        	
	        	ResultSet rs = con.executeQuery("select * from users");
	        	while(rs.next()) {
	        		Utilisateur utilisateur = new Utilisateur();
	        		utilisateur.setEmail(rs.getString("email"));
	        		utilisateur.setNom(rs.getString("nom"));
	        		utilisateur.setAdmin(rs.getBoolean("is_admin"));
	        		utilisateur.setDateCreation(rs.getDate("date_creation"));
					utilisateur.setPassword(rs.getString("password"));
					listOfUtilisateurs.add(utilisateur);
				}
	        	
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
		
		return listOfUtilisateurs;
	}
}
