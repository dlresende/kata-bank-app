# Kata BDD: Bank Application
The goal of this kata is to write new executable specifications for our bank application and implement them using TDD. In this way, you will be doing [Outside-In development with Double Loop TDD](http://coding-is-like-cooking.info/2013/04/outside-in-development-with-double-loop-tdd/).

## What to do
### Step 1: Add the withdrawal feature
1.Create the file `src/specs/features/withdraw_from_account.feature` with the following content:
```gherkin
 Feature: Withdraw from account
   As a client of the bank
   I want to withdraw money from my account
   In order to have cash
 
   Scenario: An existing client withdraws from his account
       Given an existing client named "pierre-jean" with 100.0 EUR in his account
       When he withdraws 10.0 EUR from his account
       Then the new balance is 90.0 EUR
```
2.Implement the missing steps described in the previous scenario in the class `net.diegolemos.bankapp.steps.account.AccountStepdefs`.

3.By doing TDD, implement the 'withdraw' feature just added before, starting from front (html) and going deeper as you code. By the end, users could be able to withdraw from user interface. Decide when to use any of all existing test types (e2e, integration and unit tests).

### Step 2: Cover boarder cases for last feature
Add a scenario in the previous added feature for the case when a withdrawal generates an overdraft (clients that have balance 0.0 and withdrawals must have balance 0.0 after the operation).

### Step 3: Update the existing feature deposit
Currently, users can add a negative deposit, which does not make sens. Add a new scenario to fix this issue. Does this need to be fixed in the front end, in the bacn end or both?

### Step 4: Update withdrawal feature
Check if it is possible to withdraw a negative value. If it is the case, fix it. Add the corresponding BDD scenario.

### Step 5: Implement the transfer feature
From now on, users will be able to transfer money from one to another. Implement this feature. Write the BDD feature for it. Write BDD scenarios for the main case, but write also scenarios for boarder cases, like: can one transfer money one don't have?

## Compile & Install (local .m2 repository)
`mvn clean install`

## Run webapp
`mvn tomcat7:run`
The application will be accessible at [http://localhost:8081/](http://localhost:8081/).
