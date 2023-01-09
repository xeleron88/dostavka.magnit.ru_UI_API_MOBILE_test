package ru.magnit.dostavka.tests.web.domain;

import config.Project;

public enum HeaderSectionLinks {
    VEGETABLES("Овощи и фрукты", Project.config.baseUrl() + "/catalog/ovoshchi_frukty/"),
    MILK("Молоко, сыр, яйца", Project.config.baseUrl() + "/catalog/moloko_syr_yaytsa/"),
    MEAT("Мясо, птица, колбасы", Project.config.baseUrl() + "/catalog/myaso_ptitsa_kolbasy/"),
    FOOD("Готовая еда", Project.config.baseUrl() + "/catalog/gotovaya_eda/"),
    DRINKS("Напитки", Project.config.baseUrl() + "/catalog/napitki_soki_voda/"),
    TEA("Чай, кофе, какао", Project.config.baseUrl() + "/catalog/chay_kofe_kakao/"),
    BREAD("Хлеб, выпечка", Project.config.baseUrl() + "/catalog/khleb_vypechka_sneki/");

    public final String name;
    public final String url;

    HeaderSectionLinks(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
