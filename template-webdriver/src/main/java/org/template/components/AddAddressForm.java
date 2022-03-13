package org.template.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.template.AbstractPage;

import java.util.Map;

public class AddAddressForm extends AbstractPage {

    @FindBy(id = "firstname")
    private WebElement firstNameInput;
    @FindBy(id = "lastname")
    private WebElement lastNameInput;
    @FindBy(id = "address1")
    private WebElement addressInput;
    @FindBy(id = "city")
    private WebElement cityInput;
    @FindBy(id = "id_state")
    private WebElement stateWebElement;
    @FindBy(id = "postcode")
    private WebElement postCodeInput;
    @FindBy(id = "id_country")
    private WebElement countryWebElement;
    @FindBy(id = "phone")
    private WebElement phoneInput;
    @FindBy(id = "alias")
    private WebElement aliasInput;
    @FindBy(id = "submitAddress")
    private WebElement submitButton;

    public AddAddressForm(WebDriver driver) {
        super(driver);
    }

    public void fillAndSendFormUsing(Map<String, String> data) {
        Select stateSelect = new Select(stateWebElement);
        Select countrySelect = new Select(countryWebElement);
        firstNameInput.clear();
        firstNameInput.sendKeys(data.get("firstname"));
        lastNameInput.clear();
        lastNameInput.sendKeys(data.get("lastname"));
        addressInput.sendKeys(data.get("address1"));
        cityInput.sendKeys(data.get("city"));
        stateSelect.selectByValue(data.get("id_state"));
        postCodeInput.sendKeys(data.get("postcode"));
        countrySelect.selectByValue(data.get("id_country"));
        phoneInput.sendKeys(data.get("phone"));
        aliasInput.clear();
        aliasInput.sendKeys(data.get("alias"));
        submitButton.click();
    }
}
