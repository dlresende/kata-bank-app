package net.diegolemos.bankapp.steps.client;

import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClientUI implements Client {

    private final WebDriver webDriver;
    private String username;

    public ClientUI(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public void createBy(String username) {
        this.username = username;
        webDriver.get("http://localhost:8082/#/clients");

        // attendre d'être sur la page
        (new WebDriverWait(webDriver, 5)).until((Predicate<WebDriver>) driver -> driver.findElement(By.name("addClientInput")) != null);

        // créer le compte
        webDriver.findElement(By.name("addClientInput")).sendKeys(username);
        webDriver.findElement(By.name("addClientBtn")).click();
    }

    @Override
    public net.diegolemos.bankapp.client.Client get() {
        return net.diegolemos.bankapp.client.ClientBuilder.aClient().withUsername(username).build();
    }
}
