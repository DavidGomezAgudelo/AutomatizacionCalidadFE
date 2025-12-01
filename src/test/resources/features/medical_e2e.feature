# language: en
Feature: Medical Office E2E Management
  As an administrator
  I want to manage medical offices from start to finish
  To verify the complete workflow

  Scenario Outline: Manage an office from start to finish
    Given que el usuario ha iniciado sesión como administrador
    When crea un nuevo consultorio con nombre "<nombre>", especialidad "<especialidad>" y ubicación "<sede>"
    And el usuario cierra sesión
    Then debe ver el login

    Examples:
      | nombre | especialidad        | sede           |
      | 116     | Odontología         | Sede Norte     |