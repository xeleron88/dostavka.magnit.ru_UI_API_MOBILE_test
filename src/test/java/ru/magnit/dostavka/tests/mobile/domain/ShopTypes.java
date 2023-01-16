package ru.magnit.dostavka.tests.mobile.domain;

public enum ShopTypes {
    EXPRESS("Экспресс"),
    COSMETIC("Косметик"),
    FAMILY("Семейный");

    public final String shopType;

    ShopTypes(String shopType) {
        this.shopType = shopType;
    }
}
