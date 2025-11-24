Feature: Visualizar catálogo de tienda
Como usuario autenticado
Quiero ver el catátogo de productos
Para decidir que comprar.

  Scenario: Usuario autenticado visualiza productos
    Given que el usuario ha iniciado sesion
    When accede al catalogo de productos
    Then debe visualizar la lista de productos de la tienda

  Scenario: Usuario autenticado ordena los productos
    Given que el usuario ha iniciado sesion
    And se encuentra en el catálogo de productos
    When ordena productos
    Then debe visualizar los productos ordenados