package com.nttdata.steps;

import com.nttdata.page.priceProductPage;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;

public class priceProductStep {
    private WebDriver driver;

    public priceProductStep(WebDriver driver) {
        this.driver = driver;
    }

    public void clickBtnIniciarSesion(){
        this.driver.findElement(priceProductPage.btn_inicio_sesion).click();
    }

    public void ingresaEmail(String email){
        this.driver.findElement(priceProductPage.input_email).sendKeys(email);
    }

    public void ingresaContraseña(String password){
        this.driver.findElement(priceProductPage.input_pass).sendKeys(password);
    }

    public void clickBtnIngresa(){
        this.driver.findElement(priceProductPage.btn_ingresar).click();
    }

    public void verificarBtnCerrarSesion(){
        String cerrar_sesion_pag = this.driver.findElement(priceProductPage.btn_cerrar_sesion).getText().substring(2);
        System.out.println(cerrar_sesion_pag);
        String cerrar_sesion_esperado = "Cerrar sesión";
        Assert.assertEquals("Etiqueta de Cerrar Sesión Esperada:",cerrar_sesion_esperado,cerrar_sesion_pag);
    }

    public void verificarCategoria(String categoria){
        String categoria_page = this.driver.findElement(priceProductPage.btn_categoria).getText();
        System.out.println(categoria);
        System.out.println(categoria_page);
        Assert.assertEquals("Nombre de la categoria",categoria,categoria_page);
    }

    public void clickBtnCategoria() {
        this.driver.findElement(priceProductPage.btn_categoria).click();
    }

    public void verificarSubcategoria(String subcategoria){
        String subcategoria_pag = this.driver.findElement(priceProductPage.btn_subcategoria).getText();
        System.out.println(subcategoria);
        System.out.println(subcategoria_pag);
        Assert.assertEquals("Nombre de la Subcategoría",subcategoria,subcategoria_pag);
    }

    public void clickBtnSubcategoria(){
        this.driver.findElement(priceProductPage.btn_subcategoria).click();
    }

    public void clickThumbnail(){
        this.driver.findElement(priceProductPage.producto_thumbnail).click();
    }

    public void clickAgregar1Producto(){
        this.driver.findElement(priceProductPage.touch_spin_agregar).click();
    }

    public void clickAgregarAlCarrito(){
        this.driver.findElement(priceProductPage.btn_agregar_carrito).click();
    }

    public void confirmarPopUp(){
        String titulo_popup_con_imagen_de_check = this.driver.findElement(priceProductPage.confirmacion_popup).getText();
        String titulo_popup = titulo_popup_con_imagen_de_check.substring(1);
        String etiqueta_esperada = "Producto añadido correctamente a su carrito de compra";
        Assert.assertEquals("Etiqueta de Confirmación de Agregar Productos Al Carrito",etiqueta_esperada,titulo_popup);
    }

    public void confirmarMontoPopUp(){
        float precio_unitario = Float.parseFloat(this.driver.findElement(priceProductPage.precio_unitario).getText().substring(3));
        float cantidad_producto = Float.parseFloat(this.driver.findElement(priceProductPage.cantidad_producto).getText());
        float total_monto = Float.parseFloat(this.driver.findElement(priceProductPage.total_monto).getText().substring(3));

        Assert.assertEquals("Monto Esperado Pop Up:",String.valueOf(total_monto),String.valueOf(precio_unitario*cantidad_producto));
    }

    public void clickBtnFinalizarCompraPopUp(){
        this.driver.findElement(priceProductPage.btn_finalizar_compra_popup).click();
    }

    public void verificarTituloCarritoDeCompras(){
        String etiqueta_pag = this.driver.findElement(priceProductPage.etiqueta_carrito).getText();
        String etiqueta_esperada = "CARRITO";
        Assert.assertEquals("Etiqueta de Carrito de Compras Esperada:",etiqueta_esperada,etiqueta_pag);
    }

    public void verificarMontosCarritoDeCompras(){
        float precio_unitario = Float.parseFloat(this.driver.findElement(priceProductPage.etiqueta_precio_unitario).getText().substring(3));
        float cantidad_producto = Float.parseFloat(this.driver.findElement(priceProductPage.etiqueta_cantidad_producto).getText().substring(0,1));
        float total_monto = Float.parseFloat(this.driver.findElement(priceProductPage.etiqueta_monto_total).getText().substring(3));

        Assert.assertEquals("Monto Esperado Carrito de Compras:",String.valueOf(total_monto),String.valueOf(precio_unitario*cantidad_producto));
    }
}
