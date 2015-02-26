# Kata BDD: Bank Application
The goal of this kata is to write new executable specifications for our bank application and implement them using TDD.

## What to do
1.Create the file `src/specs/features/withdraw_form_account.feature` with the following content:
```gherkin
 Feature: Withdraw from account
   As a client of the bank
   I want to withdraw money from my account
   In order to have cash
 
   Scenario: An existing client withdraws from his account
       Given an existing client named "pierre-jean" with 100.0 EUR in his account
       When he withdraws 10.0 EUR into his account
       Then the new balance is 90.0 EUR
```
2.Create the Java class `src/specs/java/steps/WithdrawFromAccountStepdefs.java` and implement the steps described in the previous scenario.
3.By doing TDD, implement the 'withdraw' feature just added before.
4.(Optional) Expose the new withdraw feature in the UI.

## Compile & Install (local .m2 repository)
`mvn clean install`

## Run Specifications
`mvn verify -Pspecs`

## Run webapp
`mvn tomcat7:run`
The application will be accessible at [http://localhost:8081/](http://localhost:8081/).
