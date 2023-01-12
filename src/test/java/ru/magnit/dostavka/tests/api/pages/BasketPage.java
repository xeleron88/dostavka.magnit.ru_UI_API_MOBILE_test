package ru.magnit.dostavka.tests.api.pages;

import config.Project;
import ru.magnit.dostavka.tests.api.specs.BasketSpecs;
import ru.magnit.dostavka.tests.api.specs.CleanBasketSpecs;

import static io.restassured.RestAssured.*;
import static ru.magnit.dostavka.tests.api.specs.BasketSpecs.request;
import static ru.magnit.dostavka.tests.api.specs.BasketSpecs.response;

public class BasketPage {

    public void cleanBasket() {
        given()
                .spec(CleanBasketSpecs.request)
                .when()
                .get("/sale/basket/clear/?useSiteXmlIdService=express")
                .then()
                .spec(CleanBasketSpecs.response);
    }

    public void addItemToBasket(String count) {
        given()
                .spec(BasketSpecs.request)
                .multiPart("quantity", count)
                .when()
                .post("/sale/basket/add/?useSiteXmlIdService=express")
                .then()
                .spec(BasketSpecs.response);
    }


}
