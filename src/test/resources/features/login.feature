Feature: Inicio de sesión
  Como usuario registrado
  Quiero iniciar sesión en la tienda
  Para poder acceder a mi cuenta

  Scenario Outline: Validación del proceso de inicio de sesión
    Given que el usuario está en la página de login
    When ingresa su usuario "<usuario>" y contraseña "<contrasena>"
    Then se debe mostrar en login "<resultado>"

    Examples:
      | usuario          | contrasena     | resultado              |
      | standard_user    | secret_sauce   | success                |
      | wrong            | wrong          | error                  |
      | locked_out_user  | secret_sauce   | error                  |
