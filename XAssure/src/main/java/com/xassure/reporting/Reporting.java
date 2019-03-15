package com.xassure.reporting;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Reporting {

	public static ExtentReports reporter;
	public static Map<Long, String> threadToTestCaseMap = new HashMap<Long, String>();
	public static Map<String, ExtentTest> nameToExtentTestMap = new HashMap<String, ExtentTest>();

	private static String reportLocation = "./reports/";
	private static String reportName = "/TestReports.html";
	private static String errorLogsLoc = "";

	/*
	 *
	 * Create Extent Reports Object:-
	 */
	private synchronized static ExtentReports initiateReporting() {
		if (reporter == null) {

			reporter = new ExtentReports(new File(reportLocation + reportName).getAbsolutePath(), true,
					DisplayOrder.NEWEST_FIRST);
		}
		return reporter;
	}

	/*
	 *
	 * Initiate Extent test logger for test initialization:-
	 */
	public synchronized static ExtentTest initiateTestLogger(String testName) {

		if (!nameToExtentTestMap.containsKey(testName)) {
			Long threadID = Thread.currentThread().getId();
			ExtentTest test = initiateReporting().startTest(testName);
			nameToExtentTestMap.put(testName, test);
			threadToTestCaseMap.put(threadID, testName);
		}
		return nameToExtentTestMap.get(testName);
	}

	/*
	 * Fetch the Extent Test Logger at runtime for the respective test case:-
	 */
	public synchronized static ExtentTest getLogger(String testName) {
		return initiateTestLogger(testName);
	}

	/*
	 * At any given stage we need to fetch the details of Test Method:-
	 *
	 */
	public synchronized static ExtentTest getLogger() {
		Long threadID = Thread.currentThread().getId();

		if (threadToTestCaseMap.containsKey(threadID)) {
			String testName = threadToTestCaseMap.get(threadID);
			return nameToExtentTestMap.get(testName);
		}
		return null;
	}

	/*
	 * Generate the reporting for the test case:-
	 */
	public synchronized static void generateTestReport(String testName) {

		if (!testName.isEmpty()) {
			ExtentTest test = getLogger(testName);
			initiateReporting().endTest(test);
		}
	}

	/*
	 * Generate reports for the logger:-
	 */
	public synchronized static void generateReport() {
		if (reporter != null) {
			reporter.flush();
			reporter.close();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static String createScreenshot(WebDriver driver) {
		Long threadID = Thread.currentThread().getId();
		String testName = threadToTestCaseMap.get(threadID);

		UUID uuid = UUID.randomUUID();

		// generate screenshot as a file object
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			// copy file object to designated location
			FileUtils.copyFile(scrFile, new File(reportLocation + "/" + uuid + testName + ".png"));
			System.out.println("Screenshot Capture: " + reportLocation + "/" + uuid + testName + ".png");
		} catch (IOException e) {
			System.out.println("Error while generating screenshot:\n" + e.toString());
		}
		return uuid + testName + ".png";

	}

	/**
	 * Generates a unique folder based upon our run:-
	 */
	public static void createReportDirectory() {

		String runID = new Reporting().generateUniqueRunId();
		File file = new File(reportLocation + runID);
		if (!file.exists()) {
			if (!file.exists()) {
				if (file.mkdir()) {
					reportLocation = reportLocation + runID;
					errorLogsLoc = reportLocation + "/errorLogs";
					if (!errorLogsLoc.isEmpty()) {
						File errorLogs = new File(errorLogsLoc);
						if (!errorLogs.exists()) {
							if (!errorLogs.mkdir()) {

								System.out.print(
										"Unable to create error logs directory for reportlocation: " + reportLocation);
							}
						}
					}
				} else {
					System.out.println("Failed to create directory!");
				}
			}

		}

	}

	/**
	 * On each call of this function, it randomly generates a unique run id
	 * (combination of 6 digits).
	 *
	 * @return String runId
	 */
	private String generateUniqueRunId() {
		String runID = "";
		try {
			// Randomly generates 6 digits unique number.
			Random random = new Random();
			int n = 100000 + random.nextInt(900000);
			runID = String.valueOf(n);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return runID.trim();
	}

	/**
	 * Get Error Logs location:-
	 */

	public String getErrorLogsLocation() {
		return this.errorLogsLoc;
	}

}
