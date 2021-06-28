package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends BaseSteps {

    @Before
    public void setup() {
        setupCucumber();
    }

    @After
    public void stop(Scenario scenario) {
        teardown(scenario);
    }
}

