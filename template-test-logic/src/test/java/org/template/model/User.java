package org.template.model;

import java.util.UUID;

public class User {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address1;
    private String city;
    private String id_state;
    private String postcode;
    private String id_country;
    private String phone;
    private String id_address;
    private String select_address;
    private final String alias = UUID.randomUUID().toString().substring(0,30);

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress1() {
        return address1;
    }

    public String getCity() {
        return city;
    }

    public String getId_state() {
        return id_state;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getId_country() {
        return id_country;
    }

    public String getPhone() {
        return phone;
    }

    public String getId_address() {
        return id_address;
    }

    public String getSelect_address() {
        return select_address;
    }

    public String getAlias() {
        return alias;
    }

}
