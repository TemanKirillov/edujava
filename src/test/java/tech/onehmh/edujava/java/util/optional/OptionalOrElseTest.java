package tech.onehmh.edujava.java.util.optional;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Тесты метода {@link Optional#orElse(Object)}
 */
public class OptionalOrElseTest
{
    @Test
    public void orElseEmptyOptional()
    {
        String result = Optional.<String>empty().orElse("1");
        assertEquals("1", result);
    }

    @Test
    public void orElseNotEmptyOptional()
    {
        String result = Optional.of("10").orElse("1");
        assertEquals("10", result);
    }

    /**
     * Под orElse выражение вызывается в любом случае, даже если возвращается только первое значение
     */
    @Test
    public void orElseNotEmptyOptionalWithCallUnderElse()
    {
        ClassWithInternalState obj = new ClassWithInternalState("1");
        String result = Optional.of("10").orElse(obj.getAndSet("20"));
        assertEquals("10", result);
        assertEquals("20", obj.getData());
    }

    @Test
    public void orElseEmptyOptionalAndNull()
    {
        String result = Optional.<String>empty().orElse(null);
        assertNull(result);
    }
}
