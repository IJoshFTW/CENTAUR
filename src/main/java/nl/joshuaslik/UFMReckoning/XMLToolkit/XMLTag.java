package nl.joshuaslik.UFMReckoning.XMLToolkit;

import java.util.HashMap;

/**
 *
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 *
 */
public class XMLTag {
	
	private String name;
	// In HashMap for easy checking if elements contains a tag of a certain name (form of indexing)
	private HashMap<String, XMLTag> elements;
	private HashMap<String, String> atts;
	private String content;
	
	public XMLTag(String name, HashMap<String, String> atts) {
		this.name = name;
		this.atts = atts;
	}
	
	public int elements() {
		return elements.size();
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean hasElements() {
		if(elements.size() == 0)
			return false;
		else
			return true;
	}
	
	public boolean hasElement(String name) {
		return elements.containsKey(name);
		
	}
	
	public void addElement(XMLTag element) {
		elements.put(element.getName(), element);
	}
	
	/**
	 * @param index
	 * 			is the index of the element to get
	 * @return the element
	 */
	public XMLTag getElement(int index) {
		return elements.get(index);
	}

	/**
	 * Getter
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**'
	 * Setter
	 * @param name
	 * 			is the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter
	 * @return the elements
	 */
	public HashMap<String, XMLTag> getElements() {
		return elements;
	}

	/**
	 * Setter
	 * @param elements
	 * 			is the elements to set
	 */
	public void setElements(HashMap<String, XMLTag> elements) {
		this.elements = elements;
	}

	/**
	 * Getter
	 * @return the attributes
	 */
	public HashMap<String, String> getAtts() {
		return atts;
	}

	/**
	 * Setter
	 * @param atts
	 * 			is the attributes to set
	 */
	public void setAtts(HashMap<String, String> atts) {
		this.atts = atts;
	}

	/**
	 * Getter
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	
	public String getContent(String element) throws NoSuchElementException {
		if(element == name) {
			return content;
		} else {
			// Get the string of the following elements (+ 1 to also chop off the '.')
			int splitpoint = element.indexOf(".") + 1;
			element = element.substring(splitpoint, element.length());
			if(elements.containsKey(element))
				return elements.get(element).getContent(element);
			else
				throw new NoSuchElementException();
		}
	}

	/**
	 * Setter
	 * @param content
	 * 			is the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
