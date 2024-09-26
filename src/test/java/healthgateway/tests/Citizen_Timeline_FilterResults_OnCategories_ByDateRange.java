package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.TimeLineTabPage;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class Citizen_Timeline_FilterResults_OnCategories_ByDateRange extends BaseTest {

	//Data for user11
	private int numberOfRecordsExpectedForDateRange = 10;
	private int numberOfAllRecords = 931;
	private String textSearchStartDate = "Jan 1, 2023";
	private String textSearchEndDate = "Jun 1, 2024";

	@Test
	public void Can_do_TimeLine_Filtering_Based_on_Categories_by_DateRange() throws Exception {
		TestcaseID = "322282"; //Original TC C322282
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		//Login as user 11
		log("/*1.---Login as user 11 ---*/");
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//GoTo TimeLines
		log("/*2.---GoTo TimeLines ---*/");
		TimeLineTabPage timeLine = mainPageHealthGateway.goToTabTimeLine();

		//Medications option from Categories dropDown must be selected first
		//log("/*3.---Medications option from Categories dropDown must be selected first ---*/");
		//export.filterByImmunization();

		//Search by the date range
		log("/*3.---Search by the date range ---*/");
		timeLine.filterByStartAndEndDate(textSearchStartDate, textSearchEndDate);

		//Validate number of records matched by date range
		log("/*4.---Validate number of records matched by date range ---*/");
		log(timeLine.getDisplayingNumberOfRecords());
		Assert.assertTrue(timeLine.getNumberOfRecordsRegex() == numberOfRecordsExpectedForDateRange, "Number of records displayed after date range search didn't match!");

		//Remove search results
		log("/*5.---Remove search results ---*/");
		timeLine.removeSearchResults();

		//Validate total count of records
		log("/*6.---Validate total count of records ---*/");
		log(timeLine.getDisplayingNumberOfRecords());
		Assert.assertTrue(timeLine.getNumberOfRecordsRegex() >= numberOfAllRecords, "Total number of all records displayed didn't match!");
		log("Filtering using the search box pass successfully");
		}

	}