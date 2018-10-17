Feature: API

  @Scenario: ("All USERS REGISTERED")
  Scenario: i want to see all users existents
    Given all permissions
    When send the request
    Then give me all users existents

  @Scenario: ("All POSTS")
  Scenario: i want to see all posts published
    Given my permissions
    When send the request
    Then give me all lasts posts

  @Scenario: ("All PRODUCT CATEGORIES")
  Scenario: i want to see all categories enabled like admin
    Given all my permissions
    When send the request
    Then give me all categories enabled


