package tech.onehmh.edujava.java.util.optional;

import org.junit.Test;

import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

/**
 * Тест метода {@link Optional#ifPresent(Consumer)}
 */
public class OptionalIfPresentTest
{
    /**
     * Если в Optional есть объект, то выполнить код изменения состоянии этого объекта. Или любого другого из доступных.
     */
    @Test
    public void ifPresentTrue()
    {
        ClassWithInternalState obj = new ClassWithInternalState("1");
        Optional.of(obj)
                .ifPresent((objWithState) ->
                {
                    objWithState.setData("10");
                });
        assertEquals("10", obj.getData());
    }

    /**
     * Если объекта в Optional нет, то код изменения состояния не выполняется
     */
    @Test
    public void ifPresentFalse()
    {
        ClassWithInternalState obj = new ClassWithInternalState("1");
        Optional.<ClassWithInternalState>empty()
                .ifPresent((objWithState) ->
                {
                    objWithState.setData("10");
                });
        assertEquals("1", obj.getData());
    }

}
