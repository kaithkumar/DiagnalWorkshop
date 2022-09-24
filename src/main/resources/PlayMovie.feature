@test
Feature: Play Movie Page scenarios

  Scenario Outline: Play Movie Test
    When I enter username and password as "<username>" and "<password>"
    And I click search button
    And I search for a movie named "<MovieName>"
    Then Navigate to the detials page
    And I click play the Video
    Then Video should be playable

    Examples:
      | username | password | MovieName |
      | 8883381818 | Kaithkumar1! | TOP GUN: MAVERICK BONUS SNEAK PEEK |