package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.BaseTest;
import keyword.ExcelUtils;
import keyword.ReadObject;

public class LoginTests extends BaseTest{
	String OBJECTNAME = "objects";
	Properties objectRepo;
	ExcelUtils wb;
    ExtentTest test;
    ExtentReports extent;
    ExtentSparkReporter spark;
    HashMap<String, Boolean> dependenceTestCases;

	
	public LoginTests() {
		dependenceTestCases = new HashMap<String, Boolean>();
		dependenceTestCases.put("", true);
		try {
			objectRepo = ReadObject.getObjectRepository(OBJECTNAME);
			wb = new ExcelUtils();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	//builds a new report using the html template 
    
    //helps to generate the logs in test report.
    
    @Parameters({ "OS", "browser" })
    @BeforeTest
    public void startReport(String OS, String browser) {
    	
        extent = new ExtentReports();
    	
        spark = new ExtentSparkReporter("index2.html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("MyReport");
        extent.attachReporter(spark);         
        
        //To add system or environment info by using the setSystemInfo method.
        extent.setSystemInfo("OS", OS);
        extent.setSystemInfo("Browser", browser);
    }
    
    @AfterMethod
    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
        	
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
        }
        else {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }
     
    @AfterTest
    public void tearDown() {
    	//to write or update test information to reporter
        extent.flush();
    }
	
	@DataProvider(name="data-provider")
	public Object[][] dpMethod() throws Exception{
		return wb.getTableArray("TestSuite");
	}
	
	@Test(dataProvider = "data-provider")
	public void masterTest(String[] testCase) throws Exception {
		
		String tc_run = testCase[0];
		String tc_name = testCase[1];
		String tc_dependence = testCase[2];
		String tc_desc = testCase[3];
		
        test = extent.createTest(tc_name);
        test.log(Status.INFO, tc_desc);
        System.out.println("extent: " + extent.getReport());
        dependenceTestCases.put(tc_name, false);
        if (tc_run.equalsIgnoreCase("N")) {
            throw new SkipException("This test will not be run");
        }
        if (!wb.checkWorkSheet(tc_name)) {
        	throw new SkipException("This test will not be run (worksheet not found)");
        }
        if (!dependenceTestCases.get(tc_dependence)) {
        	throw new SkipException("This test will not be run (parent test didnot pass test)");
        }
        
        //System.out.println(tc_name);
		String[][] ws = wb.getTableArray(tc_name);
		for (int i = 0; i < ws.length; i++) {
			test.log(Status.INFO, ws[i][0] + "----" + ws[i][1] + "----" + ws[i][2] + "----" + ws[i][3] + "----" + ws[i][4]);
			this.getKw().perform(this.getDriver(), objectRepo, ws[i][1], ws[i][2], ws[i][3], ws[i][4]);
		}
		System.out.println(this.getKw().getAssertValue());
		dependenceTestCases.put(tc_name, this.getKw().getAssertValue());
	}

	
}

