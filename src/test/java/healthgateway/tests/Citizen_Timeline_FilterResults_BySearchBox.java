package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.TimeLineTabPage;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class Citizen_Timeline_FilterResults_BySearchBox extends BaseTest {

	//Data for user11
	private int numberOfLabResultsRecords = 133;
	private int numberOfAllRecords = 931;

	@Test
	public void Can_Do_TimeLine_Filtering_using_the_Search_Box() throws Exception {
		TestcaseID = "322281"; //Original TC C322281
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		//Login as user 11
		log("/*1.---Login as user 11 ---*/");
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//GoTo TimeLines
		log("/*2.---GoTo TimeLines ---*/");
		TimeLineTabPage timeLine = mainPageHealthGateway.goToTabTimeLine();

		//Search by keyword
		log("/*3.---lab results ---*/");
		timeLine.filterBySearch("lab results");

		//Validate number of records matched by keyword
		log("/*4.---Validate number of records matched by keyword ---*/");
		log(timeLine.getDisplayingNumberOfRecords());
		Assert.assertTrue(timeLine.getNumberOfRecordsRegex() == numberOfLabResultsRecords, "Number of records displayed after search didn't match!");

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