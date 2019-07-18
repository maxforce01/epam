package ua.nure.gunko.practice7.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.gunko.practice7.entity.Base;
import ua.nure.gunko.practice7.entity.Plane;
import ua.nure.gunko.practice7.entity.Planes;

public class SAXController extends DefaultHandler {

	private String xmlFileName;

	// current element name holder
	private String currentElement;

	private Planes planes;
	private Plane plane;
	private Plane.Parameters parameters;
	private Base chars;
	static final String PLANES_TAG = "tns:Planes";
	static final String PLANE_TAG = "Plane";
	static final String MODEL_TAG = "Model";
	static final String ORIGIN_TAG = "Origin";
	static final String CHARS_TAG = "Chars";
	static final String TYPE_TAG = "Type";
	static final String COUNT_TAG = "count";
	static final String ROCKETS_TAG = "rockets";
	static final String RADAR_TAG = "radar";
	static final String PARAMETRS_TAG = "Parameters";
	static final String LONG_TAG = "long";
	static final String WIDTH_TAG = "width";
	static final String HEIGHT_TAG = "height";
	static final String PRICE_TAG = "Price";

	public SAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public void parse() throws ParserConfigurationException, SAXException, IOException {

		SAXParserFactory factory = SAXParserFactory.newInstance();

		factory.setNamespaceAware(true);

		SAXParser parser = factory.newSAXParser();
		parser.parse(xmlFileName, this);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		currentElement = qName;

		if (PLANES_TAG.equals(currentElement)) {
			planes = new Planes();
			getPlanes().plane = new ArrayList<>();
			return;
		}
		if (PLANE_TAG.equals(currentElement)) {
			plane = new Plane();
			return;
		}
		if (CHARS_TAG.equals(currentElement)) {
			chars = new Base();
			return;
		}
		if (PARAMETRS_TAG.equals(currentElement)) {
			parameters = new Plane.Parameters();
			return;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String text = new String(ch, start, length);
		if (text.contains("<") || currentElement == null) {
			return;
		}

		if (!"".equals(text.trim())) {
			if (MODEL_TAG.equals(currentElement)) {
				plane.setModel(text);
				return;
			}
			if (ORIGIN_TAG.equals(currentElement)) {
				plane.setOrigin(text);
				return;
			}
			if (PRICE_TAG.equals(currentElement)) {
				plane.setPrice(Integer.parseInt(text.trim()));
				return;
			}
			if (COUNT_TAG.equals(currentElement)) {
				chars.setCount(Integer.parseInt(text.trim()));
				return;
			}
			if (TYPE_TAG.equals(currentElement)) {
				chars.setType(text);
				return;
			}
			if (ROCKETS_TAG.equals(currentElement)) {
				chars.setRockets(Integer.parseInt(text.trim()));
				return;
			}
			if (RADAR_TAG.equals(currentElement)) {
				chars.setRadar(Boolean.parseBoolean(text.trim()));
				return;
			}
			if (LONG_TAG.equals(currentElement)) {
				parameters.setLong(Integer.parseInt(text.trim()));
				return;
			}
			if (WIDTH_TAG.equals(currentElement)) {
				parameters.setWidth(Integer.parseInt(text.trim()));
				return;
			}
			if (HEIGHT_TAG.equals(currentElement)) {
				parameters.setHeight(Integer.parseInt(text.trim()));
				return;
			}

		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(PLANE_TAG.equals(qName)) {
			getPlanes().plane.add(plane);
			return;
		}
		if(CHARS_TAG.equals(qName)) {
			plane.setChars(chars);
			return;
		}
		if(PARAMETRS_TAG.equals(qName)) {
			plane.setParameters(parameters);
			return;
		}
	}

	public Planes getPlanes() {
		return planes;
	}

}