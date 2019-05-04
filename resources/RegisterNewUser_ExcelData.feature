@All
Feature: Register New User
  This feature is to validate create account functionality - Data from Excel

  Background: Launch the application
    Given Launch IMDB application
    Then Click on Sign In button

  @RegisterExcel
  Scenario: TC_05 Register a new user, login and logout - Data from Excel
    Given Click on Create Account button
    Then Fill in the following user details: "Create Account"
    Then Click on Continue button
    Then Click on profile dropdown
    And Click on logout option

  @RegisterExcel
  Scenario: TC_06 Sign with registered user, login and logout - Data from Excel
    Given Sign in with imdb account
    Then Fill in the following user details: "Sign In"
    Then click on Submit button
    Then Click on profile dropdown
    And Click on logout option

  @RegisterExcel
  Scenario: TC_07 Verify welcome mail from IMDB
    Given Launch the email service provider with email address
    Then Click on verification email that the user received
    Then Click on account activation link
    And Verifyt the title "Complete your Registration"
