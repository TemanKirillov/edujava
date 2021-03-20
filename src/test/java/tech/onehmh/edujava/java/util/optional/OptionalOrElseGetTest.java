package tech.onehmh.edujava.java.util.optional;

import org.junit.Test;

import java.util.Optional;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Тесты метода {@link Optional#orElseGet(Supplier)}
 */
public class OptionalOrElseGetTest
{
    @Test
    public void orElseGetEmptyOptional()
    {
        String result = Optional.<String>empty().orElseGet(() -> "1");
        assertEquals("1", result);
    }

    @Test
    public void orElseGetNotEmptyOptional()
    {
        String result = Optional.of("10").orElseGet(() -> "1");
        assertEquals("10", result);
    }

    /**
     * Под orElseGet выражение не вызывается, если возвращается только первое значение
     * Есть различия с поведением {@link Optional#orElse(Object)}
     *
     * @see OptionalOrElseTest#orElseNotEmptyOptionalWithCallUnderElse()
     */
    @Test
    public void orElseGetNotEmptyOptionalWithCallUnderElse()
    {
        ClassWithInternalState obj = new ClassWithInternalState("1");
        String result = Optional.of("10").orElseGet(() -> obj.getAndSet("20"));
        assertEquals("10", result);
        assertEquals("1", obj.getData());
    }

    @Test
    public void orElseGetEmptyOptionalAndNull()
    {
        String result = Optional.<String>empty().orElseGet(() -> null);
        assertNull(result);
    }
}
