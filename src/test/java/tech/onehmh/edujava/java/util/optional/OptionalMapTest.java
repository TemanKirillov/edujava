package tech.onehmh.edujava.java.util.optional;

import org.junit.Test;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Тесты метода {@link Optional#map(Function)}
 */
public class OptionalMapTest
{
    /**
     * на пустом Optional map всегда возвращает пустой Optional
     */
    @Test
    public void mapEmptyOptional()
    {
        Optional<String> result = Optional.<String>empty().map((string) -> string + "!");
        assertFalse(result.isPresent());
    }

    /**
     * с заполненным Optional:
     * 1. map извлекает значение из Optional
     * 2. применяет к этому значению функцию
     * 3. результат запаковывает в Optional и возвращает
     */
    @Test
    public void mapNotEmpty()
    {
        Optional<String> result = Optional.of("1").map((string) -> string + "!");
        assertEquals("1!", result.get());
    }

    /**
     * использование map для получения значения поля
     */
    @Test
    public void mapGetField()
    {
        Cat cat = new Cat("Мурка", "5");
        Optional<String> age = Optional.of(cat).map(Cat::getAge);
        assertEquals("5", age.get());
    }

    /**
     * Извлечении поля Optional из значения, которое обёрнуто в Optional.
     * Получается так, что имя обёрнуто в Optional дважды.
     * Как эта проблема решается во flatMap смотри:
     * @see OptionalFlatMapTest#flatMapGetOptionalFromOptional()
     */
    @Test
    public void mapGetOptionalFromOptional()
    {
        Cat cat = new Cat("Мурка", "5");
        Optional<Optional<String>> catName = Optional.of(cat).map(Cat::getName);
        assertEquals("Мурка", catName.get().get());
    }


}
