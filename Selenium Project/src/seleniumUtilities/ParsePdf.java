package seleniumUtilities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ParsePdf {

	public static void main(String[] args) {
		String localPath = "C:\\Users\\Public\\Downloads\\Himanshu Documents\\Himanshu-Goyal-Resume.pdf";
		String urlOfSameFile = "file:///C:/Users/Public/Downloads/Himanshu%20Documents/Himanshu-Goyal-Resume.pdf";
		// We can give any https URL also
		getPdfContent(localPath);
		getPdfContentFromUrl(urlOfSameFile);
	}

	public static void getPdfContent(String localPath) {
		try {
			PDDocument doc = PDDocument.load(new File(localPath));
			PDFTextStripper strip = new PDFTextStripper();
			String stripText = strip.getText(doc);
			System.out.println(stripText);
			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getPdfContentFromUrl(String urlOrLink) {
		try {
			URL pdfUrl = new URL(urlOrLink);
			InputStream is = pdfUrl.openStream();
			BufferedInputStream br = new BufferedInputStream(is);

			PDDocument doc = PDDocument.load(br);
			System.out.println("Number of pages- " + doc.getNumberOfPages());
			
			PDFTextStripper strip = new PDFTextStripper();
			strip.setStartPage(2);
			String stripText = strip.getText(doc);
			System.out.println(stripText);
			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
