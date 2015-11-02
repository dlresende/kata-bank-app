Feature: Deposit into account
  As a client
  I want to put money into my account
  In order to have a positive balance

  Scenario: An existing client deposits money into his account UI
    Given an existing client named "maria" with 0.0 EUR in her account UI
    When she deposits 10.0 EUR into her account UI
    Then the new balance is 10.0 EUR UI
