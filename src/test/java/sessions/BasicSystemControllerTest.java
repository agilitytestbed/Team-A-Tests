package sessions;

import categories.Category;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.DependsOn;
import transactions.Transaction;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static io.restassured.RestAssured.*;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.DOUBLE;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BasicSystemControllerTest {
    public static final String API_URL = "/api/v1";
    public static final AtomicInteger counter = new AtomicInteger();
    private Category categorySeven;
    private Category categorySix;

    @Before
    public void setup(){/*
        get(API_URL + "/sessions");
        counter.incrementAndGet();
        Transaction setupTransaction = new Transaction();
        setupTransaction.setAmount(300);
        given().contentType("application/json").body(setupTransaction)
                .put(API_URL + "/{sessionId}/transactions", 1);
        counter.incrementAndGet();
        Category setupCategory = new Category();
        setupCategory.setName("setupCategory");
        given().contentType("application/json").body(setupCategory)
                .put(API_URL + "/{sessionId}/categories", 1);
        counter.incrementAndGet();
        System.out.println("Setup Complete : " + counter.get());*/
    }

    @After
    public void teardown(){

    }

    @Test
    public void basicSystemTest() throws Exception {
        newSession();
        newTransaction();
        getTransaction();
        getTransactions();
        newCategory();
        getCategory();
        getCategories();
        assignCategory();
        updateCategory();
        updateTransaction();
        deleteTransaction();
        deleteCategory();
    }

    //@Test
    private void newSession() throws Exception {
        System.out.println("[Testing session creation]");
        when().get(API_URL + "/sessions").then().statusCode(201).body("sessionID", equalTo(1));
        when().get(API_URL + "/sessions").then().statusCode(201).body("sessionID", equalTo(2));
    }

    //@Test
    private void newTransaction() throws Exception {
        System.out.println("[Testing transaction creation]");
        Transaction testTransaction = new Transaction();
        testTransaction.setAmount(500.00);
        given().
                config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(DOUBLE))).
                contentType("application/json").
                body(testTransaction).
                queryParam("sessionId", 1).
        when().
                post(API_URL + "/transactions").
        then().
                statusCode(201).
                body(
                        "amount", equalTo(500.00),
                        "id", equalTo(3)
                );

        testTransaction.setAmount(250.00);
        given().
                config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(DOUBLE))).
                contentType("application/json").
                body(testTransaction).
                queryParam("sessionId", 1).
        when().
                post(API_URL + "/transactions").
        then().
                statusCode(201).
                body(
                        "amount", equalTo(250.00),
                        "id", equalTo(4)
                );

        testTransaction.setAmount(750.00);
        testTransaction.setId(2);
        given().
                config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(DOUBLE))).
                contentType("application/json").
                body(testTransaction).
                queryParam("sessionId", 1).
        when().
                post(API_URL + "/transactions").
        then().
                statusCode(201).
                body(
                    "amount", equalTo(750.00),
                    "id", equalTo(5)
                );
    }

    //@Test
    private void getTransaction() throws Exception {
        System.out.println("[Testing getting transaction]");
        given().
                config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(DOUBLE))).
                queryParam("sessionId", 1).
        when().
                get(API_URL + "/transactions/{transactionId}",3).
        then().
                body(
                        "id", equalTo(3),
                        "amount", equalTo(500.00)
                );
    }

    //@Test
    private void getTransactions() throws Exception {
        System.out.println("[Testing getting transactions]");
        given().
                config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(DOUBLE))).
                queryParam("sessionId", 1).
        when().
                get(API_URL + "/transactions/").
        then().
                body("3.amount", equalTo(500.00), "4.amount", equalTo(250.00));
    }

    //@Test
    private void newCategory() throws Exception {
        System.out.println("[Testing category creation]");
        Category testCategory = new Category();
        testCategory.setName("testCategory");
        given().
                config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(DOUBLE))).
                contentType("application/json").
                body(testCategory).
                queryParam("sessionId", 1).
        when().
                post(API_URL + "/categories").
        then().
                statusCode(201).
                body(
                        "name", equalTo("testCategory"),
                        "id", equalTo(6)
                )
        ;
        testCategory.setId(6);
        categorySix = new Category();
        categorySix.setId(6);
        categorySix.setName("testCategory");
        testCategory.setId(3);
        testCategory.setName("anotherCategory");
        given().
                config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(DOUBLE))).
                contentType("application/json").
                body(testCategory).
                queryParam("sessionId", 1).
        when().
                post(API_URL + "/categories").
        then().
                statusCode(201).
                body(
                        "name", equalTo("anotherCategory"),
                        "id", equalTo(7)
                )
        ;
        testCategory.setId(7);
        categorySeven = testCategory;
    }

    //@Test
    private void getCategory() throws Exception {
        System.out.println("[Testing getting category]");
        given().
                config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(DOUBLE))).
                queryParam("sessionId", 1).
        when().
                get(API_URL+"/categories/{categoryId}",6).
        then().
                statusCode(200).body(
                        "name", equalTo("testCategory"),
                "id", equalTo(6)
        );
    }

    //@Test
    private void assignCategory() throws Exception {
        System.out.println("[Testing assigning category]");
        given().
                config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(DOUBLE))).
                contentType("application/json").
                body(categorySix).
                queryParam("sessionId", 1).
        when().
                patch(API_URL + "/transactions/{transactionId}", 4).
        then().
                statusCode(200).
                body(
                        "amount", equalTo(250.00),
                        "category.id", equalTo(6)
                );
    }

    //@Test
    private void getCategories() throws Exception {
        System.out.println("[Testing getting categories]");
        given().
                config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(DOUBLE))).
                contentType("application/json").
                queryParam("sessionId", 1).
        when().
                get(API_URL + "/categories").
        then().
                body("6.name", equalTo("testCategory"), "7.name", equalTo("anotherCategory"));
    }

    private void updateCategory() {
        Category testCategory = new Category();
        testCategory.setName("new testCategory");
        testCategory.setId(50);
        given().
                config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(DOUBLE))).
                contentType("application/json").
                body(testCategory).
                queryParam("sessionId", 1).
        when().
                put(API_URL + "/categories/{categoryId}", 6).
        then().
                statusCode(200).
                body("name",equalTo("new testCategory"), "id", equalTo(6));

        given().
                config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(DOUBLE))).
                contentType("application/json").
                queryParam("sessionId", 1).
        when().
                get(API_URL + "/transactions/{transactionId}",4).
        then().
                body("amount", equalTo(250.0), "category.name", equalTo("new testCategory"));
    }

    private void updateTransaction() {
        Transaction testTransaction = new Transaction();
        testTransaction.setAmount(1000);
        testTransaction.setId(50);
        testTransaction.setCategory(categorySeven);
        given().
                config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(DOUBLE))).
                contentType("application/json").
                body(testTransaction).
                queryParam("sessionId", 1).
        when().
                put(API_URL + "/transactions/{transactionId}", 3).
        then().
                statusCode(200).
                body("id",equalTo(3), "amount", equalTo(1000.0));
    }

    private void deleteTransaction() {
        given().
                config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(DOUBLE))).
                contentType("application/json").
                queryParam("sessionId", 1).
        when().
                delete(API_URL + "/transactions/{transactionId}",5).
        then().
                statusCode(200);

        given().
                config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(DOUBLE))).
                contentType("application/json").
                queryParam("sessionId", 1).
        when().
                get(API_URL + "/transactions/{transactionId}",5).
        then().
                statusCode(400);
    }

    private void deleteCategory() {
        given().
                config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(DOUBLE))).
                contentType("application/json").
                queryParam("sessionId", 1).
        when().
                delete(API_URL + "/categories/{categoryId}",6).
        then().
                statusCode(200);

        given().
                config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(DOUBLE))).
                contentType("application/json").
                queryParam("sessionId", 1).
        when().
                get(API_URL + "/transactions/{transactionId}",4).
        then().
                body("amount", equalTo(250.0), "category.name", isEmptyOrNullString());
    }

}