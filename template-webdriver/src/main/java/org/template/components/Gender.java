package org.template.components;

import org.openqa.selenium.By;

public enum Gender {

    MALE(By.cssSelector("label[for=id_gender1]")),
    FEMALE(By.cssSelector("label[for=id_gender2]"));

    private final By registerFormLocator;

    Gender(By registerFormLocator) {
        this.registerFormLocator = registerFormLocator;
    }

    public By getRegisterFormLocator() {
        return registerFormLocator;
    }

}
