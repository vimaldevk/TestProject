package stepdefs;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FlipkartSearch {

	WebDriver driver;
	WebDriverWait wait;
	
	@Before
	public void setup() {
		Reporter.assignAuthor("Vimal");
	}
	
	@Given("^Browser is opened and Flipkart application is loaded$")
	public void browser_is_opened_and_Flipkart_application_is_loaded() throws Throwable {
	    System.setProperty("webdriver.chrome.driver", "F:\\Selenium\\webdriver\\chromedriver.exe");
	    driver=new ChromeDriver();
	    driver.manage().window().maximize();
	  
	    driver.get("https://www.flipkart.com/");
	    try {
	   	 //   if(driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).isDisplayed()) {
	   	    	//wait=new WebDriverWait(driver, 20);
	   	    	//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='_2KpZ6l _2doB4z']")));
	   	    	driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
	   	    	System.out.println("Login dialogue box displayed and closed");
	   	    }
	   	    catch(Exception e) {
	   	    	System.out.println("Login dialogue box not displayed");
	   	    }
	    Assert.assertEquals(driver.getTitle(),"Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
	}

	@When("^Search condition entered$")
	public void search_condition_entered() throws Throwable {
		Reporter.addStepLog("user enters text");
	    WebElement searchText=driver.findElement(By.xpath("//input[@name='q']"));
	    searchText.sendKeys("mobile");
	    searchText.submit();
	}

	@Then("^Search result should be displayed$")
	public void search_result_should_be_displayed() throws Throwable {
		Thread.sleep(4000);
	    String mobileTitle=driver.getTitle();
	    Assert.assertEquals(mobileTitle, "obile - Buy Products Online at Best Price in India - All Categories | Flipkart.com");
	}

	@Then("^Close Browser$")
	public void close_Browser() throws Throwable {
	    driver.close();
	}
	
	@After
	public void afterScenario(Scenario scenario) throws IOException {
		Reporter.addScenarioLog("In case of failure takes screenshot");
		if(scenario.isFailed()) {
			String screenshotname=scenario.getName().replaceAll(" ", "_");
			TakesScreenshot screen=(TakesScreenshot)driver;
			File srcPath=screen.getScreenshotAs(OutputType.FILE);
			File destPath=new File(System.getProperty("user.dir")+"//target//html//"+screenshotname+".png");
			FileUtils.copyFile(srcPath, destPath);
			Reporter.addScreenCaptureFromPath(destPath.toString());
		}
	}
	

	
}
