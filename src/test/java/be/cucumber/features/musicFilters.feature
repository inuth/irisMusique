#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Search musics
	I want to search musics by passing some filters

	Background: set the base url
		When I go to "http://localhost:8082"

	Scenario Outline: correct filters
		Given I give "<param>" as "<paramName>" in the url
		When I go to "/music/v2"
		Then I validate 
		And I get a statutCode 200
		And I get 204 in "[0].duration"
		
		Examples:
		|param|paramName|
		|200|minDuration|
		|210|maxDuration|
		|thing|title|
		|autorName|Albert|
		
	Scenario Outline: 2 parameters
		Given I give "<param1>" as "<paramName1>" in the url
		And I give "<param2>" as "<paramName2>" in the url
		When I go to "/music/v2"
		Then I validate
		And I get a statutCode 200
		And I get 204 in "[0].duration"
		
	Examples:
		|param1|paramName1|param2|paramName2|
		|200|minDuration|else|title|
		|210|maxDuration|autorName|Albert|
		|thing|title|210|maxDuration|
		|autorName|Albert|200|minDuration|
