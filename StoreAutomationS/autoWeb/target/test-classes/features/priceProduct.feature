Feature: Product - Store

  @priceProductTest
  Scenario Outline: Validación del precio de un producto

    Given estoy en la página de la tienda y hago click en el boton Iniciar Sesión
    And me logueo con mi usuario "<email>" y clave "<pass>"
    When navego a la categoria "<categoria>" y subcategoria "<subcategoria>"
    And agrego 2 unidades del primer producto al carrito
    Then valido en el popup la confirmación del producto agregado
    And valido en el popup que el monto total sea calculado correctamente
    When finalizo la compra
    Then valido el titulo de la pagina del carrito
    And vuelvo a validar el calculo de precios en el carrito
    Examples:
      | email                   | pass            | categoria   | subcategoria |
      | leonardo25457@gmail.com | leonardo2506123 | CLOTHES     | MEN          |
      | error404@gmail.com      | idk2506         | CLOTHES     | MEN          |
      | leonardo25457@gmail.com | leonardo2506123 | PHONES      | MEN          |