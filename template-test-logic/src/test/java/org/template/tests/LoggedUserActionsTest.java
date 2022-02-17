package org.template.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.template.components.AddressTile;
import org.template.components.header.HeaderItem;
import org.template.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class LoggedUserActionsTest extends AbstractTest {

    private final User user = testData.getUsersData().get(0);
    private final Map<String, String> newAddressData = getNewAddressData();

    @BeforeEach
    public void logUserIn() {
        homePage.header()
                .goToLogInPage()
                .loginForm()
                .fillAndSendLoginForm(user.getEmail(), user.getPassword());
    }

    @Test
    public void shouldSuccessfullyLogOutUser() {
        // when
        homePage.header().clickButton(HeaderItem.SIGN_OUT_BUTTON);

        // then
        Assertions.assertTrue(homePage.header().isButtonDisplayed(HeaderItem.SIGN_IN_BUTTON));
    }

    @Test
    public void shouldBePossibleToRemoveAddress() {
        String expectedAddressAlias = newAddressData.get("alias");
        // given
        addAddress();

        // when
        AddressTile addressToRemove = myAdressesPage
                .getAddresses()
                .stream()
                .filter(address -> expectedAddressAlias.equalsIgnoreCase(address.getAlias()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No address found with alias: " + expectedAddressAlias));
        addressToRemove.delete();

        // then
        List<String> addressesAliases = myAdressesPage
                .getAddresses()
                .stream()
                .map(AddressTile::getAlias)
                .collect(Collectors.toList());
        Assertions.assertFalse(addressesAliases.contains(expectedAddressAlias));
    }

    private Map<String, String> getNewAddressData() {
        Map<String, String> newAddressData = new HashMap<>();
        newAddressData.put("firstname", "NE");
        newAddressData.put("lastname", "Assignment");
        newAddressData.put("address1", "Testowa1");
        newAddressData.put("city", "Nowe P");
        newAddressData.put("id_state", "43");
        newAddressData.put("postcode", "00000");
        newAddressData.put("id_country", "21");
        newAddressData.put("phone", "123456789");
        newAddressData.put("id_address", "0");
        newAddressData.put("select_address", "0");
        newAddressData.put("alias", UUID.randomUUID().toString().substring(0,30));

        return newAddressData;
    }

    private void addAddress() {
        homePage.header().clickButton(HeaderItem.MY_ACCOUNT_BUTTON);
        myAccountPage
                .openMyAdresses()
                .openAddingAddressForm()
                .fillAndSendFormUsing(newAddressData);
    }
}
