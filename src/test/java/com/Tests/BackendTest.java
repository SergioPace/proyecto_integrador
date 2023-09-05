package com.Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.extentReports.ExtentFactory;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BackendTest {

    String urlBase = "https://parabank.parasoft.com/parabank/services_proxy/bank/";
    String customerId = "14321";
    String accountId = "15453";
    String username = "user17";
    String password = "password";

    static ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTES-BACKEND.html");
    static ExtentReports extent;

    @BeforeAll
    public static void crearReporte() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    @BeforeEach
    public void time() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test
    @Order(1)
    public void registro() {
        given()
                .get("https://parabank.parasoft.com/parabank/register.htm")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    @Order(2)
    public void login() {
        String response = given()
                .get("https://parabank.parasoft.com/parabank/services/bank/" +"login/" + username + "/" + password )
                .then()
                .statusCode(200)
                .log().status()
                .log().body()
                .extract().asString();

        int tagInicio = response.indexOf("<id>") + "<id>".length();
        int tagFinal = response.indexOf("</id>");
        customerId = response.substring(tagInicio, tagFinal);

        System.out.println("Customer ID: " + customerId);
    }


    @Test
    @Order(3)
    public void crearCuenta() {
        String response =given()
                .get("https://parabank.parasoft.com/parabank/services/bank/customers/"+customerId+"/accounts")
                .then()
                .statusCode(200)
                .log().status()
                .log().body()
                .extract().asString();

        int tagInicio = response.indexOf("<id>") + "<id>".length();
        int tagFinal = response.indexOf("</id>");
        accountId = response.substring(tagInicio, tagFinal);

        System.out.println("Account ID: " + accountId);

    }

    @Test
    @Order(4)
    public void nuevaCuenta() {
        given()
                .auth()
                .basic(username, password)
                .post(urlBase +"createAccount?customerId=" + customerId + "&newAccountType=1&fromAccountId=" + accountId)
                .then()
                .statusCode(200)
                .log().status()
                .log().body();

    }

    @Test
    @Order(5)
    public void resumenCuenta() {
        given()
                .get("https://parabank.parasoft.com/parabank/overview.html")
                .then()
                .statusCode(404)
                .log().status()
                .log().body();
    }

    @Test
    @Order(6)
    public void descargaDeFondos() {
        String amount = "1000";
        given()
                .auth()
                .basic(username, password)
                .post(urlBase + "transfer?fromAccountId="+accountId+ "&toAccountId=15453&amount=" + amount)
                .then()
                .statusCode(200)
                .log().status()
                .log().body();

    }

    @Test
    @Order(7)
    public void actividadCuenta() {
        given()
                .auth().basic(username, password)
                .get(urlBase + "accounts/"+accountId+"/transactions/month/All/type/All")
                .then()
                .statusCode(200)
                .log().status()
                .log().body();

    }

    @AfterAll
    public static void reporte() {
        extent.flush();
    }
}