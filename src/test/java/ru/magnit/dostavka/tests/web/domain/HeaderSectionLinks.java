package ru.magnit.dostavka.tests.web.domain;

import config.Project;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum HeaderSectionLinks {
    VEGETABLES("Овощи и фрукты", "ovoshchi_frukty/"),
    MILK("Молоко, сыр, яйца", "moloko_syr_yaytsa/"),
    MEAT("Мясо, птица", "myaso_ptitsa_kolbasy/"),
    FOOD("Готовая еда", "gotovaya_eda/"),
    DRINKS("Напитки", "napitki_soki_voda/"),
    TEA("Чай, кофе, какао", "chay_kofe_kakao/"),
    BREAD("Хлеб, выпечка", "khleb_vypechka_sneki/");

    private final String name;
    private final String categoryUrl;

    public String getUrl() {
        return Project.config.baseUrl() + "/catalog/" + categoryUrl;
    }

    @Override
    public String toString() {
        return name;
    }


}
