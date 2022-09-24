@test
Feature: Details Page scenarios

  Scenario Outline: Details Page Test
    When I enter username and password as "<username>" and "<password>"
    And I click search button
    And I search for a movie named "<MovieName>"
    Then Navigate to the detials page

    Examples:
      | username | password | MovieName |
      | 8883381818 | Kaithkumar1! | TOP GUN: MAVERICK BONUS SNEAK PEEK |