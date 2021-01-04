Feature: Smoke test Flipkart application

Scenario: Test functionality to search for an item

Given Browser is opened and Flipkart application is loaded
When Search condition entered
Then Search result should be displayed
Then Close Browser