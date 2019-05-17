package fr.epsi.jeeProject;

import fr.epsi.jeeProject.beans.Utilisateur;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class UnitTest {
    private final String email;
    private final boolean isAdmin;

    public UnitTest(String email, boolean isAdmin) {
        this.email = email;
        this.isAdmin = isAdmin;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(
                new Object[] { "jeanmarc", false},
                new Object[] { "jeanmarc@test.fr", true}
        );
    }

    @Test
    public void isAdminByEmailTest() {

    }
}
