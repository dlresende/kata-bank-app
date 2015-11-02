Feature: Deposit into account
  As a client
  I want to put money into my account
  In order to have a positive balance

  @ui
  Scenario: Deposit money into account
    Given an existing client named "maria" with 0.0 EUR in her account
    When she deposits 10.0 EUR into her account
    Then the new balance is 10.0 EUR

  Scenario: Try to deposit negative values
    Given an existing client named "maria" with 0.0 EUR in her account
    When she deposits -10.0 EUR into her account
    Then the new balance is 0.0 EUR