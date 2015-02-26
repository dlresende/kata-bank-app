Feature: Create client
  As a person
  I want to become client
  In order to have access to the bank services

  Scenario: Create a new client in the system
    Given "diego" born on "Feb 5, 1986"
    When he becomes a client
    Then the following information should be stored in the system:
      | username | birthday    |
      | diego    | Feb 5, 1986 |
