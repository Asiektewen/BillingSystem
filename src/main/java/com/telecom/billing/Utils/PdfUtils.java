/**
 * 
 */
package com.telecom.billing.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDCcitt;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

import com.telecom.billing.model.RateHistory;

/**
 * @author Eric
 * 
 */
public class PdfUtils {

	public static String generatePDF(String fileName) {
		String path = ExcelUtils.getOutPutDir() + fileName + ".pdf";
		return path;

	}

	public static void drawTable(PDPage page,
			PDPageContentStream contentStream, float y, float margin,
			Map contentdata) throws IOException {
		String[][] content = (String[][]) contentdata.get("content");
		final int rows = content.length<25 ? content.length+1:26;
		final int cols = content[0].length;
		final float rowHeight = 20f;
		final float tableWidth = 280f;
		final float tableHeight = rowHeight * rows;
		final float colWidth = tableWidth / (float) cols;
		final float cellMargin = 1f;
		// now add the text
		contentStream.setFont(PDType1Font.COURIER_BOLD, 13);

		// draw the header
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(margin + cellMargin, y);
		SimpleDateFormat dtf = new SimpleDateFormat("mm-dd-yyyy");
		contentStream.drawString("Service Name :" + contentdata.get("service")
				+ "  From country :" + contentdata.get("srcCty") + "   Date:"
				+ dtf.format(new Date()));
		contentStream.endText();
		
		// draw the rows
		y = y - 5f;
		float nexty = y;
		for (int i = 0; i <= rows; i++) {
			contentStream.drawLine(margin, nexty, margin + tableWidth, nexty);
			if(content.length-23-i>0){
				contentStream.drawLine(margin + tableWidth + 20, nexty, margin + 2
						* tableWidth + 20, nexty);
			}

			nexty -= rowHeight;
		}

		// draw the columns
		float nextx = margin;
		for (int i = 0; i <= cols; i++) {
			contentStream.drawLine(nextx, y, nextx, y - tableHeight);
			if(content.length-25-i>0){
			contentStream.drawLine(nextx + tableWidth + 20, y, nextx
					+ tableWidth + 20, y - tableHeight);
			}
			nextx += colWidth;
		}

		float textx = margin + cellMargin;
		float texty = y - 15;
		// draw the header.
		String[] header = { "Country", "peak", "off peak", "Country", "peak",
				"off peak" };
		for (int i = 0; i < (content.length>25?6:3); i++) {
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(textx, texty);
			contentStream.drawString(header[i]);
			contentStream.endText();
			textx += colWidth;
			if (i == 2)textx += 20;
		}

		// fill the contents;
		contentStream.setFont(PDType1Font.COURIER, 12);
		textx = margin + cellMargin;
		texty = y - 15 - rowHeight;
		for (int i = 0; i < content.length; i++) {
			if (i == 24) {
				margin += tableWidth + 20;
			}
			for (int j = 0; j < content[i].length; j++) {
				String text = content[i][j];
				if (null != text && !"".equals(text)) {
					contentStream.beginText();
					contentStream.moveTextPositionByAmount(textx, texty);
					contentStream.drawString(text);
					contentStream.endText();
					textx += colWidth;
				}

			}
			texty -= rowHeight;
			textx = margin + cellMargin;
			if (i == 24) {
				texty += rowHeight * 25;
			}
		}
		texty = y - 15 - rowHeight * 27;
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(margin>tableWidth + 20? (margin - (tableWidth + 20)):margin,
				texty);
		contentStream.drawString("* peak statring time:"
				+ contentdata.get("pecktime"));
		contentStream.endText();
		texty = y - 15 - rowHeight * 28;
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(margin>tableWidth + 20? (margin - (tableWidth + 20)):margin,
				texty);
		contentStream.drawString("* off peak statring time:"
				+ contentdata.get("offpecktime"));
		contentStream.endText();
	}

	// file name is in format (service_srcCty)
	public static String generateRateSheet(String fileName, Map dataMap)
			throws Exception {
		List dataCollection = (List) dataMap.get("Rate_data");
		Map contentdata = new HashMap();
		contentdata.put("service", fileName.split("_")[0]);
		contentdata.put("srcCty", fileName.split("_")[1]);
		contentdata.put("pecktime", dataMap.get("pecktime"));
		contentdata.put("offpecktime", dataMap.get("offpecktime"));

		PDDocument doc = new PDDocument();
		for (int i = 0; i < dataCollection.size() / 50 + 1; i++) {
			String[][] content = new String[50][3];
			if(i==dataCollection.size() / 50){
				content = new String[dataCollection.size()%50][3];
			}
			for (int j = i * 50; j < (i + 1) * 50 && j < dataCollection.size(); j++) {
				RateHistory rate = (RateHistory) dataCollection.get(j);
				content[j-50*i] = new String[] { rate.getDestCountry(),
						rate.getPeakRate().toString(),
						rate.getOffpeakRate().toString() };
			}
			contentdata.put("content", content);
			createOnepageRate(dataCollection, contentdata, doc);
		}
		String path = ExcelUtils.getOutPutDir() + "\\" + fileName + ".pdf";
		File dir = new File(path);
		if (dir.exists()) {
			dir.delete();
		}
		doc.save(path);
		return path;

	}

	private static void createOnepageRate(List dataCollection, Map contentdata,
			PDDocument doc) throws IOException, FileNotFoundException {
		PDPage page = new PDPage();		
		doc.addPage(page);
		PDXObjectImage ximage = null;
		//String image= PdfUtils.class.getClass().getResource("/resources/img/logo-bill.jpg").toString();
		String image = "D:/develop/git/BillingSystem/input/logo.jpg";
		if (image.toLowerCase().endsWith(".jpg")) {
			ximage = new PDJpeg(doc, new FileInputStream(image));
		} else if (image.toLowerCase().endsWith(".tif")
				|| image.toLowerCase().endsWith(".tiff")) {
			ximage = new PDCcitt(doc,
					new RandomAccessFile(new File(image), "r"));
		} else {
			throw new IOException("Image type not supported:" + image);
		}
		ximage.setHeight(80);
		ximage.setWidth(600);
		PDPageContentStream contentStream = new PDPageContentStream(doc, page,true, true);
		contentStream.drawImage(ximage, 0, 700);
		drawTable(page, contentStream, 650, 10, contentdata);
		contentStream.close();
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws COSVisitorException
	 */
	public static void main(String[] args) throws Exception,
			COSVisitorException {
		Map dataMap = new HashMap();
		List rateList = new ArrayList();
		for (int i = 0; i < 60; i++) {
			RateHistory rate = new RateHistory();
			rate.setDestCountry("destCty" + i);
			rate.setPeakRate((double) 100 + i);
			rate.setOffpeakRate((double) 10 + i);
			rateList.add(rate);
		}
		dataMap.put("Rate_data", rateList);
		dataMap.put("pecktime", "0800");
		dataMap.put("offpecktime", "2200");
		SimpleDateFormat dtf = new SimpleDateFormat("mm-dd-yyyy");
		String date = dtf.format(new Date());
		generateRateSheet("VOIP_USA_"+date, dataMap);
		System.out.println("done!");

	}

}