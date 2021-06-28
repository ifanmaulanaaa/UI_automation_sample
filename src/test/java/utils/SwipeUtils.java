package utils;

import driver.ThreadLocalDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;

public class SwipeUtils {

    /**
     * To scroll screen to find element within (text)
     *
     * @param text
     */
    public void scrollTo(String text) {
        ThreadLocalDriver.getTLDriver().findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView("
                        +"new UiSelector().textContains(\""+text+"\").instance(0))");
    }


    /**
     * To swipe screen for given point
     *
     * @param fromX
     * @param fromY
     * @param toX
     * @param toY
     */
    public void swipeTo(int fromX, int fromY, int toX, int toY) {
        TouchAction action = new TouchAction(ThreadLocalDriver.getTLDriver());
        action.press(PointOption.point(fromX, fromY))
                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(3000))) //you can change wait durations as per your requirement
                .moveTo(PointOption.point(toX, toY))
                .release()
                .perform();
    }
}
