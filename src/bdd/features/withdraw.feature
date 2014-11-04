Feature: Withdrawal
As a customer of the bank
I want to withdraw money from my bank account
In order to spend it on shopping

Scenario:
  Given an existing user "mfrih@mail.com"
  And a bank account for "mfrih@mail.com"
  And "mfrih@mail.com" deposits 20 euros in her bank account
  When "mfrih@mail.com" withdraws 10 euros in her bank account
  Then the account balance for "mfrih@mail.com" is 10 EUR
