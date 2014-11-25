package nl.joshuaslik.centaur;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class XMLTagTest extends TestCase
{
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public XMLTagTest( String testName )
	{
    	super( testName );
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
    	return new TestSuite( XMLTagTest.class );
	}
	
	/* === *** *** **  ** *** *** ===
	 * === Tests after this point ===
	 * === *** *** **  ** *** *** === */

	/**
	 * Rigourous Test :-)
	 */
	public void testXMLTag()
	{
		System.out.println("testXMLTag()");
		assertTrue(true);
		String element = "blabla.lolxd.asdf.easd";
		int splitpoint = element.indexOf(".") + 1;
		element = element.substring(splitpoint, element.length());
		System.out.println(element);
    }
}
