Feature: Check balance
  As a new customer
  I want to check my balance
  In order to manager my account

  Scenario:
    Given a user "mfrih@email.com" with no money in her account
    When 10 EUR is deposited into her account
    Then the new balance should be 10 EUR
