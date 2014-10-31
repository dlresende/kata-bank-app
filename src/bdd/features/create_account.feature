Feature:
  As a new customer
  I want to create my account
  In order to become client of the bank

  Scenario:
    Given a new user "dlemos@email.com"
    When an account is created for "dlemos@email.com"
    Then the new account has balance 0 EUR
