package com.automation.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class SubstitutionSteps {

    private static final Map<String, String> ROLES = new HashMap<>() {
        {
            put("Manager", "now able to manage your employee accounts");
            put("Admin", "able to manage any user account on the system");
        }
    };

    private String name;
    private String role;
    private String details;

    @Given("I have a user account with my name {string}")
    public void I_have_a_user_account_with_my_name(final String name) {
        this.name = name;
        log.info(name);
    }


    @When("an Admin grants me {word} rights")
    public void an_Admin_grants_me_role_rights(String role) {
        this.role = role;
        this.details = ROLES.get(role);
        log.info(this.details);
    }

    @Then("I should receive an email with the body:")
    public void I_should_receive_an_email_with_the_body(String body) {
        String expected = String.format("Dear %s,\n" +
                        "You have been granted %s rights.  You are %s. Please be responsible.\n" +
                        "-The Admins",
                name, role, details);
        assertEquals(expected, body);
        log.info(body);
    }

}
