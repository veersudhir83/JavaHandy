package io.sudheer.practice.simple.util;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.lowagie.text.Cell;
import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.RandomAccessFileOrArray;

public class GeneratePDFDocs {
	
	
	
	private static final Logger LOG = Logger.getLogger(GeneratePDFDocs.class);

	private static final String fileUploadPath = "D:\\tmp\\";
	
	public void generateDrawingResultsPDF() throws Exception {
		try {
							
				String pdfName = "docTemp.pdf";
				String finalPdfName = "DocumentSearchResults.pdf";
				Locale locale = new Locale("English");
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", locale);
				
				Document document = new Document(PageSize.A4.rotate(), 50, 50,50, 50);
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileUploadPath + pdfName));
				writer.setEncryption(PdfWriter.STRENGTH40BITS, null, null, PdfWriter.AllowCopy | PdfWriter.AllowPrinting);	
				String publishDate = null;
				Date currentDate = new Date();		
				publishDate = sdf.format(currentDate);
				
				GregorianCalendar calendar = new GregorianCalendar();
				int global_year = calendar.get(java.util.Calendar.YEAR);
				
				//preparing Header text
				Phrase headtext = new Phrase("Document Search Results", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD));
				Phrase nten = new Phrase("\n", FontFactory.getFont(FontFactory.HELVETICA, 10));
				Phrase pp = new Phrase();
				Table imgsearchCritTable = new Table(1);
				/*imgsearchCritTable.setHorizontalAlignment(Element.ALIGN_CENTER);
				imgsearchCritTable.setVerticalAlignment(Element.ALIGN_TOP);
				imgsearchCritTable.setCellBorder(0);*/
				imgsearchCritTable.setBorderWidth(0);
				imgsearchCritTable.setWidth(100);
				Cell cell1 = new Cell();
				cell1 = new Cell(headtext);
				imgsearchCritTable.addCell(cell1);			
				pp.add(imgsearchCritTable);		
				pp.add(nten);
				HeaderFooter header = new HeaderFooter(pp, false);
				header.setBorderColor(Color.LIGHT_GRAY);
				//document.setHeader(header);	
				
				//preparing footer text
				String footerStr = "COPYRIGHT" + " � 1997-" + global_year + " General Electric Company. All Rights Reserved " 
				+ "                                     DATE PRINTED   " + publishDate
		        + "                                                                                   "
		        + "                                                              Page No: ";
				HeaderFooter footer = new HeaderFooter(new Phrase(footerStr, FontFactory.getFont(FontFactory.HELVETICA, 
						7, Font.BOLD)), true);
				footer.setBorderColor(Color.LIGHT_GRAY);
				footer.setBorderColorTop(Color.WHITE);
				footer.setBorderColorBottom(Color.WHITE);
				document.setFooter(footer);
				
				document.open();
				String paras = "";
				Paragraph pa = new Paragraph(paras);
				Chapter chapter = new Chapter(pa, 1);
				chapter.setNumberDepth(-1);
				
				// Preparing Search Criteria Table
				Table searchCritTable = new Table(8);
				searchCritTable.setTableFitsPage(true);
				searchCritTable.setPadding(2);
				searchCritTable.setSpacing(0);
				searchCritTable.setWidth(100);
				searchCritTable.setBorderWidth(2);
				searchCritTable.setBorderColor(Color.WHITE);
				Cell cell = new Cell(new Paragraph("FIELD_DOC_NUMBER", new Font(Font.HELVETICA, 9, Font.BOLD)));
				cell.setHeader(true);
				cell.setBorderColor(Color.WHITE);
				cell.setBackgroundColor(new Color(239, 243, 252));
				searchCritTable.addCell(cell);
				cell = new Cell(new Paragraph("Engineering Drawing ID", new Font(Font.HELVETICA, 9)));
				cell.setHeader(true);
				cell.setBorderColor(Color.WHITE);
				cell.setBackgroundColor(Color.LIGHT_GRAY);
				searchCritTable.addCell(cell);
				
				cell = new Cell(new Paragraph("DOC_NAME", new Font(Font.HELVETICA, 9, Font.BOLD)));
				cell.setHeader(true);
				cell.setBorderColor(Color.WHITE);
				cell.setBackgroundColor(new Color(239, 243, 252));
				searchCritTable.addCell(cell);
				cell = new Cell(new Paragraph("Engineering Drawing Name", new Font(Font.HELVETICA, 9)));
				cell.setHeader(true);
				cell.setBorderColor(Color.WHITE);
				cell.setBackgroundColor(Color.LIGHT_GRAY);
				searchCritTable.addCell(cell);
				
				cell = new Cell(new Paragraph("DOC_REV", new Font(Font.HELVETICA, 9, Font.BOLD)));
				cell.setHeader(true);
				cell.setBorderColor(Color.WHITE);
				cell.setBackgroundColor(new Color(239, 243, 252));
				searchCritTable.addCell(cell);
				cell = new Cell(new Paragraph("Engineering Drawing Revision", new Font(Font.HELVETICA, 9)));
				cell.setHeader(true);
				cell.setBorderColor(Color.WHITE);
				cell.setBackgroundColor(Color.LIGHT_GRAY);
				searchCritTable.addCell(cell);
				
				cell = new Cell(new Paragraph("Document Owner", new Font(Font.HELVETICA, 9, Font.BOLD)));
				cell.setHeader(true);
				cell.setBorderColor(Color.WHITE);
				cell.setBackgroundColor(new Color(239, 243, 252));
				searchCritTable.addCell(cell);
				cell = new Cell(new Paragraph("Engineering Drawing Owner", new Font(Font.HELVETICA, 9)));
				cell.setHeader(true);
				cell.setBorderColor(Color.WHITE);
				cell.setBackgroundColor(Color.LIGHT_GRAY);
				searchCritTable.addCell(cell);
				
				cell = new Cell(new Paragraph("DOC_TYPE", new Font(Font.HELVETICA, 9, Font.BOLD)));
				cell.setHeader(true);
				cell.setBorderColor(Color.WHITE);
				cell.setBackgroundColor(new Color(239, 243, 252));
				searchCritTable.addCell(cell);
				cell = new Cell(new Paragraph("Drawing Type", new Font(Font.HELVETICA, 9)));
				cell.setHeader(true);
				cell.setBorderColor(Color.WHITE);
				cell.setBackgroundColor(Color.LIGHT_GRAY);
				searchCritTable.addCell(cell);
				
				cell = new Cell(new Paragraph("FIELD_DOC_CRITICAL_DWG", new Font(Font.HELVETICA, 9, Font.BOLD)));
				cell.setHeader(true);
				cell.setBorderColor(Color.WHITE);
				cell.setBackgroundColor(new Color(239, 243, 252));
				searchCritTable.addCell(cell);
				cell = new Cell(new Paragraph("Engg_drwng_crtdwg", new Font(Font.HELVETICA, 9)));
				cell.setHeader(true);
				cell.setBorderColor(Color.WHITE);
				cell.setBackgroundColor(Color.LIGHT_GRAY);
				searchCritTable.addCell(cell);
				
				cell = new Cell(new Paragraph("FIELD_DOC_STATUS", new Font(Font.HELVETICA, 9, Font.BOLD)));
				cell.setHeader(true);
				cell.setBorderColor(Color.WHITE);
				cell.setBackgroundColor(new Color(239, 243, 252));
				searchCritTable.addCell(cell);
				cell = new Cell(new Paragraph("Engg_drwng_status", new Font(Font.HELVETICA, 9)));
				cell.setHeader(true);
				cell.setBorderColor(Color.WHITE);
				cell.setBackgroundColor(Color.LIGHT_GRAY);
				searchCritTable.addCell(cell);
				
				cell = new Cell(new Paragraph("Document Creation From Date", new Font(Font.HELVETICA, 9, Font.BOLD)));
				cell.setHeader(true);
				cell.setBorderColor(Color.WHITE);
				cell.setBackgroundColor(new Color(239, 243, 252));
				searchCritTable.addCell(cell);
				String fromDate = "";
				fromDate = sdf.format(new Date());
				cell = new Cell(new Paragraph(fromDate, new Font(Font.HELVETICA, 9)));
				cell.setHeader(true);
				cell.setBorderColor(Color.WHITE);
				cell.setBackgroundColor(Color.LIGHT_GRAY);
				searchCritTable.addCell(cell);
				
				cell = new Cell(new Paragraph("Document Creation To Date", new Font(Font.HELVETICA, 9, Font.BOLD)));
				cell.setHeader(true);
				cell.setBorderColor(Color.WHITE);
				cell.setBackgroundColor(new Color(239, 243, 252));
				searchCritTable.addCell(cell);
				String toDate = "";
				toDate =  sdf.format(new Date());

				cell = new Cell(new Paragraph(toDate, new Font(Font.HELVETICA, 9)));
				cell.setHeader(true);
				cell.setBorderColor(Color.WHITE);
				cell.setBackgroundColor(Color.LIGHT_GRAY);
				searchCritTable.addCell(cell);
				
				searchCritTable.endHeaders();
				// Ends Search Criteria Table
				
				chapter.add(searchCritTable);
				
				// Preparing Result Set Table
				
				Table resultTable = new Table(8);
				resultTable.setTableFitsPage(true);
				resultTable.setPadding(1);
				resultTable.setWidth(100);
				resultTable.setBorderWidth(0);
				resultTable.setBorderColor(Color.BLACK);			
				
				cell = new Cell(new Paragraph("Doc Number", new Font(Font.HELVETICA, 9)));
				cell.setHeader(true);
				cell.setBorderColor(Color.BLACK);
				cell.setBackgroundColor(Color.YELLOW);
				resultTable.addCell(cell);
				cell = new Cell(new Paragraph("Doc Name", new Font(Font.HELVETICA, 9)));
				cell.setHeader(true);
				cell.setBorderColor(Color.BLACK);
				cell.setBackgroundColor(Color.YELLOW);
				resultTable.addCell(cell);
				cell = new Cell(new Paragraph("Doc Revision", new Font(Font.HELVETICA, 9)));
				cell.setHeader(true);
				cell.setBorderColor(Color.BLACK);
				cell.setBackgroundColor(Color.YELLOW);
				resultTable.addCell(cell);
				cell = new Cell(new Paragraph("Doc Rev Date", new Font(Font.HELVETICA, 9)));
				cell.setHeader(true);
				cell.setBorderColor(Color.BLACK);
				cell.setBackgroundColor(Color.YELLOW);
				resultTable.addCell(cell);
				cell = new Cell(new Paragraph("Doc Owner", new Font(Font.HELVETICA, 9)));
				cell.setHeader(true);
				cell.setBorderColor(Color.BLACK);
				cell.setBackgroundColor(Color.YELLOW);
				resultTable.addCell(cell);
				cell = new Cell(new Paragraph("Doc Type", new Font(Font.HELVETICA, 9)));
				cell.setHeader(true);
				cell.setBorderColor(Color.BLACK);
				cell.setBackgroundColor(Color.YELLOW);
				resultTable.addCell(cell);
				cell = new Cell(new Paragraph("Doc Critical Drawing", new Font(Font.HELVETICA, 9)));
				cell.setHeader(true);
				cell.setBorderColor(Color.BLACK);
				cell.setBackgroundColor(Color.YELLOW);
				resultTable.addCell(cell);
				cell = new Cell(new Paragraph("Doc Status", new Font(Font.HELVETICA, 9)));
				cell.setHeader(true);
				cell.setBorderColor(Color.BLACK);
				cell.setBackgroundColor(Color.YELLOW);
				resultTable.addCell(cell);
				
				
				/*for (PLMSearchData resultRow : searchResultList) {
					cell = new Cell(new Paragraph(resultRow.getDocNum(), new Font(Font.HELVETICA, 7)));
					cell.setHeader(true);
					cell.setBorderColor(Color.BLACK);
					cell.setBackgroundColor(Color.WHITE);
					resultTable.addCell(cell);
					cell = new Cell(new Paragraph(resultRow.getDocNum(), new Font(Font.HELVETICA, 7)));
					cell.setHeader(true);
					cell.setBorderColor(Color.BLACK);
					cell.setBackgroundColor(Color.WHITE);
					resultTable.addCell(cell);
					cell = new Cell(new Paragraph(resultRow.getDocRev(), new Font(Font.HELVETICA, 7)));
					cell.setHeader(true);
					cell.setBorderColor(Color.BLACK);
					cell.setBackgroundColor(Color.WHITE);
					resultTable.addCell(cell);
					cell = new Cell(new Paragraph(resultRow.getDocRevDate(), new Font(Font.HELVETICA, 7)));
					cell.setHeader(true);
					cell.setBorderColor(Color.BLACK);
					cell.setBackgroundColor(Color.WHITE);
					resultTable.addCell(cell);
					cell = new Cell(new Paragraph(resultRow.getDocOwner(), new Font(Font.HELVETICA, 7)));
					cell.setHeader(true);
					cell.setBorderColor(Color.BLACK);
					cell.setBackgroundColor(Color.WHITE);
					resultTable.addCell(cell);
					cell = new Cell(new Paragraph(resultRow.getDocType(), new Font(Font.HELVETICA, 7)));
					cell.setHeader(true);
					cell.setBorderColor(Color.BLACK);
					cell.setBackgroundColor(Color.WHITE);
					resultTable.addCell(cell);
					cell = new Cell(new Paragraph(resultRow.getDocCriticalDwg(), new Font(Font.HELVETICA, 7)));
					cell.setHeader(true);
					cell.setBorderColor(Color.BLACK);
					cell.setBackgroundColor(Color.WHITE);
					resultTable.addCell(cell);
					cell = new Cell(new Paragraph(resultRow.getDocStatus(), new Font(Font.HELVETICA, 7)));
					cell.setHeader(true);
					cell.setBorderColor(Color.BLACK);
					cell.setBackgroundColor(Color.WHITE);
					resultTable.addCell(cell);
				}*/
				
				chapter.add(resultTable);
				
				// Ends Result Set Table
				
				document.add(chapter);
				document.close();
				
				downloadPdfInline(pdfName, finalPdfName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generateABITResultsPDF() throws Exception {
		try {
			Locale locale = new Locale("English");
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", locale);
			  
			String pdfName = "abitTemp.pdf";
			String finalPdfName = "ABITSearchResults.pdf";
			Document document = new Document(PageSize.A4.rotate(), 50, 50,50, 50);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileUploadPath + pdfName));
			writer.setEncryption(PdfWriter.STRENGTH40BITS, null, null, PdfWriter.AllowCopy | PdfWriter.AllowPrinting);	
			String publishDate = null;
			Date currentDate = new Date();		
			publishDate = sdf.format(currentDate);
			
			GregorianCalendar calendar = new GregorianCalendar();
			int global_year = calendar.get(java.util.Calendar.YEAR);
			
			//preparing footer text
			String footerStr = "COPYRIGHT" + " � 1997-" + global_year + " General Electric Company. All Rights Reserved " 
			+ "                                     DATE PRINTED   " + publishDate
	        + "                                                                                   "
	        + "                                                              Page No: ";
			HeaderFooter footer = new HeaderFooter(new Phrase(footerStr, FontFactory.getFont(FontFactory.HELVETICA, 
					7, Font.BOLD)), true);
			footer.setBorderColor(Color.LIGHT_GRAY);
			footer.setBorderColorTop(Color.WHITE);
			footer.setBorderColorBottom(Color.WHITE);
			document.setFooter(footer);
			
			document.open();
			String paras = "";
			Paragraph pa = new Paragraph(paras);
			Chapter chapter = new Chapter(pa, 1);
			chapter.setNumberDepth(-1);
					
			// Preparing Result Set Table
			
			Table resultTable = new Table(2);
			resultTable.setTableFitsPage(true);
			resultTable.setPadding(1);
			resultTable.setWidth(100);
			resultTable.setWidths(new int[]{10, 90});
			resultTable.setBorderWidth(0);
			resultTable.setBorderColor(Color.BLACK);			
			Cell cell = new Cell();
			cell = new Cell(new Paragraph("Item ID", new Font(Font.HELVETICA, 9)));
			cell.setHeader(true);
			cell.setBorderColor(Color.BLACK);
			cell.setBackgroundColor(Color.YELLOW);
			resultTable.addCell(cell);
			cell = new Cell(new Paragraph("Item Comments", new Font(Font.HELVETICA, 9)));
			cell.setHeader(true);
			cell.setBorderColor(Color.BLACK);
			cell.setBackgroundColor(Color.YELLOW);
			resultTable.addCell(cell);			
			
			/*for (PLMABITData resultRow : searchResultList) {
				cell = new Cell(new Paragraph(resultRow.getItemID(), new Font(Font.HELVETICA, 7)));
				cell.setBorderColor(Color.BLACK);
				cell.setBackgroundColor(Color.WHITE);
				resultTable.addCell(cell);
				cell = new Cell(new Paragraph(resultRow.getItemComments(), new Font(Font.HELVETICA, 7)));
				cell.setBorderColor(Color.BLACK);
				cell.setBackgroundColor(Color.WHITE);
				resultTable.addCell(cell);
			}*/
			
			chapter.add(resultTable);
			
			// Ends Result Set Table
			
			document.add(chapter);
			document.close();
			downloadPdfInline(pdfName, finalPdfName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void downloadPdfInline(String pdfName, String finalPdfName) throws Exception{

		// Prepare.
		removeBlankPages(pdfName, finalPdfName);
		File pdfFile = new File(fileUploadPath, finalPdfName);
		LOG.info("File Upload path::" + pdfFile.getAbsolutePath());
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.setContentType("application/pdf");
		// Download file.
		try {
			//FileUtils.downloadFile(response, pdfFile, true);
			File tempFile = new File(fileUploadPath, pdfName);
			if(tempFile.exists()) {
				tempFile.delete();
			}
		} catch (Exception e) {
			// Do your error handling thing.
			e.printStackTrace();
			
			return;
		}

		// Prevent other JSF lifecycle phases eventually being invoked.
		// Otherwise you can get the following exception:
		// java.lang.IllegalStateException: Cannot forward after response has
		// been committed.
		context.responseComplete();
	}
	
	public static void removeBlankPages(String sourceName, String finalPdfName) throws Exception {    
        try 
        {   // step 1: create new reader 
        	String pdfSourceFile=fileUploadPath+sourceName;
        	String pdfDestinationFile=fileUploadPath+finalPdfName;
            PdfReader r = new PdfReader(pdfSourceFile); 
            RandomAccessFileOrArray raf = new RandomAccessFileOrArray(pdfSourceFile); 
            Document document = new Document(r.getPageSizeWithRotation(1)); 
            // step 2: create a writer that listens to the document 
            PdfCopy writer = new PdfCopy(document, new FileOutputStream(pdfDestinationFile)); 
            // step 3: we open the document 
            document.open(); 
            // step 4: we add content 
            PdfImportedPage page = null; 
 
 
            //loop through each page and if the bs is larger than 20 than we know it is not blank. 
            //if it is less than 20 than we don't include that blank page. 
            for (int i=1;i<=r.getNumberOfPages();i++) 
            { 
                //get the page content 
                byte bContent [] = r.getPageContent(i,raf); 
                ByteArrayOutputStream bs = new ByteArrayOutputStream(); 
                //write the content to an output stream 
                bs.write(bContent); 
                System.out.println("page content length of page "+i+" = "+bs.size()); 
                //add the page to the new pdf 
                if (bs.size() > 20) 
                { 
                    page = writer.getImportedPage(r, i); 
                    writer.addPage(page); 
                } 
                bs.close(); 
            } 
            //close everything 
            document.close(); 
            writer.close(); 
            raf.close(); 
            r.close(); 
        } catch(Exception e) { 
        	e.printStackTrace();
        } 
    }
}
