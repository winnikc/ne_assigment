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
        newAddressData.put("firstname", user.getFirstName());
        newAddressData.put("lastname", user.getLastName());
        newAddressData.put("address1", user.getAddress1());
        newAddressData.put("city", user.getCity());
        newAddressData.put("id_state", user.getId_state());
        newAddressData.put("postcode", user.getPostcode());
        newAddressData.put("id_country", user.getId_country());
        newAddressData.put("phone", user.getPhone());
        newAddressData.put("id_address", user.getId_address());
        newAddressData.put("select_address", user.getSelect_address());
        newAddressData.put("alias", user.getAlias());

        return newAddressData;
    }

    private void addAddress() {
        homePage.header().clickButton(HeaderItem.MY_ACCOUNT_BUTTON);
        myAccountPage
                .openMyAdresses()
                .openAddingAddressForm()
                .fillAndSendFormUsing(getNewAddressData());
    }
}
