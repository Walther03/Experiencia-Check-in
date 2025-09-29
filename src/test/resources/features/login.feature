Feature: Búsqueda de reservas
  Scenario: Buscar reserva con datos válidos
    Given Ingreso a la pagina de Check-in
    And ejecuto la colección de Postman y obtengo la reserva
    And busco la reserva por su codigo  y apellido creado
    Then debo ver el titulo del dashboard
    And se cierra el navegador

