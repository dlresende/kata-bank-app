Feature: Deposit into account
  As a client
  I want to put money into my account
  In order to have a positive balance

  Scenario: An existing client deposits money into his account
    Given an existing client with username "maria"
    And the balance for the client account is "0.0"
    When the client deposits "10.0" EUR into his account
    Then the account balance is "10.0" EUR

  Scenario: An existing client cannot deposit negative values
    Given an existing client with username "maria"
    And the balance for the client account is "0.0"
    When the client deposits "-10.0" EUR into his account
    Then the account balance is "0.0" EUR