//package testngTests;
//
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//
//public class MyListeners implements ITestListener{
//	
//	public ExtentSparkReporter sparkReporter;
//	public ExtentReports extent;
//	public ExtentTest test;
//	
//	public void onStart(ITestContext context) {
//
//	System.out.println("Execution is started.....");
//	sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"reports/testReports.html");
//	sparkReporter.config().setDocumentTitle("Automation Title");
//	sparkReporter.config().setTheme(Theme.DARK);
//	sparkReporter.config().setReportName("Jamser Ali");
//	
//	extent = new ExtentReports();
//	extent.attachReporter(sparkReporter);
//	extent.setSystemInfo("Computer Name", "Local Host");
//	extent.setSystemInfo("Environment", "QA");
//	extent.setSystemInfo("Tester Name", "Jamser Ali");
//	extent.setSystemInfo("OS", "Windows 11");
//	extent.setSystemInfo("Browser Name", "Chrome");
//	
//	}
//	
////	  public void onTestStart(ITestResult result) {
////			System.out.println("Test execution is started.....");
////			
////
////	  }
//	
//	public void onTestSuccess(ITestResult result) {
//		System.out.println("Test successful.....");
//		test = extent.createTest(result.getName());
//		test.log(Status.PASS, "Test case create is :"+ result.getName());
//	
//		
//		test = extent.createTest(result.getName());
//		test.log(Status.PASS,"Test Pass is"+result.getName());
//		
//		  }
//	 
//	 public void onTestFailure(ITestResult result) {
//			System.out.println("Test Failled.....");
//			test = extent.createTest(result.getName());
//			test.log(Status.FAIL, "Test case FAIL is :"+ result.getName());
//			test.log(Status.FAIL, "Test case FAIL is :"+ result.getThrowable());
//
//				
//		  }
//	 public void onTestSkipped(ITestResult result) {
//			System.out.println("Test Skiped.....");
//			
//		  }
//	 
//	 public void onFinish(ITestContext context) {
//			System.out.println("Test Skipped.....");
//			extent.flush();
//		  }
//
//}

package testngTests;

import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MyListeners implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    public void onStart(ITestContext context) {
        System.out.println("Execution started...");

        String reportPath = System.getProperty("user.dir") + "/reports/testReports.html";
        File reportDir = new File(System.getProperty("user.dir") + "/reports");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Jamser Ali");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Computer Name", "Local Host");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester Name", "Jamser Ali");
        extent.setSystemInfo("OS", "Windows 11");
        extent.setSystemInfo("Browser Name", "Chrome");
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
        test = extent.createTest(result.getName());
        test.log(Status.PASS, "Test Passed: " + result.getName());
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
        test = extent.createTest(result.getName());
        test.log(Status.FAIL, "Test Failed: " + result.getName());
        test.log(Status.FAIL, "Reason: " + result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
        test = extent.createTest(result.getName());
        test.log(Status.SKIP, "Test Skipped: " + result.getName());
    }

    public void onFinish(ITestContext context) {
        System.out.println("Execution finished.");
        extent.flush();
    }
}

