package tech.onehmh.edujava.classIsInstance;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Тесты, которые показывают эквивалентность использования
 *     {@code child instanceof Parent} и
 *     {@code Parent.class.isInstance(child)}
 */
public class ClassIsInstanceTest
{
    private static class Parent {}
    private static class Child extends Parent {}
    private static class OtherClass {}

    /**
     * Экземпляр дочернего класса является экземпляром родительского класса
     */
    @Test
    public void childInstanceofParent()
    {
        Child child = new Child();
        assertTrue(child instanceof Parent);
        assertTrue(Parent.class.isInstance(child));
    }

    /**
     * Экземпляр родительского класса НЕявляется экземпляром дочернего класса
     */
    @Test
    public void parentInstanceofChild()
    {
        Parent parent = new Parent();
        assertFalse(parent instanceof Child);
        assertFalse(Child.class.isInstance(parent));
    }

    /**
     * Экземпляр класса является экземпляром своего класса
     */
    @Test
    public void instanceofYourself()
    {
        Child child = new Child();
        assertTrue(child instanceof Child);
        assertTrue(Child.class.isInstance(child));
    }

    /**
     * null не является ничьим экземпляром
     */
    @Test
    public void nullNotInstanceOfClass()
    {
        assertFalse(null instanceof Child);
        assertFalse(Child.class.isInstance(null));
    }

    /**
     * не является экземпляром постороннего класса
     */
    @Test
    public void notInstanceOfOtherClass()
    {
        Object child = new Child();
        assertFalse(child instanceof OtherClass);
        assertFalse(OtherClass.class.isInstance(child));
    }

}
