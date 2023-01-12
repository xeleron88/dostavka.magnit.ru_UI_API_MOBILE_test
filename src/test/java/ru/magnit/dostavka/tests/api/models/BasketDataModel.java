package ru.magnit.dostavka.tests.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BasketDataModel {
    private BasketItemsListModel data;
    private String message;
}
