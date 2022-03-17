package ru.netology.i18n;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceImplTest {

//Написать тесты для проверки возвращаемого текста (класс LocalizationServiceImpl)
//Проверить работу метода public String locale(Country country)


    @ParameterizedTest
    @MethodSource("source")
   public void testLocale(Country country, String expected) {

        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String actual = localizationService.locale(country);

        assertEquals(expected, actual);

    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.GERMANY, "Welcome"),
                Arguments.of(Country.BRAZIL, "Welcome")

        );
    }

}
