package nl.joshuaslik.UFMReckoning.XMLToolkit;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 *
 */
public class XMLTag {
	
	private String name;
	private ArrayList<XMLTag> elements;
	private HashMap<String, String> atts;
	private String content;
	
	public XMLTag(String name, HashMap<String, String> atts) {
		this.name = name;
		this.atts = atts;
	}
	
	public void addElement(XMLTag subtag) {
		elements.add(subtag);
	}
	
	public ArrayList<XMLTag> getElements() {
		return elements;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}

}
