package seleniumUtilities;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ParsePdf {

	public static void main(String[] args) {
		String url = "C:\\Users\\Public\\Downloads\\Himanshu Documents\\Himanshu-Goyal-Resume.pdf";
		getPdfContent(url);
	}

	public static void getPdfContent(String url) {
		try {
			PDDocument doc = PDDocument.load(new File(url));
			PDFTextStripper strip = new PDFTextStripper();
			String stripText = strip.getText(doc);
			System.out.println(stripText);
			doc.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
