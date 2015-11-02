package net.diegolemos.bankapp.steps.account;

import com.google.common.base.Predicate;
import net.diegolemos.bankapp.client.Client;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountUI implements Account {

    private final WebDriver webDriver;
    private String username;

    public AccountUI(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public void createFor(Client client) {
        this.username = client.username();
        webDriver.get("http://localhost:8082/#/clients");

        // attendre d'être sur la page
        (new WebDriverWait(webDriver, 5)).until((Predicate<WebDriver>) driver -> driver.findElement(By.name("addClientInput")) != null);

        // créer le compte
        webDriver.findElement(By.name("addClientInput")).sendKeys(username);
        webDriver.findElement(By.name("addClientBtn")).click();
    }

    @Override
    public void deposit(double amount) {
        // aller sur la page des dépôts
        webDriver.get("http://localhost:8082/#/accounts");

        // attendre d'âtre sur la page
        (new WebDriverWait(webDriver, 5)).until((Predicate<WebDriver>) driver -> driver.findElement(By.id("input-username")) != null);

        // rechercher le compte
        webDriver.findElement(By.id("input-username")).sendKeys(username);
        webDriver.findElement(By.name("searchUserBtn")).click();
        (new WebDriverWait(webDriver, 5)).until((Predicate<WebDriver>) driver -> driver.findElement(By.name("addTransactionBtn")) != null);

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

    @Override
    public void checkBalance(double expectedBalance) {
        webDriver.findElement(By.name("searchUserBtn")).click();
        // attendre d'être sur la page
        (new WebDriverWait(webDriver, 5)).until((Predicate<WebDriver>) driver -> (webDriver.findElements(By.cssSelector("table[name=transactions]"))) != null);

        // valider le montant de la balance
        double actualBalance = Double.valueOf(webDriver.findElements(By.cssSelector("table[name=transactions] > tfoot > tr > td")).get(1).getText().split(" ")[0]);
        assertThat(expectedBalance, is(equalTo(actualBalance)));

    }
}
