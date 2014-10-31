Feature: Check balance
  As a new customer
  I want to check my balance
  In order to manager my account

  Scenario:
    Given a user "mfrih@email.com" with an empty account
    When "mfrih@email.com" deposit 10 euros into her account
    Then the new balance is 10 euros
