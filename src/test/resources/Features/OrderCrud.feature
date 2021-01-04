Feature: Crud for order
  Background:
    Given create "order" with following detail
      |attribute          |value     |
      |currency           |Rial      |
      |name               |Antea     |
      |addressLine1       |somewhere |
      |city               |Frankfurt     |
      |country            |IRAN      |
      |productId          |uuid      |
      |itemDescription    |Desc      |
      |itemPrice          |100       |
      |quantity           |1         |
    When the client calls POST "api/orders" with the given detail

  Scenario: Create order
    Then the client receive status code of 201

