Feature: Get feature

  Background:
    Given the baseURL is "https://api.restful-api.dev/"

  Scenario Outline: Get first object
    Given the "<endpoint>" endpoint is available
    When the user send a GET request
    Then the response code is <statusCode>
    And the response includes:
      |id|
      |name  |
      |data      |
    And "id" is equals with "<id>"
    And "name" is equals with "<name>"
    And the data contains:
      | color    | Cloudy White |
      | capacity | 128 GB       |


    Examples:
      |id|name  |data      |statusCode| endpoint |
      |1|Google Pixel 6 Pro  |{"color":"Cloudy White","capacity":"128 GB"}      |200| objects/1 |

  Scenario Outline: Get second object
    Given the "<endpoint>" endpoint is available
    When the user send a GET request
    Then the response code is <statusCode>
    And the response includes:
      |id|
      |name  |
      |data      |
    And "id" is equals with "<id>"
    And "name" is equals with "<name>"
    And the data is null


    Examples:
      |id|name  |data      |statusCode| endpoint |
      |2|Apple iPhone 12 Mini, 256GB, Blue  |null      |200| objects/2 |