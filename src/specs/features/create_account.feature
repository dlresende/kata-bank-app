Feature: Create account
  As a client
  I want to create my account
  In order to manage my money and payments

  Scenario: Create an account with balance 0.0
    Given an existing client with username "diego"
    When a bank account is created for the client
    Then balance of his new account is 0.0 EUR
