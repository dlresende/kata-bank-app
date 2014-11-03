Feature:
  As a person over 18 years old
  I want to create an account
  In order to become client of the bank

  Scenario:
    Given a new user "dlemos@mail.com"
    When an account is created for the user
    Then the user information is stored in the system
