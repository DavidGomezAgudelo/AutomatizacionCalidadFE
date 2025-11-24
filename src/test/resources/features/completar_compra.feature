Feature: Finalizar compra
  Como usuario autenticado
  Quiero finalizar mi compra
  Para completar el proceso

  Scenario Outline: Validación del proceso de compra
    Given que el usuario ha iniciado sesion
    And el usuario ha realizado su compra hasta el checkout
    When accede a la página de checkout
    And ingresa su informacion "<nombre>" "<apellido>" "<codigo_postal>"
    Then se debe mostrar en compra "<resultado>"

    Examples:
      | nombre    | apellido    | codigo_postal | resultado           |
      |           |             |               | error               |
      | Argenis   | Medina      | 12345         | confirmacion        |
