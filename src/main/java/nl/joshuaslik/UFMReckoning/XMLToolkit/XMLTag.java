package nl.joshuaslik.UFMReckoning.XMLToolkit;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 *
 */
public class XMLTag {

	private String name;
	// In LinkedHashMap for easy checking if elements contains a tag of a
	// certain name
	// (form of indexing)
	private LinkedHashMap<String, XMLTag> elements;
	private LinkedHashMap<String, String> atts;
	private String content;

	public XMLTag(String name, LinkedHashMap<String, String> atts) {
		this.name = name;
		this.atts = atts;
		this.elements = new LinkedHashMap<String, XMLTag>();
		this.content = null;
	}

	public int elements() {
		return elements.size();
	}

	/**
	 * 
	 * @return
	 */
	public boolean hasElements() {
		if (elements.size() == 0)
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
	 * @param element
	 *            is the index of the element to get
	 * @return the element
	 * @throws NoSuchElementException 
	 */
	public XMLTag getElement(String element) throws NoSuchElementException {
		if (element.equals(name)) {
			return this;
		} else {
			// Get the string of the following elements (+ 1 to also chop off
			// the '.')
			int splitpoint = element.indexOf(".") + 1;
			element = element.substring(splitpoint, element.length());
			if (elements.containsKey(element))
				return elements.get(element).getElement(element);
			else
				throw new NoSuchElementException(this.name
						+ " does not have element " + element);
		}
	}
	
	public String getContent(String element) throws NoSuchElementException {
		if (element.equals(name)) {
			return content;
		} else {
			// Get the string of the following elements (+ 1 to also chop off
			// the '.')
			int splitpoint = element.indexOf(".") + 1;
			element = element.substring(splitpoint, element.length());
			if (elements.containsKey(element))
				return elements.get(element).getContent(element);
			else
				throw new NoSuchElementException(this.name
						+ " does not have element " + element);
		}
	}
	
	public boolean hasAttribute() {
		return !(atts.isEmpty());
	}
	
	public boolean hasAttribute(String str) {
		return atts.containsKey(str);
	}
	
	public String getAttribute(String attribute) throws NoSuchAttributeException {
		if(atts.containsKey(attribute)) {
			return atts.get(attribute);
		} else {
			throw new NoSuchAttributeException(this.name + " does not have attribute " + attribute + "!");
		}
	}

	/**
	 * Getter
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter
	 * 
	 * @param name
	 *            is the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter
	 * 
	 * @return the elements
	 */
	public LinkedHashMap<String, XMLTag> getElements() {
		return elements;
	}

	/**
	 * Setter
	 * 
	 * @param elements
	 *            is the elements to set
	 */
	public void setElements(LinkedHashMap<String, XMLTag> elements) {
		this.elements = elements;
	}

	/**
	 * Getter
	 * 
	 * @return the attributes
	 */
	public LinkedHashMap<String, String> getAttributes() {
		return atts;
	}

	/**
	 * Setter
	 * 
	 * @param atts
	 *            is the attributes to set
	 */
	public void setAttributes(LinkedHashMap<String, String> atts) {
		this.atts = atts;
	}
	
	/**
	 * Getter
	 * 
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Setter
	 * 
	 * @param content
	 *            is the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public String toString() {
		return toString(0);
	}

	public String toString(int indent) {
		String retstr = "";
		String dent = "";
		for (int i = 0; i < indent; i++)
			dent = dent + " ";

		retstr = retstr + dent + "<" + name;

		if (!(atts.isEmpty())) {
			ArrayList<String> keys = new ArrayList<String>(atts.keySet());
			ArrayList<String> values = new ArrayList<String>(atts.values());
			for (int i = 0; i < atts.size(); i++) {
				retstr = retstr + " " + keys.get(i) + "=\"" + values.get(i)
						+ "\"";
			}
		}

		if (elements.size() == 0 && (content == null || content.isEmpty())) {
			return retstr + " />" + "\n";
		} else {
			retstr = retstr + ">" + "\n";
		}

		if (!(content == null))
			if (!(content.isEmpty()))
				retstr = retstr + dent + "    " + content + "\n";

		if (elements.size() > 0) {
			String[] keys = elements.keySet().toArray(new String[] {});
			for (int i = 0; i < keys.length; i++)
				retstr = retstr + elements.get(keys[0]).toString(indent + 4);
		}

		retstr = retstr + dent + "</" + name + ">" + "\n";
		return retstr;
	}
}
