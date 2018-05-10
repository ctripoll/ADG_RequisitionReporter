/**
 * 
 */
package adg.nonpostedreport.nonpostedreporter;

import java.io.IOException;
import java.text.ParseException;

import adg.nonpostedreport.io.ExcelReader;
import adg.nonpostedreport.io.TextWriter;
import adg.nonpostedreport.requsition.Requisition;
import adg.nonpostedreport.util.ArrayBasedList;

/**
 * Takes in the two give excel files to parse into arrays. Then compares the
 * arrays to find the inconsistent requisitions (the requisitions that are in
 * one but not the other - the nonposted requisitions).
 * 
 * @author Courtney Ripoll
 *
 */
public class NonpostedReporter {

	/**
	 * This method calls all the functionality of the program. It takes in two
	 * file names to receive the lists of requisitions to compare. It will get
	 * the list of current ADG requisitions and all current requisitions. Then
	 * these lists will be compared to generate a list of nonposted
	 * requisitions. These requisitions will be written to a new excel file and
	 * posted on the Desktop.
	 * 
	 * @param fileName
	 *            desired file name given by the user
	 * @param currentADGJobsFile
	 *            file name for the Excel Workbook of current ADG Requisitions
	 * @param myCurrentReqsFile
	 *            file name for the Excel Workbook of all current requisitions
	 * @param filter
	 *            if not null, filters the results by requested by
	 */
	public NonpostedReporter(String fileName, String currentADGJobsFile, String myCurrentReqsFile, String filter) {
		if (fileName == null || fileName.equals("") || currentADGJobsFile == null || myCurrentReqsFile == null) {
			throw new IllegalArgumentException();
		}
		ArrayBasedList<Requisition> currentADGJobs = ExcelReader.excelReader(currentADGJobsFile);
		ArrayBasedList<Requisition> myCurrentReqs = ExcelReader.excelReader(myCurrentReqsFile);
		ArrayBasedList<Requisition> finalReport = compareLists(currentADGJobs, myCurrentReqs);

		ArrayBasedList<Requisition> filtered = new ArrayBasedList<Requisition>();
		if (filter != null) { // If filtering by requested by
			for (int i = 0; i < finalReport.size(); i++) {
				if (finalReport.get(i).getRequestedBy().equals(filter.trim())) {
					filtered.add(finalReport.get(i));
				}
			}
			try {
				TextWriter.writeExcelReport(fileName, filtered);
			} catch (IOException | ParseException e) {
				throw new IllegalArgumentException();
			}
		} else { // If no filter
			try {
				TextWriter.writeExcelReport(fileName, finalReport);
			} catch (IOException | ParseException e) {
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * Compares the two given lists to find the nonposted requisitions. This
	 * method takes currentADGJobs and compares it to myCurrentReqs to see which
	 * requisitions myCurrentReqs has and currentADGJobs does not contain. If a
	 * requisition is not in currentADGJobs, then it is not posted. This
	 * requisition will be put into a list to later report.
	 * 
	 * @param currentADGJobs
	 *            list of current ADG Requisitions
	 * @param myCurrentReqs
	 *            list of all current requisitions
	 * @return a list of nonposted requisitions
	 */
	private ArrayBasedList<Requisition> compareLists(ArrayBasedList<Requisition> currentADGJobs,
			ArrayBasedList<Requisition> myCurrentReqs) {
		ArrayBasedList<Requisition> report = new ArrayBasedList<Requisition>();
		for (int i = 0; i < myCurrentReqs.size(); i++) {
			if (!currentADGJobs.contains(myCurrentReqs.get(i))) {
				report.add(myCurrentReqs.get(i));
			}
		}
		return report;
	}
}
