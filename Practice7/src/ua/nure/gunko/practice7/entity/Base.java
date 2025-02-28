//
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.13 at 07:29:26 PM MSK 
//

package ua.nure.gunko.practice7.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * <p>
 * Java class for Base complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Base">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)

public class Base {
	@XmlElement(name = "Type", required = true)
	protected String type;
	protected int count;
	protected int rockets;
	protected boolean radar;

	public String print() {
		StringBuilder sb = new StringBuilder();
		if (!"разведчик".equals(type)) {
			sb.append("Type: ").append(type).append(System.lineSeparator());
			sb.append("Count of place: ").append(count).append(System.lineSeparator());
			sb.append("Ammo: ").append(rockets).append(System.lineSeparator());
			sb.append("Radar: ").append(radar).append(System.lineSeparator());
		} else {
			sb.append("Type: ").append(type).append(System.lineSeparator());
			sb.append("Count of place: ").append(count).append(System.lineSeparator());
			sb.append("Radar: ").append(radar).append(System.lineSeparator());
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return print();
	}

	/**
	 * Gets the value of the type property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the value of the type property.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setType(String value) {
		this.type = value;
	}

	/**
	 * Gets the value of the count property.
	 * 
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Sets the value of the count property.
	 * 
	 */
	public void setCount(int value) {
		this.count = value;
	}

	/**
	 * Gets the value of the rockets property.
	 * 
	 */
	public int getRockets() {
		return rockets;
	}

	/**
	 * Sets the value of the rockets property.
	 * 
	 */
	public void setRockets(int value) {
		this.rockets = value;
	}

	/**
	 * Gets the value of the radar property.
	 * 
	 */
	public boolean isRadar() {
		return radar;
	}

	/**
	 * Sets the value of the radar property.
	 * 
	 */
	public void setRadar(boolean value) {
		this.radar = value;
	}

}
