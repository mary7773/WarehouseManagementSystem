package output;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entity.Invoice;
import entity.Products;

public class OutputFile {
	// private static String FILE = "c:/Temp/Invoice.pdf";
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 10,
			Font.BOLD);
	@SuppressWarnings("unused")
	private static Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.NORMAL);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 8,
			Font.ITALIC);
	@SuppressWarnings("unused")
	private static Font largeBold = new Font(Font.FontFamily.TIMES_ROMAN, 22,
			Font.BOLD);

	public static void createPDF(String fileName, Invoice in)
			throws IOException, Exception {

		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			createInvoiceInfo(document, in);
			addContent(document, in);
			addFooter(document, in);
			document.close();
			JOptionPane.showMessageDialog(null,
					"Document successfully created!!!");
		} catch (FileNotFoundException | DocumentException e) {
			JOptionPane.showMessageDialog(null, "Document not found!!!");
			e.printStackTrace();
		}

	}

	public static void createInvoiceInfo(Document document, Invoice invoice)
			throws DocumentException {

		// a table with three columns
		PdfPTable table = new PdfPTable(6);
		PdfPTable t = new PdfPTable(1);
		Font f = new Font();
		f.setSize(26);
		f.setColor(BaseColor.DARK_GRAY);

		// header
		PdfPCell cell = new PdfPCell(new Phrase("Invoice", f));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(7);
		t.addCell(cell);
		document.add(t);
		Paragraph paragraph = new Paragraph();
		addEmptyLine(paragraph, 2);
		document.add(paragraph);

		// invoice number row 1
		cell = new PdfPCell(new Phrase("Company:", catFont));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(invoice.getCompany().getCompany(),
				subFont));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// invoice number row 1
		cell = new PdfPCell(new Phrase("No:", catFont));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		cell = new PdfPCell(
				new Phrase(invoice.getInvoiceNumber() + "", subFont));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// customer name 1 row
		cell = new PdfPCell(new Phrase("Customer:", catFont));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(invoice.getCustomer().getFirstName()
				+ " " + invoice.getCustomer().getLastName(), subFont));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// city company row 2
		cell = new PdfPCell(new Phrase("Bulstat:", catFont));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(invoice.getCompany().getBulstat() + "",
				subFont));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// invoice date row 2
		cell = new PdfPCell(new Phrase(""));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(""));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// city company row 2
		cell = new PdfPCell(new Phrase("Bulstat:", catFont));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(invoice.getCustomer().getBulstat() + "",
				subFont));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// city company row 3
		cell = new PdfPCell(new Phrase("MOL:", catFont));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(invoice.getCompany().getMol() + "",
				subFont));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// invoice date row 2
		cell = new PdfPCell(new Phrase(""));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(""));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// city company row 2
		cell = new PdfPCell(new Phrase("MOL:", catFont));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(invoice.getCustomer().getMol() + "",
				subFont));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		// city company row 2
		cell = new PdfPCell(new Phrase("City:", catFont));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(invoice.getCompany().getCities()
				.getName()
				+ "", subFont));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// invoice date row 2
		cell = new PdfPCell(new Phrase("Date:", catFont));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);

		SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd");
		cell = new PdfPCell(new Phrase(ft.format(invoice.getDateInvoice()),
				subFont));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// city customer row 2
		cell = new PdfPCell(new Phrase("City:", catFont));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(invoice.getCustomer().getCities()
				.getName()
				+ "", subFont));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// address company 3 row
		cell = new PdfPCell(new Phrase("Address:", catFont));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(invoice.getCompany().getAddress() + "",
				subFont));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// invoice date row 3
		cell = new PdfPCell(new Phrase(""));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(""));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// address customer 3 row
		cell = new PdfPCell(new Phrase("Address:", catFont));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(invoice.getCustomer().getAddress() + "",
				subFont));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		// }
		document.add(table);

	}

	private static void addContent(Document document, Invoice in)
			throws DocumentException {
		// create table
		Paragraph paragraph = new Paragraph();
		addEmptyLine(paragraph, 2);
		document.add(paragraph);
		// Create a table with 5 columns
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.getDefaultCell().setUseAscender(true);
		table.getDefaultCell().setUseDescender(true);

		// Add the second header row twice
		table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);

		for (int i = 0; i < 1; i++) {
			table.addCell("ID");
			table.addCell("Product Name");
			table.addCell("Quantity");
			table.addCell("Price");
			table.addCell("Total");
		}
		table.getDefaultCell().setBackgroundColor(null);
		// There are three special rows
		table.setHeaderRows(1);
		// One of them is a footer
		// table.setFooterRows(1);
		// Now let's loop over the produts

		for (Products pr : in.getProducts()) {
			table.addCell(pr.getProductId() + "");
			table.addCell(pr.getName());
			table.addCell(pr.getQuantity() + "");
			table.addCell(pr.getPrice() + "");
			table.addCell(pr.getTotal() + "");
		}
		PdfPCell cell;

		// emptty cell
		cell = new PdfPCell(new Phrase(""));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		// emptty cell
		cell = new PdfPCell(new Phrase(""));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		// emptty cell
		cell = new PdfPCell(new Phrase(""));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		// total without vat
		cell = new PdfPCell(new Phrase("Total: "));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		// vat value
		cell = new PdfPCell(new Phrase(in.getRowTotal() + ""));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		// emptty cell
		cell = new PdfPCell(new Phrase(""));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		// emptty cell
		cell = new PdfPCell(new Phrase(""));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		// emptty cell
		cell = new PdfPCell(new Phrase(""));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		// vat
		cell = new PdfPCell(new Phrase("VAT: " + in.getPercent() + "%"));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);

		// vat value
		cell = new PdfPCell(new Phrase(in.getRowTotal()*in.getPercent()/100 + ""));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);

		// emptty cell
		cell = new PdfPCell(new Phrase(""));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		// emptty cell
		cell = new PdfPCell(new Phrase(""));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		// emptty cell
		cell = new PdfPCell(new Phrase(""));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);

		// total
		cell = new PdfPCell(new Phrase("Total with VAT: "));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);

		// total value
		cell = new PdfPCell(new Phrase(in.getTotal() + ""));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);

		document.add(table);
	}

	private static void addFooter(Document document, Invoice invoice)
			throws DocumentException, IOException, Exception {

		Paragraph paragraph = new Paragraph();
		addEmptyLine(paragraph, 3);

		document.add(paragraph);

		PdfPTable table = new PdfPTable(2);
		PdfPCell cell;

		// signature cell
		cell = new PdfPCell(new Phrase(
				"Company signature:...................................."));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		// vat
		cell = new PdfPCell(new Phrase(
				"Customer signature:...................................."));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);

		document.add(table);

	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

}
