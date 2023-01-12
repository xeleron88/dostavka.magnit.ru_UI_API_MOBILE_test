package ru.magnit.dostavka.tests.api.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BasketItemModel {
    private String basketId;
    private String productId;
    private String offerId;
    private String quantity;
}
