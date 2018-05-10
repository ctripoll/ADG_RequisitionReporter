/**
 * 
 */
package adg.nonpostedreport.io;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

import adg.nonpostedreport.requsition.Requisition;
import adg.nonpostedreport.util.ArrayBasedList;

/**
 * Tests the Excel file reader class.
 * 
 * @author Courtney Ripoll
 *
 */
public class ExcelReaderTest {

	/** Test file */
	private final String validTestFile = "test/test-file.xlsx";

	/** Expected requisitions */
	private final Requisition req1 = new Requisition("720", "Office Manager - Franchise Automotive Dealership",
			"Sanford, NC", "Open", "Tony Cottrell",
			"Cook, Jamie; ADGAdmin; English, Greg; Mundie, Daniel; Ward, Rob; Pantlin, Sally");
	private final Requisition req2 = new Requisition("719",
			"Service Technician - Automotive - Thompson Buick GMC Cadillac (GM)", "Raleigh, NC", "Open",
			"Tony Cottrell", "Cook, Jamie; ADGAdmin; English, Greg; Mundie, Daniel; Ward, Rob; Pantlin, Sally");
	private final Requisition req3 = new Requisition("718",
			"Automotive Technician - Quick Lube - Thompson Buick GMC Cadillac (GM)", "Raleigh, NC", "Open",
			"Tony Cottrell", "Cook, Jamie; ADGAdmin; English, Greg; Mundie, Daniel; Ward, Rob; Pantlin, Sally");
	private final Requisition req4 = new Requisition("717", "F&I Specialist - ADG EasyCare", "Bowie, MD", "Open",
			"Rob Ward", "Cook, Jamie; English, Greg; Ward, Rob; Hensley, John");

	/** Array to hold expected results */
	private final Requisition[] reqs = { req1, req2, req3, req4 };

	/**
	 * Resets the test-file in case of any modifications or changes made to the
	 * file while testing.
	 * 
	 * @throws java.lang.Exception
	 *             exception thrown if files cannot be reset
	 */
	@Before
	public void setUp() {
		Path sourcePath = FileSystems.getDefault().getPath("test", "start-test-file.xlsx");
		Path destinationPath = FileSystems.getDefault().getPath("test", "test-file.xlsx");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Could not reset files");
		}
	}

	/**
	 * Test method for
	 * {@link adg.nonpostedreport.io.ExcelReader#excelReader(java.lang.String)}.
	 * 
	 * @throws FileNotFoundException
	 *             exception thrown if file cannot be found
	 */
	@Test
	public void testExcelReader() throws FileNotFoundException {
		ArrayBasedList<Requisition> y = ExcelReader.excelReader(validTestFile);
		assertEquals(4, y.size());
		for (int i = 0; i < reqs.length; i++) {
			Requisition x = (Requisition) y.get(i);
			assertEquals(reqs[i].getJobCode(), x.getJobCode());
			assertEquals(reqs[i].getTitle(), x.getTitle());
			assertEquals(reqs[i].getLocation(), x.getLocation());
			assertEquals(reqs[i].getStatus(), x.getStatus());
			assertEquals(reqs[i].getRequestedBy(), x.getRequestedBy());
			assertEquals(reqs[i].getReqOwners(), x.getReqOwners());
		}
	}

}
