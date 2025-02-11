package com.nttdata.page;

import org.openqa.selenium.By;

public class priceProductPage {
    //Locator de la Página de Inicio
    public static By btn_inicio_sesion = By.cssSelector("#_desktop_user_info > div > a");
    public static By input_email = By.xpath("//*[@id=\"field-email\"]");
    public static By input_pass = By.xpath("//*[@id=\"field-password\"]");
    public static By btn_ingresar = By.xpath("//*[@id=\"submit-login\"]");
    public static By btn_cerrar_sesion = By.xpath("//*[@id=\"_desktop_user_info\"]/div/a[1]");

    //Locators de la Página después de Iniciar Sesión
    public static By btn_categoria = By.cssSelector("#category-3 > a");

    //Locators después de hacer click en Categoría
    public static By btn_subcategoria = By.cssSelector("#subcategories > ul > li:nth-child(1) > h5 > a");

    //Locators después de hacer click en Subcategoría
    public static By producto_thumbnail = By.cssSelector("#js-product-list > div.products.row > div > article > div > div.thumbnail-top > a");

    //Locators después de hacer click en el producto
    public static By touch_spin_agregar = By.cssSelector("#add-to-cart-or-refresh > div.product-add-to-cart.js-product-add-to-cart > div > div.qty > div > span.input-group-btn-vertical > button.btn.btn-touchspin.js-touchspin.bootstrap-touchspin-up");

    public static By btn_agregar_carrito = By.cssSelector("#add-to-cart-or-refresh > div.product-add-to-cart.js-product-add-to-cart > div > div.add > button");

    public static By confirmacion_popup = By.xpath("//*[@id=\"myModalLabel\"]");

    public static By precio_unitario = By.cssSelector("#blockcart-modal > div > div > div.modal-body > div > div.col-md-5.divide-right > div > div:nth-child(2) > p");
    public static By cantidad_producto = By.cssSelector("#blockcart-modal > div > div > div.modal-body > div > div.col-md-5.divide-right > div > div:nth-child(2) > span.product-quantity > strong");
    public static By total_monto = By.cssSelector("#blockcart-modal > div > div > div.modal-body > div > div.col-md-7 > div > p.product-total > span.value");

    public static By btn_finalizar_compra_popup = By.cssSelector("#blockcart-modal > div > div > div.modal-body > div > div.col-md-7 > div > div > a");

    //Locators después de hacer click en Finalizar Compra
    public static By etiqueta_carrito = By.cssSelector("#main > div > div.cart-grid-body.col-lg-8 > div > div.card-block > h1");
    public static By etiqueta_precio_unitario = By.cssSelector("#main > div > div.cart-grid-body.col-lg-8 > div > div.cart-overview.js-cart > ul > li > div > div.product-line-grid-body.col-md-4.col-xs-8 > div.product-line-info.product-price.h5.has-discount > div.current-price > span");
    public static By etiqueta_cantidad_producto = By.cssSelector("#cart-subtotal-products > span.label.js-subtotal");
    public static By etiqueta_monto_total = By.cssSelector("#main > div > div.cart-grid-right.col-lg-4 > div.card.cart-summary > div.cart-detailed-totals.js-cart-detailed-totals > div.card-block.cart-summary-totals.js-cart-summary-totals > div.cart-summary-line.cart-total > span.value");

}
