Feature:
  As a new customer
  I want to create my account
  In order to become client of the bank

  Scenario:
    Given a new user "dlemos@email.com"
    When "dlemos@email.com" creates an account
    Then the account is created with a balance 0
