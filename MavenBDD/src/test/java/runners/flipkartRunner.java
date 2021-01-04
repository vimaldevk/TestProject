package runners;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
//@ExtendedCucumberOptions(
//
//		detailedReport=true,
//		detailedAggregatedReport = true,
//		usageReport = true,
//		toPDF=true,
//		outputFolder="target"
//		)
@CucumberOptions(
		features="src//test//resources//Feature//FlipkartFeature.feature",
		glue= {"stepdefs"},
		dryRun = false,
		strict= true,
		monochrome = true,
		plugin= {
				// "pretty",
				//"usage:target//cucumber-reports"
				//"html:target//html-reports"
				
				"com.cucumber.listener.ExtentCucumberFormatter:target/html/ExtentReport.html"
				}
		
		)


public class flipkartRunner {
	@AfterClass
	public static void writeExtentReport() {
		System.out.println("Extent Report name "+System.getProperty("user.name"));
		Reporter.loadXMLConfig(new File(System.getProperty("user.dir")+"/src/test/resources/extent-config.xml"));
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("Application Name", "Sample Test App");
		Reporter.setSystemInfo("OS", System.getProperty("os.name").toString());
		Reporter.setSystemInfo("Environment", "Testing Server");
	}
}
