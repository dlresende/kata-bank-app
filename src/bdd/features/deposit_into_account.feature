Feature: Deposit into account
  As a new customer of the bank
  I want to put money into my account
  In order to have a positive balance

  Scenario:
    Given an existing user "mfrih@mail.com"
    And a bank account for "mfrih@mail.com"
    And the account balance for "mfrih@mail.com" is 0 EUR
    When "mfrih@mail.com" deposits 10 euros in her bank account
    Then the account balance for "mfrih@mail.com" is 10 EUR
