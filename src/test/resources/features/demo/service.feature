Feature: Demo service basic verification features

  Scenario: verify ping services
    When SERVICE select pingService
    Then SERVICE ping all should success

  Scenario: verify telnet services
    When SERVICE select telnetService
    Then SERVICE telnet all should success

  Scenario: verify rest services health check
    When SERVICE select restService
    Then SERVICE health check all should success
