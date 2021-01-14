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

