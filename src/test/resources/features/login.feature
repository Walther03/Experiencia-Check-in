Feature: Login functionality

  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter username "test3@yopmail.com" and password "Sky.2023"
    Then I should be logged in successfully
