package nl.joshuaslik.UFMReckoning.XMLToolkit;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class SAXParserTest extends TestCase
{
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public SAXParserTest( String testName )
	{
    	super( testName );
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
    	return new TestSuite( SAXParserTest.class );
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testSAXParser1()
	{
		System.out.println("testSAXParser1()");
		SAXParser.parseString("<?xml version=\"1.0\" encoding=\"UTF-8\"?><PLAYER id=\"adamsarota\"><NAME first=\"Adam\" last=\"Sarota\" /></PLAYER>");
		assertTrue(true);
    }
	
	/**
	 * Rigourous Test :-)
	 */
	public void testSAXParser2()
	{
		System.out.println("testSAXParser2()");
		assertTrue(true);
    }
}
