package tech.onehmh.edujava.java.util.optional;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Тест метода {@link Optional#isPresent()}
 */
public class OptionalIsPresentTest
{
    @Test
    public void isPresentTrue()
    {
        boolean result = Optional.of("10").isPresent();
        assertTrue(result);
    }

    @Test
    public void isPresentFalse()
    {
        boolean result = Optional.empty().isPresent();
        assertFalse(result);
    }

}
