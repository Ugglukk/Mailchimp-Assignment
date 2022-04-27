Feature: MailChimp

  Scenario: Enter new user information and everything goes as expected
    Given I have opened browser
    Given I enter email address
    Given I enter username
    Given I enter password
    When I press signup button
    Then I get verification that account is created

  Scenario: Enter long username over 100 signs
    Given I have opened browser
    Given I enter email address
    Given I enter long username
    Given I enter password
    When I press signup button
    Then I get verification that account is not created

  Scenario: Username already registered
    Given I have opened browser
    Given I enter email address
    Given I enter already used username
    Given I enter password
    When I press signup button
    Then I get verification that account is not created

  Scenario: Create account without email
    Given I have opened browser
    Given I don not enter email address
    Given I enter username
    Given I enter password
    When I press signup button
    Then I get verification that account is not created
