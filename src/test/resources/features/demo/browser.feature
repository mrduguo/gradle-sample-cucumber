Feature: Demo browser features

  Scenario: visit gebsite
    Given BROWSER base url http://www.gebish.org
    When BROWSER go /
    Then BROWSER title Geb - Very Groovy Browser Automation
    And BROWSER has element #header-content ul li=MANUAL