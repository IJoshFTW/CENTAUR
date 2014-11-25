package nl.joshuaslik.centaur;

/**
 *
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 *
 */
public class XMLFile {

	XMLTag root;

	/**
	 * Constructor
	 */
	public XMLFile(XMLTag root) {
		this.root = root;
	}

	/**
	 * 
	 * @param name
	 *            is the name of the element to get
	 * @throws NoSuchElementException
	 *             Is thrown if element is not found
	 */
	public String getContent(String name) throws NoSuchElementException {
		return root.getContent(name);
	}
	
	public XMLTag getElement(String name) throws NoSuchElementException {
		return root.getElement(name);
	}

	public String toString() {
		return root.toString();
	}

}
