/**
 * 
 */
package adg.nonpostedreport.io;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import adg.nonpostedreport.requsition.Requisition;
import adg.nonpostedreport.util.ArrayBasedList;

/**
 * Test class for the text writer
 * 
 * @author Courtney Ripoll
 *
 */
public class TextWriterTest {

	/** Test file */
	private final String validTestFile = "test/test-file.xlsx";

	/**
	 * Rests the test-file in case of any modifications or changes made to the
	 * file while testing.
	 * 
	 * @throws java.lang.Exception
	 *             exception throw if files cannot be reset
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
	 * {@link adg.nonpostedreport.io.TextWriter#writeReport(java.lang.String, adg.nonpostedreport.util.ArrayBasedList)}.
	 */
	@Test
	public void testWriteReport() {
		ArrayBasedList<Requisition> y = ExcelReader.excelReader(validTestFile);
		try {
			TextWriter.writeExcelReport("requisition-test-excel", y);
		} catch (IOException | ParseException e) {
			assertNotNull(y);
		}
	}

}
