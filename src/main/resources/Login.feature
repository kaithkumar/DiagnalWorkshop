@test
Feature: Login scenarios

  Scenario Outline: Login with valid user name and password
    When I enter username as "<username>"
    And I enter password as "<password>"
    And I login
    Then I should see amazon home page
    Examples:
      | username | password |
      | 8883381818 | Kaithkumar1! |