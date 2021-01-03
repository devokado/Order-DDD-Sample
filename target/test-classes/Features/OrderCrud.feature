Feature: Crud for order
  Background:
    Given create "order" with following detail
      |attribute          |value     |
      |currency           |Rial      |
      |name               |Antea     |
      |addressLine1       |somewhere |
      |city               |Karaj     |
      |country            |IRAN      |
      |productId          |uuid      |
      |itemDescription    |Desc      |
      |itemPrice          |100       |
      |quantity           |1         |
    When the client calls POST "api/orders" with the given detail


