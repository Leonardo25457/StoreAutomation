package com.ntt;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class StoreTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private ExtentReports extent;
    private ExtentTest test;

    private final String baseUrl = "https://qalab.bensg.com/store/pe/";

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    /**
     * Escenario: Validación del precio de un producto.
     * Flujo: Login con credenciales válidas, navegación a la categoría "Clothes" y subcategoría "Men",
     * agregar 2 unidades del primer producto al carrito, validación en el popup y en la página del carrito.
     */
    @Test
    public void testValidProductPrice() {
        test = extent.createTest("Valid Product Price Test", "Flujo completo con credenciales válidas");
        try {
            // 1. Abrir la página de la tienda
            driver.get(baseUrl);
            test.info("Navegó a la página de la tienda: " + baseUrl);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("homeBanner")));
            test.pass("Página de la tienda cargada exitosamente");

            // 2. Iniciar sesión con usuario y contraseña válidos
            driver.findElement(By.id("loginBtn")).click();
            test.info("Clic en botón de Login");
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            WebElement passwordField = driver.findElement(By.id("password"));
            usernameField.sendKeys("validUser");
            passwordField.sendKeys("validPassword");
            driver.findElement(By.id("submitLogin")).click();
            test.info("Envío de credenciales válidas");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userMenu")));
            test.pass("Autenticación exitosa");

            // 3. Navegar a la categoría "Clothes" y subcategoría "Men"
            WebElement category = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Clothes")));
            category.click();
            test.info("Seleccionada categoría 'Clothes'");
            WebElement subCategory = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Men")));
            subCategory.click();
            test.info("Seleccionada subcategoría 'Men'");

            // 4. Agregar 2 unidades del primer producto al carrito
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("product-item")));
            WebElement firstProduct = driver.findElements(By.className("product-item")).get(0);
            firstProduct.click();
            test.info("Seleccionado el primer producto");
            WebElement quantityField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("quantity")));
            quantityField.clear();
            quantityField.sendKeys("2");
            test.info("Cantidad configurada a 2");
            driver.findElement(By.id("addToCart")).click();
            test.info("Producto agregado al carrito");
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmationPopup")));
            test.pass("Popup de confirmación mostrado");

            // 5. Validar en el popup la confirmación del producto agregado
            String popupText = popup.getText().toLowerCase();
            Assert.assertTrue("El mensaje del popup no contiene 'agregado'", popupText.contains("agregado"));
            test.pass("Mensaje de confirmación validado en el popup");

            // 6. Validar en el popup que el monto total sea calculado correctamente
            WebElement unitPriceElement = popup.findElement(By.id("unitPrice"));
            WebElement totalPriceElement = popup.findElement(By.id("totalPrice"));
            double unitPrice = Double.parseDouble(unitPriceElement.getText().replace("$", "").trim());
            double totalPrice = Double.parseDouble(totalPriceElement.getText().replace("$", "").trim());
            double expectedTotal = unitPrice * 2;
            Assert.assertEquals("El total calculado en el popup es incorrecto", expectedTotal, totalPrice, 0.01);
            test.pass("Cálculo del monto en el popup validado");

            // 7. Finalizar la compra
            driver.findElement(By.id("finalizePurchase")).click();
            test.info("Clic en 'Finalizar Compra'");
            wait.until(ExpectedConditions.titleContains("Carrito"));
            test.pass("Redirigido a la página del carrito");

            // 8. Validar el título de la página del carrito
            String cartTitle = driver.getTitle();
            Assert.assertTrue("El título de la página del carrito no contiene 'Carrito'", cartTitle.contains("Carrito"));
            test.pass("Título de la página del carrito validado");

            // 9. Validar el cálculo de precios en el carrito
            WebElement cartTotalElement = driver.findElement(By.id("cartTotal"));
            double cartTotal = Double.parseDouble(cartTotalElement.getText().replace("$", "").trim());
            Assert.assertEquals("El total en el carrito es incorrecto", expectedTotal, cartTotal, 0.01);
            test.pass("Cálculo de precios en el carrito validado");

            test.info("Escenario 'Validación del precio de un producto' completado exitosamente");
        } catch(Exception e) {
            test.fail("Fallo en el test: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Escenario: Usuario y contraseña inválidos.
     * Se espera que la automatización falle al no llegar a la pantalla principal.
     */
    @Test
    public void testInvalidLogin() {
        test = extent.createTest("Invalid Login Test", "Verifica que login falle con credenciales inválidas");
        try {
            // 1. Abrir la página de la tienda
            driver.get(baseUrl);
            test.info("Navegó a la página de la tienda");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("homeBanner")));
            test.pass("Página de la tienda cargada");

            // 2. Intentar login con credenciales inválidas
            driver.findElement(By.id("loginBtn")).click();
            test.info("Clic en botón de Login");
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            WebElement passwordField = driver.findElement(By.id("password"));
            usernameField.sendKeys("invalidUser");
            passwordField.sendKeys("invalidPassword");
            driver.findElement(By.id("submitLogin")).click();
            test.info("Envío de credenciales inválidas");

            // Validar que aparezca un mensaje de error (por ejemplo, un elemento con id "loginError")
            WebElement loginError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginError")));
            Assert.assertTrue("No se mostró el mensaje de error esperado", loginError.isDisplayed());
            test.pass("Error de autenticación mostrado correctamente con credenciales inválidas");
        } catch(Exception e) {
            test.fail("Fallo en el test de login inválido: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Escenario: Navegación a una categoría inexistente ("Autos").
     * Se espera que la automatización falle y no continúe.
     */
    @Test
    public void testNonExistentCategory() {
        test = extent.createTest("Non-Existent Category Test", "Verifica que al intentar navegar a una categoría inexistente ('Autos') la prueba se detenga");
        try {
            // 1. Abrir la página de la tienda
            driver.get(baseUrl);
            test.info("Navegó a la página de la tienda");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("homeBanner")));
            test.pass("Página de la tienda cargada");

            // 2. Iniciar sesión con credenciales válidas
            driver.findElement(By.id("loginBtn")).click();
            test.info("Clic en botón de Login");
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            WebElement passwordField = driver.findElement(By.id("password"));
            usernameField.sendKeys("validUser"); // Reemplazar por credenciales válidas
            passwordField.sendKeys("validPassword");
            driver.findElement(By.id("submitLogin")).click();
            test.info("Envío de credenciales válidas");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userMenu")));
            test.pass("Login exitoso");

            // 3. Intentar navegar a la categoría inexistente "Autos"
            try {
                WebElement nonExistentCategory = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Autos")));
                nonExistentCategory.click();
                test.fail("La categoría 'Autos' se encontró y se pudo hacer clic, lo cual es incorrecto.");
                Assert.fail("La categoría inexistente 'Autos' no debería estar presente.");
            } catch (TimeoutException te) {
                test.pass("La categoría 'Autos' no se encontró, como se esperaba.");
            }
        } catch(Exception e) {
            test.fail("Fallo en el test de categoría inexistente: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @After
    public void tearDown() {
        extent.flush();
        if(driver != null) {
            driver.quit();
        }
    }
}
