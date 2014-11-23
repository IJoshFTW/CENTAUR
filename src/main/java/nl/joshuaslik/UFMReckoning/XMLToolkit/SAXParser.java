package nl.joshuaslik.UFMReckoning.XMLToolkit;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import nl.joshuaslik.UFMReckoning.App;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Joshua Slik<br>
 *         http://www.joshuaslik.nl/
 *
 */
public class SAXParser extends DefaultHandler {
	
	private XMLFile root = null;
	
	public SAXParser() {}

	public static void parseFile(String filename) {
		XMLReader xr = null;
		try {
			xr = XMLReaderFactory.createXMLReader();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		SAXParser handler = new SAXParser();
		xr.setContentHandler(handler);
		xr.setErrorHandler(handler);

		// Parse each file provided on the command line.
		ClassLoader classLoader = App.class.getClassLoader();
		InputStream is = classLoader.getResourceAsStream(filename);
		try {
			xr.parse(new InputSource(is));
		} catch (IOException | SAXException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void parseString(String xmlstring) {
		XMLReader xr = null;
		try {
			xr = XMLReaderFactory.createXMLReader();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		SAXParser handler = new SAXParser();
		xr.setContentHandler(handler);
		xr.setErrorHandler(handler);
		InputStream is = new ByteArrayInputStream(xmlstring.getBytes(StandardCharsets.UTF_8));
		try {
			xr.parse(new InputSource(is));
		} catch (IOException | SAXException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	// //////////////////////////////////////////////////////////////////
	// Event handlers.
	// //////////////////////////////////////////////////////////////////

	public void startDocument() {
		System.out.println("Start document");
	}

	public void endDocument() {
		System.out.println("End document");
	}

	public void startElement(String uri, String name, String qName,
			Attributes atts) {
		if ("".equals(uri)) {
			System.out.println("Start element: " + qName);
			for (int i = 0; i < atts.getLength(); i++) {
				System.out.println("   Attribute : " + atts.getQName(i) + "=\""
						+ atts.getValue(i) + "\"");
			}
		} else {
			System.out.println("Start element: {" + uri + "}" + name);
		}
		
		if (root == null) {
			
		}
	}

	public void endElement(String uri, String name, String qName) {
		if ("".equals(uri))
			System.out.println("End element: " + qName);
		else
			System.out.println("End element:   {" + uri + "}" + name);
	}

	public void characters(char ch[], int start, int length) {
		System.out.print("   Characters: \"");
		for (int i = start; i < start + length; i++) {
			switch (ch[i]) {
			case '\\':
				System.out.print("\\\\");
				break;
			case '"':
				System.out.print("\\\"");
				break;
			case '\n':
				System.out.print("\\n");
				break;
			case '\r':
				System.out.print("\\r");
				break;
			case '\t':
				System.out.print("\\t");
				break;
			default:
				System.out.print(ch[i]);
				break;
			}
		}
		System.out.print("\"\n");
	}

}