package net.diegolemos.bankapp.steps.ui;

import static org.junit.Assert.assertEquals;
import org.glassfish.grizzly.http.server.HttpServer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.base.Predicate;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.diegolemos.bankapp.BankAppBinder;
import net.diegolemos.bankapp.MyServer;

public class AccountStepdefs {

    private HttpServer server;
    private WebDriver webDriver;
    private String username;

    @Before
    public void initServerAndDriver() {
        server = MyServer.startServer(new BankAppBinder());
        webDriver = new FirefoxDriver();
    }

    @Given("^an existing client named \"([^\"]*)\" with ([^\"]*) EUR in (?:her|his) account UI$")
    public void an_existing_client_named_with_EUR_in_her_account(String username, double balance) throws Throwable {
        this.username = username;
        webDriver.get("http://localhost:8081/#/clients");

        // attendre d'âtre sur la page
        (new WebDriverWait(webDriver, 5)).until(new Predicate<WebDriver>() {
            @Override public boolean apply(WebDriver driver) {
                return driver.findElement(By.name("addClientInput")) != null;
            }
        });

        // créer le compte
        webDriver.findElement(By.name("addClientInput")).sendKeys(username);
        webDriver.findElement(By.name("addClientBtn")).click();
    }

    @When("^(?:she|he) deposits ([^\"]*) EUR into (?:her|his) account UI$")
    public void client_desposits(double amount) throws Throwable {
        // aller sur la page des dépôts
        webDriver.get("http://localhost:8081/#/accounts");

        // attendre d'âtre sur la page
        (new WebDriverWait(webDriver, 5)).until(new Predicate<WebDriver>() {
            @Override public boolean apply(WebDriver driver) {
                return driver.findElement(By.id("input-username")) != null;
            }
        });

        // rechercher le compte
        webDriver.findElement(By.id("input-username")).sendKeys(username);
        webDriver.findElement(By.name("searchUserBtn")).click();
        (new WebDriverWait(webDriver, 5)).until(new Predicate<WebDriver>() {
            @Override public boolean apply(WebDriver driver) {
                return driver.findElement(By.name("addTransactionBtn")) != null;
            }
        });

        // déposer de l'argent
        WebElement baseTable = webDriver.findElement(By.name("transactions"));
        WebElement lines = baseTable.findElement(By.tagName("tbody"));
        WebElement firstLine = lines.findElements(By.tagName("tr")).get(0);
        WebElement amountField = firstLine.findElement(By.cssSelector("td > input[name=amount]"));
        amountField.sendKeys(String.valueOf(amount));
        Select typeField = new Select(firstLine.findElement(By.cssSelector("td > select[name=type]")));
        typeField.selectByValue("DEPOSIT");
        webDriver.findElement(By.name("saveNewTransactionBtn")).click();
    }

    @Then("^the new balance is ([^\"]*) EUR UI$")
    public void verify_balance(double expectedBalance) throws Throwable {
        webDriver.findElement(By.name("searchUserBtn")).click();
        // attendre d'être sur la page
        (new WebDriverWait(webDriver, 5)).until(new Predicate<WebDriver>() {
            @Override public boolean apply(WebDriver driver) {
                return (webDriver.findElements(By.cssSelector("table[name=transactions]"))) != null;
            }
        });

        // valider le montant de la balance
        double balance = Double.valueOf(webDriver.findElements(By.cssSelector("table[name=transactions] > tfoot > tr > td")).get(1).getText().split(" ")[0]);
        assertEquals(expectedBalance, balance, 0);
    }

    @After
    public void stopServer() {
        webDriver.close();
        server.stop();
    }
}
