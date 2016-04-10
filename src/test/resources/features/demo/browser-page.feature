Feature: Demo browser features with page object pattern

  Scenario: visit gebsite to access manual page
    When GEBSITE go home page
    Then GEBSITE click manual link contains CURRENT
    Then GEBSITE on TheBookOfGebPage