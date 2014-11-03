Feature:
As a new customer of the bank
I want to put money into my account
In order to have a positive balance

Scenario:
  Given an existing user "mfrih@mail.com"
  And her account balance being 0 euros
  When "mfrih@mail.com" deposits 10 euros on the bank account
  Then the new account balance is 10 euros
