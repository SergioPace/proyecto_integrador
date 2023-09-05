package com.Tests;

import com.Pages.RegisterPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.extentReports.ExtentFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistroTest {

    private WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTES-REGISTRO.html");
    static ExtentReports extent;

    @BeforeAll
    public static void crearReporte() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
    }
    
    @Test
    void RegistroExitoso() throws  InterruptedException {
        ExtentTest test = extent.createTest("Prueba de Registro Exitoso");
        test.log(Status.INFO, "Comienza el Test");

        RegisterPage registerPage = new RegisterPage(driver,wait);
        registerPage.setup();
        registerPage.open("https://parabank.parasoft.com/parabank/index.htm");


        test.log(Status.PASS, "Se ha ingresado exitosamente a la página.");

        registerPage.clickRegister();
        test.log(Status.PASS, "Se ha ingresado exitosamente a la página de registro.");

        registerPage.escribirFirstName("NombreTest");
        registerPage.escribirLastName("ApellidoTest");
        registerPage.escribirAddress("AddressTest");
        registerPage.escribirCity("CityTest");
        registerPage.escribirState("StateTest");
        registerPage.escribirZipCode("ZipCodeTest");
        registerPage.escribirTelephone("123456");
        registerPage.escribirSsn("123456");
        registerPage.escribirUsername("user17");
        registerPage.escribirPassword("password");
        registerPage.escribirConfirmacionPassword("password");

        registerPage.clickSubmit();

        assertTrue(registerPage.obtenerMensajeRegistrado().equals("Your account was created successfully. You are now logged in."));

        test.log(Status.PASS, "Se ha registrado correctamente.");
    }


    @AfterEach
    public void cerrar() {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.close();
    }

    @AfterAll
    public static void reporte() {
        extent.flush();
    }

}
