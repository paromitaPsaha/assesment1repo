@AllScenario
Feature: Health check of Automation Practice Site

  Background: Navigation to the URL
    Given User navigate to URL and open the landing page

  @DisplayLogo
  Scenario: User able to see logo of application on landing page
    When User is on landing page
    Then User see the logo of application

  @ProductCategory
  Scenario: User able to see product category on landing page
    When User see the product category
    Then Validate product category as per expected product category listed below
      | Android (1) 	 |
      | HTML (3)   		 |
      | JavaScript (3) |
      | selenium (1)	 |
    And Size of product category should be 4

  @MyAccountPage
  Scenario: User clicks on MyAccount and redirect to respective page
    Given user check the MyAccount tab
    When User clicks on MyAccount 
    Then User is on MyAccount page where the title is "My Account – Automation Practice Site"
    And Redirect to landing page

  @loginPositive @smoke
  Scenario Outline: User is able to login into the application
    Given User click on MyAccount button from home page
    When User redirected to login page of the application where title is "My Account – Automation Practice Site"
    And User enters "<username>" and "<password>" and click on signin button
    Then User successfully directed to next page 

    Examples: 
      | username               | password | 
      | dsfsfsdfds11@gmail.com |   dummypass@1 |
      | dsfsfsdfds12@gmail.com |   dummypass@2 |
     

  @loginNegative @smoke
  Scenario Outline: User is unable to login into the application
    When User click on signin button from home page
    And User redirected to login page of the application where title is "My Account – Automation Practice Site"
    And User enters "<username>" and "<password>" and click on signin button
    Then User is unable to login with an error message "Error: the password you entered for the username user1@gmail.com is incorrect. Lost your password?"

    Examples: 
      | username            | password |
      | user1@gmail.com     |   123456 |
      | user2@gmail.com     |   123456 |
