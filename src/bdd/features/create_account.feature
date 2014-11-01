Feature:
  As a client
  I want to create my account
  In order to become client of the bank

  Scenario:
    Given an existing user "dlemos@mail.com"
    When the user creates a bank account
    Then balance of his new account is 0.0 EUR
