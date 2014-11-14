# Kata BDD: Bank Application
The goal of this kata is to write new executable specifications for our bank application and implement them using TDD.

## What to do
 1. Create the file `src/bdd/features/withdraw_form_account.feature` with the following content:
> Feature: Withdraw from account
>>  As a customer of the bank
>>   I want to withdraw money from my account
>>   In order to take money off
>> 
>>   Scenario:
>>>     Given an existing user "mfrih@mail.com"
>>>     And a new bank account for "mfrih@mail.com"
>>>     And "mfrih@mail.com" deposits 10 EUR in her bank account
>>>     When "mfrih@mail.com" withdraws 5 EUR from her bank account
>>>     Then the account balance for "mfrih@mail.com" is 5 EUR
 2. Create the Java class `src/bdd/java/steps/WithdrawFromAccountStepdefs.java` and implement the steps described in the previous scenario. Use the following template:
 ```java
 @When("^\"([^\"]*)\" withdraws (\\d+) EUR from her bank account$")
 public void withdraws_EUR_from_her_bank_account(String username, int amount) throws Throwable {
     // implement the step here...
 }
 ```
 3. By doing TDD, implement the 'withdraw' feature just added before
 4. (Optional) Expose the new withdraw feature in the UI

## Compile & Install (local .m2 repository)
`mvn clean install`

## Run Specifications
`mvn verify -Pbdd`

## Run webapp
`mvn tomcat7:run`
The application will be accessible at [http://localhost:8081/](http://localhost:8081/).
