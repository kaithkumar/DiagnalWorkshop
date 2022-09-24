@test
Feature: search movie scenarios

  Scenario Outline: Search Movie Test
    When I enter username and password as "<username>" and "<password>"
    And I click search button
    And I search for a movie named "<MovieName>"
    Then Result suggestion should not be empty

    Examples:
      | username | password | MovieName |
      | 8883381818 | Kaithkumar1! | TOP GUN: MAVERICK BONUS SNEAK PEEK |