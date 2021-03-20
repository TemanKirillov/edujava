package tech.onehmh.edujava.java.util.optional;

import org.junit.Test;

import java.util.Optional;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

/**
 * Тесты метода {@link Optional#orElseThrow(Supplier)}
 */
public class OptionalOrElseThrowTest
{
    /**
     * Если под Optional null, то выбрасывается исключение
     */
    @Test(expected = RuntimeException.class)
    public void orElseThrowEmptyOptional()
    {
        Optional.<String>empty().orElseThrow(() -> new RuntimeException("Message"));
    }

    /**
     * Если под Optional не null, то это значение возвращается
     */
    @Test
    public void orElseThrowNotEmptyOptional()
    {
        String result = Optional.of("10").orElseThrow(() -> new RuntimeException("Message"));
        assertEquals("10", result);
    }

    /**
     * Под orElseThrow выражение не вызывается, если возвращается только первое значение
     * Есть различия с поведением {@link Optional#orElse(Object)}
     *
     * @see OptionalOrElseTest#orElseNotEmptyOptionalWithCallUnderElse()
     */
    @Test
    public void orElseThrowNotEmptyOptionalWithCallUnderElse()
    {
        ClassWithInternalState obj = new ClassWithInternalState("1");
        String result = Optional.of("10").orElseThrow(
                () -> {
                    obj.setData("20");
                    return new RuntimeException("Message");
                });
        assertEquals("10", result);
        assertEquals("1", obj.getData());
    }

}
