package tech.onehmh.edujava.java.util.optional;

import org.junit.Test;

import java.util.Optional;

/**
 * Тестирование способов создания {@link Optional}
 */
public class OptionalCreationTest
{
    /**
     * Передача null в метод of возбуждает исключение.
     * Полезно для раннего обнаружения null там, где он не подразумевается контрактом.
     */
    @Test(expected = NullPointerException.class)
    public void ofNull()
    {
        Optional.of(null);
    }
}
