package ua.nure.gunko.practice7.controllers;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

import ua.nure.gunko.practice7.entity.Base;
import ua.nure.gunko.practice7.entity.Plane;
import ua.nure.gunko.practice7.entity.Planes;

public class ParsStAX {
	private String xmlFileName;

	// main container
	private Planes planes;

	public Planes getPlanes() {
		return planes;
	}

	public ParsStAX(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	/**
	 * Parses XML document with StAX (based on event reader). There is no validation
	 * during the parsing.
	 */
	public void parse() throws XMLStreamException {

		Plane plane = null;
		Base chars = null;
		Plane.Parameters params = null;
		// current element name holder
		String currentElement = null;

		XMLInputFactory factory = XMLInputFactory.newInstance();

		factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

		XMLEventReader reader = factory.createXMLEventReader(new StreamSource(xmlFileName));

		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();

			// skip any empty content
			if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
				continue;
			}

			// handler for start tags
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				currentElement = startElement.getName().getLocalPart();

				if ("Planes".equals(currentElement)) {
					planes = new Planes();
					continue;
				}

				if ("Chars".equals(currentElement)) {
					chars = new Base();
					continue;
				}

				if ("Plane".equals(currentElement)) {
					plane = new Plane();
					continue;
				}
				if ("Parameters".equals(currentElement)) {
					params = new Plane.Parameters();
					continue;
				}
			}

			// handler for contents
			if (event.isCharacters()) {
				Characters characters = event.asCharacters();

				if ("Model".equals(currentElement)) {
					plane.setModel(characters.getData());
					continue;
				}

				if ("Origin".equals(currentElement)) {
					plane.setOrigin(characters.getData());
					continue;
				}
				if ("Price".equals(currentElement)) {
					plane.setPrice(Integer.parseInt(characters.getData()));
					continue;
				}
				if ("Type".equals(currentElement)) {
					chars.setType(characters.getData());
					continue;
				}
				if ("count".equals(currentElement)) {
					chars.setCount(Integer.parseInt(characters.getData()));
					continue;
				}
				if ("rockets".equals(currentElement)) {
					chars.setRockets(Integer.parseInt(characters.getData()));
					continue;
				}
				if ("radar".equals(currentElement)) {
					chars.setRadar(Boolean.parseBoolean(characters.getData()));
					continue;
				}
				if ("long".equals(currentElement)) {
					params.setLong(Integer.parseInt(characters.getData()));
					continue;
				}
				if ("width".equals(currentElement)) {
					params.setWidth(Integer.parseInt(characters.getData()));
					continue;
				}
				if ("height".equals(currentElement)) {
					params.setHeight(Integer.parseInt(characters.getData()));
					continue;
				}
			}

			// handler for end tags
			if (event.isEndElement()) {
				EndElement endElement = event.asEndElement();
				String localName = endElement.getName().getLocalPart();

				if ("Parameters".equals(localName)) {
					plane.setParameters(params);
					continue;
				}
				if ("Chars".equals(localName)) {
					plane.setChars(chars);
					continue;
				}
				if ("Plane".equals(localName)) {
					planes.getPlane().add(plane);
					continue;
				}
			}
		}
		reader.close();
	}
}
