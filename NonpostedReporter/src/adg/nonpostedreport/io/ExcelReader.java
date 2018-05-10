/**
 * 
 */
package adg.nonpostedreport.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import adg.nonpostedreport.requsition.Requisition;
import adg.nonpostedreport.util.ArrayBasedList;

/**
 * Reads in an excel file with the appropriate requisition information;
 * including job code, title, last updated, location, status, requested by, and
 * requisition owners. Only takes in excel files. Other files will report an
 * exception.
 * 
 * @author Courtney Ripoll
 *
 */
public class ExcelReader {

	/**
	 * A static class that takes in the given fileName (should be an Excel
	 * Workbook file) and parses each row's cells to get the needed information.
	 * It should receive a job code, a title, a location, a status, a requested
	 * by name, and a list of requisition owners. If not, the method will throw
	 * the appropriate exception.
	 * 
	 * @param fileName
	 *            Excel Workbook file name
	 * @return an array list of received requisitions
	 */
	public static ArrayBasedList<Requisition> excelReader(String fileName) {
		ArrayBasedList<Requisition> reqs = new ArrayBasedList<Requisition>();
		try {
			FileInputStream excelFile = new FileInputStream(new File(fileName));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet dataSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = dataSheet.iterator();
			iterator.next();
			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				String jobCode = ((int) cellIterator.next().getNumericCellValue()) + "";
				if (jobCode.equals("0")) {
					// last, empty line
					break;
				}
				String title = cellIterator.next().getStringCellValue();
				cellIterator.next(); // candidates
				cellIterator.next(); // last updated
				String location = cellIterator.next().getStringCellValue();
				String status = cellIterator.next().getStringCellValue();
				String requestedBy = cellIterator.next().getStringCellValue();
				String reqOwners = cellIterator.next().getStringCellValue();
				if (status.equals("Open")) {
					reqs.add(new Requisition(jobCode, title, location, status, requestedBy, reqOwners));
				}
			}
			workbook.close();
			excelFile.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		} catch (IOException e) {
			throw new IllegalArgumentException();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
		return reqs;
	}
}
