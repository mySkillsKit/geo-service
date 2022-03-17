package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageSenderImplTest {

    //Написать тесты для проверки языка отправляемого сообщения
    // (класс MessageSender) с использованием Mockito

    //(для сервиса MessageSenderImpl,
    // нужно обязательно создать заглушки (mock)
    // в виде GeoServiceImpl и LocalizationServiceImpl)


    //Поверить, что MessageSenderImpl всегда отправляет только русский текст,
    // если ip относится к российскому сегменту адресов. "172."

    @Test
    void testSendRussianTextIfRusIp(){

        GeoServiceImpl geoServiceImpl = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceImpl.byIp("172.123.12.19"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        LocalizationServiceImpl localizationServiceImpl = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceImpl.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSenderImpl messageSenderImpl = new MessageSenderImpl(geoServiceImpl,localizationServiceImpl);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
        String result = messageSenderImpl.send(headers) ;
        String expected = "Добро пожаловать";
        assertEquals(expected, result);

    }

    ///Поверить, что MessageSenderImpl всегда отправляет только английский текст,
    // если ip относится к американскому сегменту адресов. "96."

    @Test
    void testSendEnglishTextIfAnyIp(){

        GeoServiceImpl geoServiceImpl = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceImpl.byIp("96.44.14.14"))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        LocalizationServiceImpl localizationServiceImpl = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceImpl.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSenderImpl messageSenderImpl = new MessageSenderImpl(geoServiceImpl,localizationServiceImpl);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.14.14");
        String result = messageSenderImpl.send(headers) ;
        String expected = "Welcome";
        assertEquals(expected, result);

    }


}
