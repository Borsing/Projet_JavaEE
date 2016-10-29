package testDAO;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.Test;
import junit.framework.TestSuite;

@RunWith(Suite.class)
@SuiteClasses({ TestParticipantServices.class, TestEventServices.class })
public class ServicesTestSuite {
	
	public static Test suite() 
	{
		final Class<?>[] classesTest = {
			TestParticipantServices.class,
				TestEventServices.class

		};

		final TestSuite suite = new TestSuite(classesTest);

		return suite;
	}
}
