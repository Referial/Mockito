package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    LocalizationService localizationService = new LocalizationServiceImpl();

    @Test
    void localeRussia (){
        String actual = localizationService.locale(Country.RUSSIA);
        String expected = "Добро пожаловать";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void localeUSA (){
        String actual = localizationService.locale(Country.USA);
        String expected = "Welcome";

        Assertions.assertEquals(expected, actual);
    }


}