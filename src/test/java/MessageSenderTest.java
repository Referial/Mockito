package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderTest {
    protected GeoService geoService;
    protected LocalizationService localizationService;
    protected MessageSender messageSender;
    protected Map<String, String> header;


    @BeforeEach
    void setUp() {
        geoService = Mockito.mock(GeoService.class);
        localizationService = Mockito.mock(LocalizationService.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);
        header = new HashMap<>();
    }

    @Test
    void sendRussia (){
        Mockito.when(geoService.byIp("172."))
                .thenReturn(new Location(null, Country.RUSSIA, null,0));
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        header.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.");
        String actual = messageSender.send(header);
        String expected = localizationService.locale(Country.RUSSIA);

        Mockito.verify(geoService, Mockito.times(1)).byIp("172.");
        Mockito.verify(geoService, Mockito.times(0)).byIp("96.");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void sendUSA (){
        Mockito.when(geoService.byIp("96."))
                .thenReturn(new Location(null, Country.USA, null,0));
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        header.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.");
        String actual = messageSender.send(header);
        String expected = localizationService.locale(Country.USA);

        Mockito.verify(geoService, Mockito.times(1)).byIp("96.");
        Mockito.verify(geoService, Mockito.times(0)).byIp("172.");
        Assertions.assertEquals(expected, actual);
    }
}