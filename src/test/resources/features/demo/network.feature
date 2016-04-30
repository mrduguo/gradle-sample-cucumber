Feature: Demo network ping features

  Scenario Outline: ping server
    When NET ping <address>
    Then NET status should <result>
    Examples:
      | address                   | result  |
      | 127.0.0.1                 | success |
      | 8.8.8.8                   | success |
      | www.google.ie             | success |
      | www.notexisting-google.ie | failed  |

  Scenario Outline: telnet server
    When NET telnet <address> <port>
    Then NET status should <result>
    Examples:
      | address                   | port | result        |
      | 8.8.8.8                   | 53   | success       |
      | www.google.ie             | 80   | success       |
      | www.google.ie             | 443  | success       |
      | 127.0.0.1                 | 9988 | port_not_open |
      | www.notexisting-google.ie | 80   | unknown_host  |
