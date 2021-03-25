Feature: demo

  Scenario: DEmo
    Given The service is available
    When we hit hello endpoint to sent a kafka message
    Then we should publish a kafka message