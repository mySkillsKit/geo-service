package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeoServiceImplTest {

//Написать тесты для проверки определения локации по ip (класс GeoServiceImpl)
//Проверить работу метода public Location byIp(String ip)

    @Test
    void determiningLocationByIp() {

        GeoServiceImpl geoService = new GeoServiceImpl();
        String currentIp = "96.44.183.149";
        Location currentLocation = new Location(null, Country.USA, null, 0);
        Enum expected = currentLocation.getCountry();

        Location locationByIp = geoService.byIp(currentIp);
        Enum actual = locationByIp.getCountry();

        assertEquals(expected, actual);

    }

}
