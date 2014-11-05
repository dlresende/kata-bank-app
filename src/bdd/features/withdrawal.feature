Feature: Withdraw from account
  As a customer of the bank
  I want to withdraw money from my account
  In order to take money off

  Scenario:
    Given an existing user "mfrih@mail.com"
    And a new bank account for "mfrih@mail.com"
    And "mfrih@mail.com" deposits 10 EUR in her bank account
    When "mfrih@mail.com" withdraws 5 EUR from her bank account
    Then the account balance for "mfrih@mail.com" is 5 EUR
