package ua.nure.gunko.rent.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ua.nure.gunko.rent.db.entity.Order;

public class PDFDocument {

	public static void createReportEN(List<Order> list) throws DocumentException, IOException {
		Document document = new Document();

		PdfWriter.getInstance(document, new FileOutputStream("/home/maxforce01/Документы/OrderReport.pdf"));
		document.open();
		final BaseFont bf = BaseFont.createFont(Path.ARIALL, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font = new Font(bf, 10, Font.NORMAL);
		PdfPTable table = new PdfPTable(4);
		Stream.of("ID", "Date start", "Date end", "Payment").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle,font));
			table.addCell(header);
		});
		for (Order order : list) {
			table.addCell(String.valueOf(order.getId()));
			table.addCell(String.valueOf(order.getDateStart()));
			table.addCell(String.valueOf(order.getDateEnd()));
			table.addCell(String.valueOf(order.getPayment()) + "(UAH)");
		}
		int payment = 0;
		for (Order order : list) {
			payment += order.getPayment();
		}
		Paragraph p = new Paragraph("Total profit for the period:" + payment+"(UAH)", font);
		document.add(table);
		document.add(p);
		document.close();
	}

	public static void createReportRU(List<Order> list) throws DocumentException, IOException {
		Document document = new Document();

		PdfWriter.getInstance(document, new FileOutputStream("/home/maxforce01/Документы/OrderReport.pdf"));
		document.open();
		final BaseFont bf = BaseFont.createFont(Path.ARIALL, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font = new Font(bf, 10, Font.NORMAL);
		PdfPTable table = new PdfPTable(4);
		Stream.of("Номер", "Дата начала", "Дата окончания", "Оплата").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle,font));
			table.addCell(header);
		});
		for (Order order : list) {
			table.addCell(String.valueOf(order.getId()));
			table.addCell(String.valueOf(order.getDateStart()));
			table.addCell(String.valueOf(order.getDateEnd()));
			table.addCell(String.valueOf(order.getPayment()) + "(UAH)");
		}
		int payment = 0;
		for (Order order : list) {
			payment += order.getPayment();
		}
		Paragraph p = new Paragraph("Общий доход за период:" + payment+"(UAH)", font);
		document.add(table);
		document.add(p);
		document.close();
	}

}
