package fr.epsi.jeeProject.jmx;

import java.lang.management.ManagementFactory;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

public class LancerResponseJmx {
    public static void main(String[] args) {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName name = null;
        try {
            name = new ObjectName("fr.epsi.jeeProject.jmx:type=ResponseJmxMBean");

            ResponseJmx mbean = new ResponseJmx();

            mbs.registerMBean(mbean, name);

            mbean.getCommentaire();

            mbean.setCommentaire("Test commentaire");

        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
        }
    }
}
