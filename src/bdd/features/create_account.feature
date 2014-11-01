Feature:
  As a new customer
  I want to create my account
  In order to become client of the bank

  Scenario:
    Given a new user "dlemos@mail.com"
    When the user becomes client
    Then balance of his new account is 0 EUR
