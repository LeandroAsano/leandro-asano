
Feature: Blog

  @Scenario: ("LASTS 10 POSTS")
    Scenario: i want to see my last 10 posts
        Given all my posts
        When i run that app
        Then show me 10 lasts posts


  @Scenario: ("MOST LIKED POSTS")
  Scenario: i want to see my most liked posts
    Given all my posts
    When i select "show my most liked posts" option
    Then i see my most liked posts

  @Scenario: ("I want to see my most liked posts")
  Scenario: i want to subscribe to a blogger posts
    Given all the blogger posts
    When i select select a blogger post
    Then i be subscribe to an blogger posts

