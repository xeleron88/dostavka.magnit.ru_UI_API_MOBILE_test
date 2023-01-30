package ru.magnit.dostavka.tests.api.specs;

import config.App;
import config.Project;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import ru.magnit.dostavka.helpers.CustomAllureListener;

import static io.restassured.RestAssured.with;

public class BasketSpecs {
    public static RequestSpecification request = with()
            .filter(CustomAllureListener.withCustomTemplates())
            .log().all()
            .baseUri(Project.config.apiBaseUrl())
            .log().all()
            .cookie("PHPSESSID", App.config.httpSessId())
            .contentType("multipart/form-data")
            .multiPart("id", Project.config.productId())
            .multiPart("shopId", Project.config.shopId());

    public static ResponseSpecification response = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .build();
}

