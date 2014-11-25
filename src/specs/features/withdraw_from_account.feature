Feature: Withdraw from account
  As a client
  I want to withdraw from my account
  In order to have cash

  Scenario:
    Given an existing user "dlemos@mail.com"
    And a new bank account for "dlemos@mail.com"
    And "dlemos@mail.com" deposits 10 EUR in her bank account
    When "dlemos@mail.com" withdraws 5 EUR from his account
    Then the account balance for "dlemos@mail.com" is 5 EUR
