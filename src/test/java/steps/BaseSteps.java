package steps;

import cucumber.api.Scenario;
import driver.ThreadLocalDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class BaseSteps {
    public ThreadLocalDriver driver;
    protected WebDriverWait wait;

    protected void setupCucumber() {
        System.out.println("Cucumber Base Step");
        //Get driver to use step files
        wait = new WebDriverWait(ThreadLocalDriver.getTLDriver(), 10);
    }

    protected void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) ThreadLocalDriver.getTLDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
            try {
                if (ThreadLocalDriver.getTLDriver() != null)
                    ThreadLocalDriver.getTLDriver().quit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Objects.requireNonNull(ThreadLocalDriver.getTLDriver()).getSessionId();
    }
}

