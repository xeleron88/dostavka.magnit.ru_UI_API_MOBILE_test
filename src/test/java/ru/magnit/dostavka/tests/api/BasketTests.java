package ru.magnit.dostavka.tests.api;

import config.Project;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.magnit.dostavka.tests.api.models.BasketDataModel;
import ru.magnit.dostavka.tests.api.pages.BasketApi;
import ru.magnit.dostavka.tests.api.specs.BasketSpecs;
import ru.magnit.dostavka.tests.api.specs.CleanBasketSpecs;


import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

import static io.restassured.RestAssured.given;

@Tag("API")
@Epic("API")
@Owner("xeleron88")

public class BasketTests {
    private final BasketApi basketApi = new BasketApi();

    @Test
    @DisplayName("Ability to add items to cart")
    public void addItemToBasket() {

        step("Cleaning the basket",
                basketApi::cleanBasket);
        BasketDataModel basketDataModel = step("Add 1 item to basket", () ->
                    given()
                        .spec(BasketSpecs.request)
                        .multiPart("id", Project.config.productId())
                        .multiPart("quantity", "1")
                        .multiPart("shopId", Project.config.shopId())
                    .when()
                        .post("/sale/basket/add/?useSiteXmlIdService=express")
                    .then()
                        .spec(BasketSpecs.response)
                        .extract().as(BasketDataModel.class));

        step("Check response message", () ->
            assertThat(basketDataModel.getMessage()).isEqualTo("Добавление товара в корзину"));
        }

    @Test
    @DisplayName("Ability to add nonexistent items to cart")
    public void addNonexistentItemToBasket() {


        step("Cleaning the basket", basketApi::cleanBasket);
        BasketDataModel basketDataModel = step("Add nonexistent item to basket", () ->
             given()
                    .spec(BasketSpecs.request)
                    .multiPart("id", "000")
                    .multiPart("quantity", "1")
                    .multiPart("shopId", Project.config.shopId())
                    .when()
                    .post("/sale/basket/add/?useSiteXmlIdService=express")
                    .then()
                    .spec(BasketSpecs.response)
                    .extract().as(BasketDataModel.class));

        step("Check response message", () ->
                assertThat(basketDataModel.getMessage()).isEqualTo("невалидный запрос"));
    }

    @Test
    @DisplayName("Ability to add nonexistent count of items to cart with 1 item")
    public void addNonexistentCountOFItemsToNotEmptyBasket() {
        step("Cleaning the basket", basketApi::cleanBasket);
        step("Add 1 items to basket", () -> {
            basketApi.addItemToBasket("1");
        });
        BasketDataModel basketDataModel = step("Add 999 items to basket", () ->
            given()
                    .spec(BasketSpecs.request)
                    .multiPart("id", Project.config.productId())
                    .multiPart("quantity", "999")
                    .multiPart("shopId", Project.config.shopId())
                    .when()
                    .post("/sale/basket/add/?useSiteXmlIdService=express")
                    .then()
                    .spec(BasketSpecs.response)
                    .extract().as(BasketDataModel.class));

        step("Check response message", () ->
                assertThat(basketDataModel.getMessage()).isEqualTo("Не хватает остатков"));
    }

    @Test
    @DisplayName("Ability to add max count of items to empty basket")
    public void addNonexistentCountOFItemsToEmptyBasket() {

        step("Cleaning the basket", basketApi::cleanBasket);
        BasketDataModel basketDataModel = step("Add 999 items to basket", () ->
            given()
                    .spec(BasketSpecs.request)
                    .multiPart("quantity", "999")
                    .when()
                    .post("/sale/basket/add/?useSiteXmlIdService=express")
                    .then()
                    .spec(BasketSpecs.response)
                    .extract().as(BasketDataModel.class));

        step("Check response message", () ->
                assertThat(basketDataModel.getMessage()).isEqualTo("Добавление товара в корзину"));
    }

    @Test
    @DisplayName("Ability to clear the basket")
    public void abilityClearBasket() {
        BasketDataModel basketDataModel = step("Cleaning the basket", () ->
            given()
                    .spec(CleanBasketSpecs.request)
                    .when()
                    .get("/sale/basket/clear/?useSiteXmlIdService=express")
                    .then()
                    .spec(CleanBasketSpecs.response)
                    .extract().as(BasketDataModel.class));
        step("Check response message", () ->
                assertThat(basketDataModel.getMessage()).isEqualTo("Корзина очищена"));
    }

    @Test
    @DisplayName("Ability to update cart item count")
    public void abilityToDeleteItem() {
        String[] basketId = new String[1];
        step("Cleaning the basket", basketApi::cleanBasket);
        BasketDataModel basketDataModel = step("Add 1 items to basket and", () ->
            given()
                    .spec(BasketSpecs.request)
                    .multiPart("quantity", "1")
                    .when()
                    .post("/sale/basket/add/?useSiteXmlIdService=express")
                    .then()
                    .spec(BasketSpecs.response)
                    .extract().as(BasketDataModel.class));

        BasketDataModel finalBasketDataModel = basketDataModel;
        step("Get basket id", () -> {
            basketId[0] = finalBasketDataModel.getData().getBasketItems().get(0).getBasketId();
        });
        basketDataModel = step("Update item count to 3", () ->
             given()
                    .spec(BasketSpecs.request)
                    .multiPart("quantity", "3")
                    .multiPart("basketId", basketId[0])
                    .when()
                    .post("/sale/basket/update/?useSiteXmlIdService=express")
                    .then()
                    .spec(BasketSpecs.response)
                    .extract().as(BasketDataModel.class));

        BasketDataModel finalBasketDataModel1 = basketDataModel;
        step("Checks that the number of items in the basket is 3", () ->
            assertThat(finalBasketDataModel1.getData().getBasketItems().get(0).getQuantity()).isEqualTo("3"));
        };


    }


