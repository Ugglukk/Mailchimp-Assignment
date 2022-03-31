import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class MailChimpStepdefs {
    WebDriver driver;
    String remail = ("olakola." + randomLetters(2) + randomNumbers(2) + "@mail.com"); //creates random email starting with olakola. and ends with @mail.com
    String rpassword = (randomLetters(3) +"zK"+ randomNumbers(2) + randomSigns(1));//creates random password

    @Given("I have opened browser")
    public void iHaveOpenedBrowser() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Selenium/chromedriver.exe");
        driver = new ChromeDriver();    //Initiate chromedriver
        driver.get("https://login.mailchimp.com/signup/");  //Opens webpage
        driver.manage().window().maximize();    //maximizes window
        Thread.sleep(1500);// wait for the cookie banner to load
        driver.findElement(By.id("onetrust-reject-all-handler")).click();//click on reject all cookies on popup
    }

    @Given("I enter email address")
    public void iEnterEmailAddress() {

        WebElement email = driver.findElement(By.id("email"));  //locate email field
        email.sendKeys(remail); //enters random email into field
    }

    @Given("I enter username")  //uses email address as username as it is allowed for login purposes
    public void iEnterUserName() {
        WebElement username = driver.findElement(By.id("new_username"));    //locate username field
        username.sendKeys(remail);   //enters random email as username into field as it is allowed as username
    }

    @Given("I enter password")
    public void iEnterPassWord() throws InterruptedException {
        WebElement password = driver.findElement(By.id("new_password"));    //locate password field
        password.sendKeys(rpassword);  //enters random password into field
        Thread.sleep(1000);// wait for the verifications to close that password contains upper,lower case char and number and special sign
    }

    @When("I press signup button")
    public void iPressSignUpButton() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id='create-account']")).click();    //find and press button submit to create account
        Thread.sleep(750);// short wait to load next page
    }

    @Then("I get verification if account created or not")
    public void iGetVerificationIfAccountCreatedOrNot() {
        String url = driver.getCurrentUrl();    // locate and get the url of current page.
        if (url.contains("success/"))//Successfully registered enters a success page and if not its labeled post.
        {
            System.out.println("\nAccount creation successful");
        } else {
            System.out.println("\nAccount creation failed");
            WebElement invalid = driver.findElement(By.className("invalid-error"));
            invalid.getText();
            System.out.println(invalid.getText());
        }
        driver.quit();
    }

    @Given("I enter long username")
    public void iEnterLongUserName() { // input long semi randomized username
        String longname = "olakola" + randomLetters(100);// start name with olakola and continue with random letters
        WebElement username = driver.findElement(By.id("new_username"));    //locate username field
        username.sendKeys(longname);   //enters username into field

        Assertions.assertTrue(longname.length() >= 100);
        System.out.println("\nUsername is to long");
    }

    @Given("I enter already used username")
    public void iEnterAlreadyUsedUsername() {
        WebElement username = driver.findElement(By.id("new_username"));    //locate username field
        username.sendKeys("olakola@mail.com");   //enters already registered username into field
        System.out.println("\nUsername already registered, choose another and try again");

    }

    @Given("I don not enter email address")
    public void iDonNotEnterEmailAddress() {
        System.out.println("\nMissing email, please enter a valid email and try again.");
    }

    static String randomLetters(int nrofrletters) {    // method for generating random letters nrofletters of letters to generate
        String allowedLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz";//allowed letters
        StringBuilder sb = new StringBuilder(nrofrletters);//creates a string with set number of letters
        for (int i = 0; i < nrofrletters; i++) {
            int index = (int) (allowedLetters.length() * Math.random());
            sb.append(allowedLetters.charAt(index));//builds the string with the random letters
        }
        return sb.toString();
    }

    static String randomNumbers(int nrofrnumbers) {    // method for generating random numbers, nrofrnumbers = amount of numbers to generate
        String allowedNumbers = "0123456789"; // allowed numbers to use
        StringBuilder sb = new StringBuilder(nrofrnumbers); //creates string with set nr of numbers
        for (int i = 0; i < nrofrnumbers; i++) {
            int index = (int) (allowedNumbers.length() * Math.random());
            sb.append(allowedNumbers.charAt(index));//builds the string with the random numbers
        }
        return sb.toString();
    }

    static String randomSigns(int nrofrsigns) {    // method for generating random signs nrofrsigns =number of signs to generate
        String allowedSigns = "!#%&/()=?-_*+;:<>|"; //signs allowed
        StringBuilder sb = new StringBuilder(nrofrsigns);//creates string with set number of signs
        for (int i = 0; i < nrofrsigns; i++) {
            int index = (int) (allowedSigns.length() * Math.random());
            sb.append(allowedSigns.charAt(index));//fills the string with signs
        }
        return sb.toString();
    }


}
