package nl.joshuaslik.centaur;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;

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

	private XMLFile file = null;
	private ArrayList<XMLTag> tagstack = new ArrayList<XMLTag>();

	public SAXParser() {
	}

	public static XMLFile parseFile(String filename) {
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
		ClassLoader classLoader = SAXParser.class.getClassLoader();
		InputStream is = classLoader.getResourceAsStream(filename);
		try {
			xr.parse(new InputSource(is));
		} catch (IOException | SAXException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return handler.getXMLFile();
	}

	public static XMLFile parseString(String xmlstring) {
		XMLReader xr = null;
		try {
			xr = XMLReaderFactory.createXMLReader();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		SAXParser handler = new SAXParser();
		xr.setContentHandler(handler);
		xr.setErrorHandler(handler);
		InputStream is = new ByteArrayInputStream(
				xmlstring.getBytes(StandardCharsets.UTF_8));
		try {
			xr.parse(new InputSource(is));
		} catch (IOException | SAXException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return handler.getXMLFile();
	}

	// //////////////////////////////////////////////////////////////////
	// Event handlers.
	// //////////////////////////////////////////////////////////////////

	public void startDocument() {
		//System.out.println("Start document");
	}

	public void endDocument() {
		//System.out.println("End document");
		this.file = new XMLFile(tagstack.get(0));
	}

	public void startElement(String uri, String name, String qName,
			Attributes atts) {
		/* Default behaviour
		if ("".equals(uri)) {
			System.out.println("Start element: " + qName);
			for (int i = 0; i < atts.getLength(); i++) {
				System.out.println("   Attribute : " + atts.getQName(i) + "=\""
						+ atts.getValue(i) + "\"");
			}
		} else {
			System.out.println("Start element: {" + uri + "}" + name);
		}
		*/

		// My behaviour
		
		// Generate HashMap for the attributes
		LinkedHashMap<String, String> attributes = new LinkedHashMap<String, String>();
		for (int i = 0; i < atts.getLength(); i++) {
			attributes.put(atts.getQName(i), atts.getValue(i));
		}

		// Create new XMLTag and push it onto the stack
		XMLTag current = new XMLTag(qName, attributes);
		tagstack.add(current);
	}

	public void endElement(String uri, String name, String qName) {
		/* Default behaviour
		if ("".equals(uri))
			System.out.println("End element: " + qName);
		else
			System.out.println("End element:   {" + uri + "}" + name);
		*/
		
		// My behaviour
		
		// Pop the current XMLTag off the stack (only if it is not the root
		// element)
		if (tagstack.size() > 1) {
			XMLTag ended = popStack();

			topOfStack().addElement(ended);
		}
	}

	public void characters(char ch[], int start, int length) {
		//System.out.print("   Characters: \"");
		String content = "";
		for (int i = start; i < start + length; i++) {
			switch (ch[i]) {
			case '\\':
				//System.out.print("\\\\");
				//content = content + "\\\\";
				break;
			case '"':
				//System.out.print("\\\"");
				content = content + "\\\"";
				break;
			case '\n':
				//System.out.print("\\n");
				//content = content + "\\n";
				break;
			case '\r':
				//System.out.print("\\r");
				//content = content + "\\r";
				break;
			case '\t':
				//System.out.print("\\t");
				//content = content + "\\t";
				break;
			default:
				//System.out.print(ch[i]);
				content = content + ch[i];
				break;
			}
		}
		topOfStack().setContent(content);
		//System.out.print("\"\n");
	}

	private XMLTag topOfStack() {
		return tagstack.get(tagstack.size() - 1);
	}

	private XMLTag popStack() {
		return tagstack.remove(tagstack.size() - 1);
	}

	public XMLFile getXMLFile() {
		return file;
	}

}