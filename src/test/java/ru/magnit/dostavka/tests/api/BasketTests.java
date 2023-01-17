package ru.magnit.dostavka.tests.api;

import config.Project;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.magnit.dostavka.tests.api.models.BasketDataModel;
import ru.magnit.dostavka.tests.api.pages.BasketPage;
import ru.magnit.dostavka.tests.api.specs.BasketSpecs;
import ru.magnit.dostavka.tests.api.specs.CleanBasketSpecs;


import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

import static io.restassured.RestAssured.given;

@Tag("API")
@Epic("API")
@Owner("xeleron88")

public class BasketTests {

    @Test
    @DisplayName("Ability to add items to cart")
    public void addItemToBasket() {
        BasketDataModel[] basketDataModel = {new BasketDataModel()};
        BasketPage basketPage = new BasketPage();

        step("Cleaning the basket", basketPage::cleanBasket);
        step("Add 1 item to basket", () -> {
            basketDataModel[0] = given()
                    .spec(BasketSpecs.request)
                    .multiPart("id", Project.config.productId())
                    .multiPart("quantity", "1")
                    .multiPart("shopId", Project.config.shopId())
                    .when()
                    .post("/sale/basket/add/?useSiteXmlIdService=express")
                    .then()
                    .spec(BasketSpecs.response)
                    .extract().as(BasketDataModel.class);
        });
        step("Check response message", () ->
            assertThat(basketDataModel[0].getMessage()).isEqualTo("Добавление товара в корзину"));
        }

    @Test
    @DisplayName("Ability to add nonexistent items to cart")
    public void addNonexistentItemToBasket() {
        BasketDataModel[] basketDataModel = {new BasketDataModel()};
        BasketPage basketPage = new BasketPage();

        step("Cleaning the basket", basketPage::cleanBasket);
        step("Add nonexistent item to basket", () -> {
            basketDataModel[0] = given()
                    .spec(BasketSpecs.request)
                    .multiPart("id", "000")
                    .multiPart("quantity", "1")
                    .multiPart("shopId", Project.config.shopId())
                    .when()
                    .post("/sale/basket/add/?useSiteXmlIdService=express")
                    .then()
                    .spec(BasketSpecs.response)
                    .extract().as(BasketDataModel.class);
        });
        step("Check response message", () ->
                assertThat(basketDataModel[0].getMessage()).isEqualTo("невалидный запрос"));
    }

    @Test
    @DisplayName("Ability to add nonexistent count of items to cart with 1 item")
    public void addNonexistentCountOFItemsToNotEmptyBasket() {
        BasketPage basketPage = new BasketPage();
        BasketDataModel[] basketDataModel = {new BasketDataModel()};
        step("Cleaning the basket", basketPage::cleanBasket);
        step("Add 1 items to basket", () -> {
            basketPage.addItemToBasket("1");
        });
        step("Add 999 items to basket", () -> {
            basketDataModel[0] = given()
                    .spec(BasketSpecs.request)
                    .multiPart("id", Project.config.productId())
                    .multiPart("quantity", "999")
                    .multiPart("shopId", Project.config.shopId())
                    .when()
                    .post("/sale/basket/add/?useSiteXmlIdService=express")
                    .then()
                    .spec(BasketSpecs.response)
                    .extract().as(BasketDataModel.class);
        });
        step("Check response message", () ->
                assertThat(basketDataModel[0].getMessage()).isEqualTo("Не хватает остатков"));
    }

    @Test
    @DisplayName("Ability to add max count of items to empty basket")
    public void addNonexistentCountOFItemsToEmptyBasket() {
        BasketPage basketPage = new BasketPage();
        BasketDataModel[] basketDataModel = {new BasketDataModel()};
        step("Cleaning the basket", basketPage::cleanBasket);
        step("Add 999 items to basket", () -> {
            basketDataModel[0] = given()
                    .spec(BasketSpecs.request)
                    .multiPart("quantity", "999")
                    .when()
                    .post("/sale/basket/add/?useSiteXmlIdService=express")
                    .then()
                    .spec(BasketSpecs.response)
                    .extract().as(BasketDataModel.class);
        });
        step("Check response message", () ->
                assertThat(basketDataModel[0].getMessage()).isEqualTo("Добавление товара в корзину"));
    }

    @Test
    @DisplayName("Ability to clear the basket")
    public void abilityClearBasket() {
        BasketDataModel[] basketDataModel = {new BasketDataModel()};
        step("Cleaning the basket", () -> {
            basketDataModel[0] = given()
                    .spec(CleanBasketSpecs.request)
                    .when()
                    .get("/sale/basket/clear/?useSiteXmlIdService=express")
                    .then()
                    .spec(CleanBasketSpecs.response)
                    .extract().as(BasketDataModel.class);
        });
        step("Check response message", () ->
                assertThat(basketDataModel[0].getMessage()).isEqualTo("Корзина очищена"));
    }

    @Test
    @DisplayName("Ability to update cart item count")
    public void abilityToDeleteItem() {
        BasketPage basketPage = new BasketPage();
        BasketDataModel[] basketDataModel = {new BasketDataModel()};
        String[] basketId = new String[1];
        step("Cleaning the basket", basketPage::cleanBasket);
        step("Add 1 items to basket and", () -> {
            basketDataModel[0] = given()
                    .spec(BasketSpecs.request)
                    .multiPart("quantity", "1")
                    .when()
                    .post("/sale/basket/add/?useSiteXmlIdService=express")
                    .then()
                    .spec(BasketSpecs.response)
                    .extract().as(BasketDataModel.class);
        });
        step("Get basket id", () -> {
            basketId[0] = basketDataModel[0].getData().getBasketItems().get(0).getBasketId();
        });
        step("Update item count to 5", () -> {
            basketDataModel[0] = given()
                    .spec(BasketSpecs.request)
                    .multiPart("quantity", "5")
                    .multiPart("basketId", basketId[0])
                    .when()
                    .post("/sale/basket/update/?useSiteXmlIdService=express")
                    .then()
                    .spec(BasketSpecs.response)
                    .extract().as(BasketDataModel.class);
        });
        step("Checks that the number of items in the basket is 5", () ->
            assertThat(basketDataModel[0].getData().getBasketItems().get(0).getQuantity()).isEqualTo("5"));
        };


    }


