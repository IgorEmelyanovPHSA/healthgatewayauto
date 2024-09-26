package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.ExportTabPage;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class Export_Filtering_ByDateRange extends BaseTest {

	//Data for user11
	private int numberOfRecordsExpectedForDateRange = 7;
	private int numberOfAllRecords = 10;
	private String textSearchStartDate = "Jan 1, 2020";
	private String textSearchEndDate = "Jun 1, 2024";

	@Test
	public void Can_do_Filtering_Export_data_by_Date_Range() throws Exception {
		TestcaseID = "322313"; //Original TC C322313
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		//Login as user 11
		log("/*1.---Login as user 11 ---*/");
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//GoTo Export tab
		log("/*2.---GoTo Export tab ---*/");
		ExportTabPage export = mainPageHealthGateway.goToTabExport();

		//Immunization dropDown must be selected first
		log("/*3.---Immunization dropDown must be selected first ---*/");
		export.filterByImmunization();

		//Search by the date range
		log("/*4.---Search by the date range ---*/");
		export.filterByStartAndEndDate(textSearchStartDate, textSearchEndDate);

		//Validate number of records matched by date range
		log("/*5.---Validate number of records matched by date range ---*/");
		Assert.assertTrue( export.getNumberOfRecords() == numberOfRecordsExpectedForDateRange, "Number of records displayed after date range search didn't match!");

		//Remove search results
		log("/*6.---Remove search results ---*/");
		export.removeSearchResults();

		//Validate total count of records
		log("/*7.---Validate total count of records ---*/");
		Assert.assertTrue(export.getNumberOfRecords() == numberOfAllRecords, "Total number of all records displayed didn't match!");

		log("Filtering using the search box pass successfully");
		}

	}