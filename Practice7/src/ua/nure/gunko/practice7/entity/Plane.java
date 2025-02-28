//
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.13 at 07:29:26 PM MSK 
//


package ua.nure.gunko.practice7.entity;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for plane complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="plane">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Model" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Origin" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Chars" type="{http://nure.ua/gunko/practice7}Base"/>
 *         &lt;element name="Parameters">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="long" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="width" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="height" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Price" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "plane", propOrder = {
    "model",
    "origin",
    "chars",
    "parameters",
    "price"
})
public class Plane {

    @XmlElement(name = "Model", required = true)
    protected String model;
    @XmlElement(name = "Origin", required = true)
    protected String origin;
    @XmlElement(name = "Chars", required = true)
    protected Base chars;
    @XmlElement(name = "Parameters", required = true)
    protected Plane.Parameters parameters;
    @XmlElement(name = "Price", required = true)
    protected int price;

    /**
     * Gets the value of the model property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the value of the model property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModel(String value) {
        this.model = value;
    }

    /**
     * Gets the value of the origin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the value of the origin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigin(String value) {
        this.origin = value;
    }

    /**
     * Gets the value of the chars property.
     * 
     * @return
     *     possible object is
     *     {@link Base }
     *     
     */
    public Base getChars() {
        return chars;
    }

    /**
     * Sets the value of the chars property.
     * 
     * @param value
     *     allowed object is
     *     {@link Base }
     *     
     */
    public void setChars(Base value) {
        this.chars = value;
    }

    /**
     * Gets the value of the parameters property.
     * 
     * @return
     *     possible object is
     *     {@link Plane.Parameters }
     *     
     */
    public Plane.Parameters getParameters() {
        return parameters;
    }

    /**
     * Sets the value of the parameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link Plane.Parameters }
     *     
     */
    public void setParameters(Plane.Parameters value) {
        this.parameters = value;
    }

    public String print() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("Model: ").append(model).append(System.lineSeparator());
    	sb.append("Origin: ").append(origin).append(System.lineSeparator());
    	sb.append("Chars: ").append(System.lineSeparator()).append(chars).append(System.lineSeparator());
    	sb.append("Parametrs: ").append(System.lineSeparator()).append(parameters).append(System.lineSeparator());
    	sb.append("Price: ").append(price).append(System.lineSeparator());
    	return sb.toString();
    	
    }
    
    @Override
	public String toString() {
		return print();
	}

	/**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrice(int value) {
        this.price = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="long" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="width" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="height" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "_long",
        "width",
        "height"
    })
    public static class Parameters {

        @XmlElement(name = "long")
        protected int llong;
        protected int width;
        protected int height;

        /**
         * Gets the value of the long property.
         * 
         */
        public int getLong() {
            return llong;
        }

        public String print() {
        	StringBuilder sb = new StringBuilder();
        	sb.append("Long: ").append(llong).append(System.lineSeparator());
        	sb.append("Width: ").append(width).append(System.lineSeparator());
        	sb.append("Height: ").append(height).append(System.lineSeparator());
        	return sb.toString();
        }
        
        @Override
		public String toString() {
			return print();
		}

		/**
         * Sets the value of the long property.
         * 
         */
        public void setLong(int value) {
            this.llong = value;
        }

        /**
         * Gets the value of the width property.
         * 
         */
        public int getWidth() {
            return width;
        }

        /**
         * Sets the value of the width property.
         * 
         */
        public void setWidth(int value) {
            this.width = value;
        }

        /**
         * Gets the value of the height property.
         * 
         */
        public int getHeight() {
            return height;
        }

        /**
         * Sets the value of the height property.
         * 
         */
        public void setHeight(int value) {
            this.height = value;
        }

    }

}
