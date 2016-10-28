package testDAO;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.Test;
import junit.framework.TestSuite;

@RunWith(Suite.class)
@SuiteClasses({ TestParticipantServices.class })
public class ServicesTestSuite {
	
	public static Test suite() 
	{
		final Class<?>[] classesTest = {
			// TODO : insérer ici les classes de test. 
		};

		final TestSuite suite = new TestSuite(classesTest);

		return suite;
	}
}
