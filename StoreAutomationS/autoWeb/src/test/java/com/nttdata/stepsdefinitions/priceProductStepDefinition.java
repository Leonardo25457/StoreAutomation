package com.nttdata.stepsdefinitions;

import com.nttdata.steps.priceProductStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static com.nttdata.core.DriverManager.getDriver;
import static com.nttdata.core.DriverManager.screenShot;

public class priceProductStepDefinition {
    private WebDriver driver;
    priceProductStep priceproduct;

    @Given("estoy en la página de la tienda y hago click en el boton Iniciar Sesión")
    public void estoyEnLaPáginaDeLaTiendaYHagoClickEnElBotonIniciarSesión() {
        driver=getDriver();
        driver.get("https://qalab.bensg.com/store/pe/");
        priceproduct = new priceProductStep(driver);
        priceproduct.clickBtnIniciarSesion();
        screenShot();
    }

    @And("me logueo con mi usuario {string} y clave {string}")
    public void meLogueoConMiUsuarioYClave(String email, String pass) {
        driver=getDriver();
        priceproduct.ingresaEmail(email);
        priceproduct.ingresaContraseña(pass);
        screenShot();
        priceproduct.clickBtnIngresa();
    }

    @When("navego a la categoria {string} y subcategoria {string}")
    public void navegoALaCategoriaYSubcategoria(String categoria, String subcategoria) throws InterruptedException {
        priceproduct = new priceProductStep(driver);
        driver=getDriver();
        screenShot();
        priceproduct.verificarBtnCerrarSesion();

        priceproduct.verificarCategoria(categoria);
        priceproduct.clickBtnCategoria();

        driver=getDriver();
        screenShot();
        Thread.sleep(3000);
        priceproduct.verificarSubcategoria(subcategoria);
        priceproduct.clickBtnSubcategoria();
    }

    @And("agrego {int} unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito(int unidades) throws InterruptedException {
        driver=getDriver();
        priceproduct.clickThumbnail();
        Thread.sleep(3000);
        priceproduct.clickAgregar1Producto();
        Thread.sleep(500);
        screenShot();
        priceproduct.clickAgregarAlCarrito();
        Thread.sleep(3000);
    }

    @Then("valido en el popup la confirmación del producto agregado")
    public void validoEnElPopupLaConfirmaciónDelProductoAgregado() {
        driver=getDriver();
        priceproduct.confirmarPopUp();
    }

    @And("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        driver=getDriver();
        priceproduct.confirmarMontoPopUp();
    }

    @When("finalizo la compra")
    public void finalizoLaCompra() throws InterruptedException {
        driver=getDriver();
        screenShot();
        priceproduct.clickBtnFinalizarCompraPopUp();
        Thread.sleep(1000);
    }

    @Then("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        driver=getDriver();
        priceproduct.verificarTituloCarritoDeCompras();
    }

    @And("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {
        driver=getDriver();
        priceproduct.verificarMontosCarritoDeCompras();
        screenShot();
    }
}
