package datadriventesting;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataDrivenTest {
	
	public String TEST_DATA_SHEET ="./src/test/resources/testdata/loginData.xlsx";

	WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().fullscreen();
		
	}
	
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	
	
	public void doSendKeys(By locator, String value) {
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	@Test(dataProvider="getData")
	public void loginTest(String user, String pwd, String exp) {
		
		//System.out.println(user + " " + pwd + " " + exp);
		
		driver.get("https://admin-demo.nopcommerce.com/login");
		
		By username = By.id("Email");
		By password = By.id("Password");
		By logout = By.linkText("Logout");
		By submitBtn = By.xpath("//input[@type='submit' and @value='Log in']");
		
		
		
		doSendKeys(username, user);
		doSendKeys(password, pwd);
		doClick(submitBtn);
		
		String exp_title = "Dashboard / nopCommerce administration";
		String actual_title = getTitle();
		
		if(exp.equals("valid")) {
			if(exp_title.equals(actual_title)) {
				
				doClick(logout);
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		}
		else if(exp.equals("invalid")) {
			if(exp_title.equals(actual_title)) {
				doClick(logout);
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
			}
		}
		
		
	}
	
	
	@DataProvider
	public String[][] getData() throws IOException {
		
//		String loginData[][] = {
//			{"admin@yourstore.com",	"admin", "valid"},
//			{"admin@yourstore.com"	,"adm",	"invalid"},
//			{"adm@yourstore.com",	"admin",	"invalid"},
//			{"adm@yourstore.com",	"adm",	"invalid"}
//		
//		};
//		
//		return loginData;
		
		
		
		XLUtility xlUtil = new XLUtility(TEST_DATA_SHEET);
		
		int total_rows = xlUtil.getRowCount(Constants.LOGIN_SHEET_NAME);
		int total_cols = xlUtil.getCellCount(Constants.LOGIN_SHEET_NAME, 0);
		
		String loginData[][] = new String[total_rows][total_cols];
		
		for(int i=0; i<total_rows; i++) {
			
			for(int j=0; j<total_cols; j++) {
				
				
				loginData[i][j] = xlUtil.getCellData(Constants.LOGIN_SHEET_NAME, i+1, j);
				
			}
		}
		
		
		
		return loginData;
		
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
	
	
	
}
