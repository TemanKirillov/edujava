package tech.onehmh.edujava.java.util.optional;

import org.junit.Test;

import java.util.Optional;
import java.util.function.Predicate;

import static org.junit.Assert.*;

/**
 * Тесты метода {@link Optional#filter(Predicate)}
 */
public class OptionalFilterTest
{
    /**
     * Если к пустому Optional применить фильтр, то получим пустой Optional
     */
    @Test
    public void filterEmptyOptional()
    {
        Optional<String> result = Optional.<String>empty().filter((string) -> string.equals("10"));
        assertFalse(result.isPresent());
    }

    /**
     * Optional на выходе тот же, что и на входе, так как предикат вернул true
     */
    @Test
    public void optionalNotEmptyFilterTrue()
    {
        String data = "10";
        Optional<String> result = Optional.of(data).filter((string) -> string.equals("10"));
        assertEquals("10", result.get());
    }

    /**
     * Пустой Optional на выходе, так как предикат вернул false
     */
    @Test
    public void optionalNotEmptyFilterFalse()
    {
        String data = "1";
        Optional<String> result = Optional.of(data).filter((string) -> string.equals("10"));
        assertFalse(result.isPresent());
    }

}
