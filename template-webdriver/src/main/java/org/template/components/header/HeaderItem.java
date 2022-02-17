package org.template.components.header;

import org.openqa.selenium.By;

public enum HeaderItem {

    SIGN_IN_BUTTON(By.xpath("//a[@class='login']")),
    SIGN_OUT_BUTTON(By.cssSelector("a.logout")),
    MY_ACCOUNT_BUTTON(By.cssSelector("div.header_user_info span"));

    private final By locator;

    HeaderItem(By locator) {
        this.locator = locator;
    }

    public By getLocator() {
        return locator;
    }

}
