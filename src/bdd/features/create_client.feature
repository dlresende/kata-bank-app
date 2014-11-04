Feature: Create client
  As a person over 18 years old
  I want to become client
  In order to access the bank services

  Scenario:
    Given a new user "dlemos@mail.com"
    When an account is created for the user
    Then the user information is stored in the system
