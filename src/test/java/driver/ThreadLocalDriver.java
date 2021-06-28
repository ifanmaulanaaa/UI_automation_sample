package driver;

import io.appium.java_client.android.AndroidDriver;

public class ThreadLocalDriver {
    private static ThreadLocal<AndroidDriver<?>> tlDriver = new ThreadLocal<>();

    public static void setTLDriver(AndroidDriver<?> driver) {
        tlDriver.set(driver);
    }

    public static AndroidDriver<?> getTLDriver() {
        return tlDriver.get();
    }

}
