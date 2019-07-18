package ua.nure.gunko.rent.web;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CopyrightTag extends SimpleTagSupport{

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		out.println("<footer id='footer' class=\"footer\">");
		out.println("<div class=\"footer-copyright text-center py-3\">© 2019 Copyright:maxforce01</div>");
		out.println("</footer>");
	}

}
