package ua.nure.gunko.practice7;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import ua.nure.gunko.practice7.controllers.ParsDOM;
import ua.nure.gunko.practice7.controllers.ParsStAX;
import ua.nure.gunko.practice7.controllers.SAXController;
import ua.nure.gunko.practice7.entity.Planes;
import ua.nure.gunko.practice7.util.Sorter;

public class Main {
	public static final String FILENAME = "input.xml";

	public static void main(String[] args)
			throws SAXException, ParserConfigurationException, IOException, TransformerException, XMLStreamException {
		ParsDOM dom = new ParsDOM();
		dom.create();
		Planes planes = dom.getPlanes();
		Sorter.sortByModel(planes);
		ParsDOM.saveToXML(planes, "output.dom.xml");
		SAXController sax = new SAXController(FILENAME);
		sax.parse();
		planes = sax.getPlanes();
		Sorter.sortByModel(planes);
		ParsDOM.saveToXML(planes, "output.sax.xml");
		ParsStAX stax = new ParsStAX(FILENAME);
		stax.parse();
		planes = stax.getPlanes();
		Sorter.sortByModel(planes);
		ParsDOM.saveToXML(planes, "output.stax.xml");
	}
}
