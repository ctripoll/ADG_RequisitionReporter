/**
 * 
 */
package adg.nonpostedreport.nonpostedreporter;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Nonposted Reporter
 * 
 * @author Courtney Ripoll
 *
 */
public class NonpostedReporterTest {

	/** Test files */
	private final String validTestFile1 = "test/currentADGJobs.xlsx";
	private final String validTestFile2 = "test/myCurrentRequisitions.xlsx";

	/**
	 * Rests the Excel Workbook files, currentADGJobs and myCurrentRequisitions,
	 * in case of any modifications or changes made to the files while testing.
	 * 
	 * @throws java.lang.Exception
	 *             exception throw if the files cannot be reset
	 */
	@Before
	public void setUp() {
		Path sourcePath = FileSystems.getDefault().getPath("test", "start-currentADGJobs.xlsx");
		Path destinationPath = FileSystems.getDefault().getPath("test", "currentADGJobs.xlsx");
		Path sourcePath2 = FileSystems.getDefault().getPath("test", "start-myCurrentRequisitions.xlsx");
		Path destinationPath2 = FileSystems.getDefault().getPath("test", "myCurrentRequisitions.xlsx");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
			Files.deleteIfExists(destinationPath2);
			Files.copy(sourcePath2, destinationPath2);
		} catch (IOException e) {
			fail("Could not reset files");
		}
	}

	/**
	 * Tests the main functionality of the NonpostedReporter
	 */
	@Test
	public void test() {
		// No filter
		NonpostedReporter npr = new NonpostedReporter("report", validTestFile1, validTestFile2, null);
		assertNotNull(npr);
		// Filter test
		NonpostedReporter npr1 = new NonpostedReporter("js-report", validTestFile1, validTestFile2, "Justin Cardwell");
		assertNotNull(npr1);
		
		NonpostedReporter invalid = null;
		try {
			invalid = new NonpostedReporter(null, validTestFile1, validTestFile2, null);
		} catch (IllegalArgumentException e) {
			assertNull(invalid);
		}
		try {
			invalid = new NonpostedReporter("report1", null, validTestFile2, null);
		} catch (IllegalArgumentException e) {
			assertNull(invalid);
		}
		try {
			invalid = new NonpostedReporter("report1", validTestFile1, null, null);
		} catch (IllegalArgumentException e) {
			assertNull(invalid);
		}
	}

}
