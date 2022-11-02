

Feature: automate  End2End API test
  			I want to print a user Email and check the exist posts and add new post


  Scenario: Add new post using random userID
    Given Get the random userID
    And users API endpoint is exists
    When I print a user Email
    And Verify a user posts ID between 1and100
    Then Add new Post using same userID

     
