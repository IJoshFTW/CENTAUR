package nl.joshuaslik.UFMReckoning.XMLToolkit;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class SAXParserTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public SAXParserTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(SAXParserTest.class);
	}

	/*
	 * === *** *** ** ** *** *** === === Tests after this point === === *** ***
	 * ** ** *** *** ===
	 */

	/**
	 * Rigourous Test :-)
	 */
	public void testSAXParser1() {
		System.out.println("testSAXParser1()");
		
		XMLFile result = SAXParser.parseString("<?xml version=\"1.0\" encoding=\"UTF-8\"?><PLAYER id=\"adamsarota\"><NAME first=\"Adam\" last=\"Sarota\">thisIsMyNameValue<subtag>\n</subtag></NAME></PLAYER>");
		System.out.println((result.toString()));
		assertTrue(true);
		
		try {
			System.out.println(result.getContent("PLAYER.NAME"));
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println();
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testSAXParser2() {
		System.out.println("testSAXParser2()");

		ArrayList<XMLTag> tagstack = new ArrayList<XMLTag>();
		LinkedHashMap<String, String> atts1 = new LinkedHashMap<String, String>();
		atts1.put("name", "rootele");
		XMLTag tag1 = new XMLTag("rootelement", atts1);
		tagstack.add(tag1);

		LinkedHashMap<String, String> atts2 = new LinkedHashMap<String, String>();
		atts2.put("aname", "zelement");
		atts2.put("zname", "aelement");
		XMLTag tag2 = new XMLTag("childelement", atts2);
		tag2.setContent("1");
		tagstack.add(tag2);

		XMLTag currenttag = tagstack.get(tagstack.size() - 1);
		tagstack.remove(tagstack.size() - 1);
		tagstack.get(tagstack.size() - 1).addElement(currenttag);

		XMLTag returntag = tagstack.get(tagstack.size() - 1);
		// System.out.println(returntag.hasElement("childelement"));
		System.out.println(returntag.toString(0));
		assertTrue(true);
	}
	
	public void indentOut(String str) {
		
	}
	
	public static String unEscapeString(String s){
	    StringBuilder sb = new StringBuilder();
	    for (int i=0; i<s.length(); i++)
	        switch (s.charAt(i)){
	            case '\n': sb.append("\\n"); break;
	            case '\t': sb.append("\\t"); break;
	            // ... rest of escape characters
	            default: sb.append(s.charAt(i));
	        }
	    return sb.toString();
	}
}
