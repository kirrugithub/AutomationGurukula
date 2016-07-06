package gurukulaAutomation;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GurukulaTestNG3 {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();
  private WebDriverWait wait;
  private int i;
  
  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    i=1;
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @Test (description="Launching the site..")
  public void launch() throws Exception {
    driver.get(baseUrl + "/#/");
  }
  
  @Test (description="Executing Register new account Test Cases")
  public void registernewaccount() throws Exception {
	  	driver.findElement(By.linkText("Register a new account")).click();
	    driver.findElement(By.name("login")).clear();
	    driver.findElement(By.name("login")).sendKeys("user1");
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("email")).sendKeys("user1@servicenow.com");
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("userpwd");
	    driver.findElement(By.name("confirmPassword")).clear();
	    driver.findElement(By.name("confirmPassword")).sendKeys("userpwd");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    
	    wait = new WebDriverWait(driver, 1);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/strong")));
	    
	    String var = driver.findElement(By.xpath("//div[2]/strong")).getText();
	    if (var.contains("fail")){
	    	getscreenshot();
	    	throw new Exception("Because of some technical failure user is not registered");
	    }
  }
  
  @Test (description="Executing Login Test Cases")
  public void login() throws Exception {
    driver.findElement(By.linkText("login")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("admin");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
  }
  
  @Test (description="Executing Entities Test cases")
  public void entities() throws Exception {
	  	driver.findElement(By.cssSelector("span.glyphicon.glyphicon-th-list")).click();
	    driver.findElement(By.cssSelector("ul.dropdown-menu > li > a > span.ng-scope")).click();
	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	    driver.findElement(By.name("name")).clear();
	    driver.findElement(By.name("name")).sendKeys("MechanicalEng");
	    driver.findElement(By.name("code")).clear();
	    driver.findElement(By.name("code")).sendKeys("001");
	    driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();
	    driver.findElement(By.xpath("(//button[@type='submit'])[03]")).click();
	    driver.findElement(By.cssSelector("button.btn.btn-info")).click();
	    driver.findElement(By.cssSelector("span.hidden-tablet.ng-scope")).click();
	    driver.findElement(By.xpath("//div[@id='navbar-collapse']/ul/li[2]/ul/li[2]/a/span[2]")).click();
	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	    driver.findElement(By.name("name")).clear();
	    driver.findElement(By.name("name")).sendKeys("ABC");
	    new Select(driver.findElement(By.name("related_branch"))).selectByVisibleText("MechanicalEng");
	    driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();
  }
  
  @Test (description="Executing Account Test cases")
  public void account() throws Exception {
	  	driver.findElement(By.linkText("Account")).click();
	    driver.findElement(By.cssSelector("span.glyphicon.glyphicon-wrench")).click();
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("email")).sendKeys("admin@localhost.com");
	    driver.findElement(By.xpath("(//button[@type='submit'])")).click();
	    
	    wait = new WebDriverWait(driver, 1);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div[3]")));
	    
	    String var = driver.findElement(By.xpath("//div/div[3]")).getText();
	    String error = new String();
	    if (var.contains("error")){
	    	getscreenshot();
	    	error = "settings";
	    }
	    
	    driver.findElement(By.xpath("//div[@id='navbar-collapse']/ul/li[3]/a/span/span[2]")).click();
	    driver.findElement(By.xpath("//div[@id='navbar-collapse']/ul/li[3]/ul/li[2]/a/span[2]")).click();
	    driver.findElement(By.xpath("//div[@id='navbar-collapse']/ul/li[3]/a/span/span[2]")).click();
	    driver.findElement(By.xpath("//div[@id='navbar-collapse']/ul/li[3]/ul/li[3]/a/span[2]")).click();
	    
	    if(error.contains("settings")){
	    	throw new Exception("Because of some issues, settings cannot be changed.  Contact admin"); 
	    }
  }

  @Test (description="Executing Logout Test cases")
  public void logout() throws Exception{
	  	driver.findElement(By.xpath("//html")).click();
	  	driver.findElement(By.xpath("//div[@id='navbar-collapse']/ul/li[3]/a/span")).click();
	    driver.findElement(By.xpath("//div[@id='navbar-collapse']/ul/li[3]/ul/li[4]/a/span[2]")).click();
  }
  
  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  public void getscreenshot() throws Exception 
  {
	  	  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       //The below method will save the screen shot in d drive with name "screenshot.png"
          FileUtils.copyFile(scrFile, new File("screenshot"+i+++".png"));
  }
}
