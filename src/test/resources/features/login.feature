@smoke
Feature: Login Test

  Scenario: As user i should be able to login in with valid credentials [TestRailID: 0002]

    Given i'm on landing page
    When i login with valid credentials
    Then i should see the welcome page


  Scenario: As user i should be able see error validation for invalid credentials [TestRailID: 0003]
    Given i'm on landing page
    When i login with invalid credentials
    Then i should see validation message