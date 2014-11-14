Feature: Create account
  As a person over 18 years old
  I want to create my account
  In order to manage my money and payments

  Scenario:
    Given an existing user "dlemos@mail.com"
    When the user creates a bank account
    Then balance of his new account is 0.0 EUR
