package configuration;

public enum Browser {

    CHROME("chrome"),
    FIREFOX("firefox"),
    IE("ie"),
    EDGE("edge");

    private String browser;

    Browser(String browser) {
        this.browser = browser;
    }
}
