Feature: Demo rest api features with github

  Scenario: verify single repo
    Given REST base url https://api.github.com/
    When REST GET /repos/mrduguo/gradle-sample-cucumber
    Then REST status 200
    And REST json path owner.login=mrduguo
    And REST json path html_url=https://github.com/mrduguo/gradle-sample-cucumber


  Scenario Outline: list user repos
    Given REST base url https://api.github.com/
    When REST GET /users/<username>/repos
    Then REST status 200
    And REST has item name=<expectedRepo>
    Examples:
      | username | expectedRepo           |
      | mrduguo  | gradle-buildscript     |
      | mrduguo  | gradle-sample-cucumber |
