Feature: Create client
  As a person
  I want to become client
  In order to have access to the bank services

  Scenario: Create a new client in the system
    Given a person born in "Feb 5, 1986"
    When this person becomes a client with the username "diego"
    Then the following client information should be stored in the system:
      | username | birthday    |
      | diego    | Feb 5, 1986 |
