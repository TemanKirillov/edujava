package tech.onehmh.edujava.java.util.optional;

import org.junit.Test;

import java.util.Optional;
import java.util.function.Function;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Тесты метода {@link Optional#flatMap(Function)}
 * Нужен, чтобы избежать двойной обёртки в Optional при извлечении при помощи map поля Optional из значения,
 *    которое обёрнуто в Optional
 */
public class OptionalFlatMapTest
{
    /**
     * на пустом Optional flatMap всегда возвращает пустой Optional
     *   Функция, которая передана во flatMap должна возвращать Optional
     */
    @Test
    public void flatMapEmptyOptional()
    {
        Optional<String> result = Optional.<String>empty().flatMap((string) -> Optional.of("1"));
        assertFalse(result.isPresent());
    }

    /**
     * Извлечении поля Optional из значения, которое обёрнуто в Optional
     * Различия с map смотри:
     * @see OptionalMapTest#mapGetOptionalFromOptional()
     */
    @Test
    public void flatMapGetOptionalFromOptional()
    {
        Cat cat = new Cat("Мурка", "5");
        Optional<String> catName = Optional.of(cat).flatMap(Cat::getName);
        assertEquals("Мурка", catName.get());
    }
}
