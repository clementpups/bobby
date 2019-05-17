package fr.epsi.jeeProject.Listener;
import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.IBlogDao;
import fr.epsi.jeeProject.dao.mockImpl.MockBlogDao;
import org.apache.logging.log4j.Logger;

import java.lang.management.ManagementFactory;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@WebListener()
public class StartupListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {
    // Public constructor is required by servlet spec
    public StartupListener() {
    	ObjectName name = null;
    	name = new ObjectName("fr.epsi.jeeProject:type=ResponseJmxMBean");
    	ResponseJmx mbean = new ResponseJmx();
    	MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
    	mbs.registerMBean(mbean, name);
    }
    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        System.out.println("Démarrage application");
        IBlogDao blogDao = new MockBlogDao();
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("contact@aquasys.fr");
        List<Blog> listOfBlogs = blogDao.getBlogs(utilisateur);
        System.out.println("nb blogs = " + listOfBlogs.size());

        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:hsqlbd:hsql://localhost:9003","SA","");
            System.out.println("connexion OK");
            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
