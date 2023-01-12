package ru.magnit.dostavka.tests.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BasketItemsListModel {
    private List<BasketItemModel> basketItems;
}
