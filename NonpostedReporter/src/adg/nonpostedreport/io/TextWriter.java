/**
 * 
 */
package adg.nonpostedreport.io;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFBorderFormatting;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import adg.nonpostedreport.requsition.Requisition;
import adg.nonpostedreport.util.ArrayBasedList;

/**
 * Writes retrieved data from Nonposted Report into an excel file. Writes a
 * report (in .xlsx format) listing all requisitions that are not currently
 * posted.
 * 
 * @author Courtney Ripoll
 *
 */
public class TextWriter {

	/**
	 * Writes all the nonposted requisitions into a excel file. The program puts
	 * the generated excel file onto the current users desktop directory.
	 * 
	 * @param fileName
	 *            desired file name given by the user
	 * @param reqs
	 *            array list of requisitions to print out
	 * @throws IOException
	 *             exception thrown if there is an error with writing the file
	 * @throws ParseException
	 *             exception thrown if there is an error with the date
	 */
	@SuppressWarnings("deprecation")
	public static void writeExcelReport(String fileName, ArrayBasedList<Requisition> reqs)
			throws IOException, ParseException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		Date date = new Date();
		String todaysDate = date.toString().substring(0, 10);
		// Get and input data for the sheet into an array for storage
		XSSFSheet sheet = workbook.createSheet("Nonposted Reqs " + todaysDate);
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("1", new Object[] { "Title", "Job Code", "Status", "Location", "Requested By" });
		for (int i = 0; i < reqs.size(); i++) {
			Requisition r = reqs.get(i);
			data.put((i + 2) + "",
					new Object[] { r.getTitle(), r.getJobCode(), r.getStatus(), r.getLocation(), r.getRequestedBy() });
		}
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			// Title row set to bold and light olive green
			final XSSFCellStyle row1style = (XSSFCellStyle) workbook.createCellStyle();
			XSSFFont bold = (XSSFFont) workbook.createFont();
			bold.setBold(true);
			row1style.setBorderBottom(XSSFBorderFormatting.BORDER_THIN);
			row1style.setFont(bold);
			row1style.setFillForegroundColor(new XSSFColor(Color.decode("#cbd1a1")));
			row1style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			// Apply data to the sheet
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				if (rownum == 1) {
					Cell cell = row.createCell(cellnum++);
					cell.setCellStyle(row1style);
					cell.setCellValue((String) obj);
				} else {
					Cell cell = row.createCell(cellnum++);
					cell.setCellValue((String) obj);
				}
			}
		}
		// Auto size the columns for readability
		int numCols = 5;
		for (int i = 0; i < numCols; i++) {
			sheet.autoSizeColumn(i);
		}
		// Write to a file
		String userHomeFolder = System.getProperty("user.home") + "/Desktop";
		PrintStream fileWriter = new PrintStream(new File(userHomeFolder, fileName + ".xlsx"));
		workbook.write(fileWriter);
		fileWriter.close();
		workbook.close();
	}
}
