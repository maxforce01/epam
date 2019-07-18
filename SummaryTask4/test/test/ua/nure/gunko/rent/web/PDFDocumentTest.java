package test.ua.nure.gunko.rent.web;


import java.io.IOException;

import org.junit.Test;

import com.itextpdf.text.DocumentException;

import ua.nure.gunko.rent.db.OrderDao;
import ua.nure.gunko.rent.db.entity.Order;
import ua.nure.gunko.rent.web.PDFDocument;

public class PDFDocumentTest {

	@Test
	public void test() throws DocumentException, IOException {
		new PDFDocument();
		PDFDocument.createReportEN(new OrderDao().findOrdersByStatus(Order.Status.RETURNED.getName()));
		PDFDocument.createReportRU(new OrderDao().findOrdersByStatus(Order.Status.RETURNED.getName()));
	}

}
