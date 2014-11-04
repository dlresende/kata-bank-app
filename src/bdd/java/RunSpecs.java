import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/bdd/features/",
        format = {
                "pretty",
                "html:target/cucumber"
        })
public class RunSpecs {
}
