package configuration;

public enum Browser {

    CHROME("chrome"),
    FIREFOX("firefox"),
    IE("ie");
    private String browser;

    Browser(String browser) {
        this.browser = browser;
    }
}
