
@tag
Feature: purchase the order from Ecommerce website
  I want to use this template for my feature file

Background:
Given I landed on Ecommerce page
  
  @tag2
  Scenario Outline: positive test of Submitting the order
    Given I logged in with username <name> with password <pass>
    When I add product <productName> to cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." is displayed on ConfirmationPage

    Examples: 
      | name                    | pass               | productName  |
      | pratiksha1488@gmail.com | Password@1234      | ZARA COAT 3  |
     
