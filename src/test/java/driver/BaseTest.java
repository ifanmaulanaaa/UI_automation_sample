package driver;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import utils.PropertyUtils;

import java.io.File;
import java.net.URL;

public class BaseTest {

    public final static String packageName = PropertyUtils.getProperty("android.appPackage" );
    public final static String activity = PropertyUtils.getProperty("android.appActivity");
    public final static String uiautomator = PropertyUtils.getProperty("android.automationName");
    public final static String permissions = PropertyUtils.getProperty("android.autoGrantPermissions");
    public final static String clearsystem = PropertyUtils.getProperty("android.clearSystemFile");

    protected static AppiumDriverLocalService service;

    @BeforeTest
    @Parameters({"deviceName", "platformVersion"})
    public void preparation(String deviceName, String platformVersion) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platform", "Android");
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("appPackage", packageName);
        capabilities.setCapability("appActivity", activity);
        capabilities.setCapability("clearSystemFile", clearsystem);
        capabilities.setCapability("autoGrantPermissions", permissions);
        capabilities.setCapability("automationName", uiautomator);
        capabilities.setCapability("chromedriverExecutable", "/usr/local/bin/chromedriver"); //based on your local chromedriver
        capabilities.setCapability("setWebContentsDebuggingEnabled", true);
        capabilities.setCapability("autoWebview", false);
        capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));

//  ***************** choose which one will be use for start service *****************
//      default appium service start using default port and config
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

//      appium service start using certain port and config
//        int port = 4847;
//        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
//                .usingDriverExecutable(new File(("/Users/mac/.nvm/versions/node/v8.16.2/bin/node")))
//                .withAppiumJS(new File(("/Users/mac/.nvm/versions/node/v8.16.2/lib/node_modules/appium/build/lib/main.js")))
//                .withIPAddress("127.0.0.1")
//                .usingPort(port));
//        service.start();
//  ************************** end of start service choices ************************

        String service_url = service.getUrl().toString();
        System.out.println("Appium Service Address: " + service_url);
        ThreadLocalDriver.setTLDriver(new AndroidDriver(new URL(service_url), capabilities));
    }

    @AfterTest
    public void stopService() {
        try {
            if (ThreadLocalDriver.getTLDriver() != null)
                ThreadLocalDriver.getTLDriver().quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

