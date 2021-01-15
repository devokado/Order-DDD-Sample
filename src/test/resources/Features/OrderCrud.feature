Feature: Crud for order
  Background:
    Given create "order" with following detail
      |attribute          |value     |
      |currency           |Rial      |
      |name               |Antea     |
      |addressLine1       |somewhere |
      |city               |Frankfurt |
      |country            |IRAN      |
      |productId          |uuid      |
      |itemDescription    |Desc      |
      |itemPrice          |100       |
      |quantity           |1         |
    When the client calls POST "api/orders" with the given detail

  Scenario: Create order
    Then the client receive status code of 201
    And check the response value type
      |attribute   |type        |
      |id          | [0-9A-Fa-f]{8}[-0-9A-Fa-f]{5}[-0-9A-Fa-f]{5}[-0-9A-Fa-f]{5}[-0-9A-Fa-f]{13}              |
      |orderedOn   | [0-9]{1,4}-[0-9]{1,2}-[0-9]{1,2}[T]{1}[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}[.][0-9]{1,6}[Z]{1}|
      |currency    | [A-Z]{1}[a-z]+                                                                           |
      |state       |[A-Z]+                                                                                    |



  Scenario: retrieve the order with order id
    When the client calls GET "api/orders/{id}" with id
    Then the client receive status code of 200
    And check the response value type
      |attribute   |type                                                                                      |
      |id          | [0-9A-Fa-f]{8}[-0-9A-Fa-f]{5}[-0-9A-Fa-f]{5}[-0-9A-Fa-f]{5}[-0-9A-Fa-f]{13}              |
      |orderedOn   | [0-9]{1,4}-[0-9]{1,2}-[0-9]{1,2}[T]{1}[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}[.][0-9]{1,6}[Z]{1}|
      |currency    | [A-Z]{1}[a-z]+                                                                           |
      |state       |[A-Z]+                                                                                    |

  Scenario: start order processing
    When the client calls POST "api/orders/{id}/startProcessing" with id
    Then the client receive status code of 200
    When the client calls GET "api/orders/{id}" with id
    Then check the response value type
      |attribute   |type        |
      |state       |(PROCESSING)|

  Scenario: finish order processing
    When the client calls POST "api/orders/{id}/finishProcessing" with id
    Then the client receive status code of 200
    When the client calls GET "api/orders/{id}" with id
    Then check the response value type
      |attribute   |type        |
      |state       |(PROCESSED)|

  Scenario: cancel order processing
    When the client calls POST "api/orders/{id}/cancelProcessing" with id
    Then the client receive status code of 200
    When the client calls GET "api/orders/{id}" with id
    Then check the response value type
      |attribute   |type        |
      |state       |(CANCELLED)|

  Scenario: delete the order with order id
    When the client calls DELETE "api/orders/{id}" with id
    Then the client receive status code of 204
    When the client calls GET "api/order/{id}" with id
    Then the client receive status code of 404


