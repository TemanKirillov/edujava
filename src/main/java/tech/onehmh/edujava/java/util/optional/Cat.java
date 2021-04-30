package tech.onehmh.edujava.java.util.optional;


import java.util.Optional;

/**
 * Кошка, у которой может не быть имени
 */
public class Cat
{
    private String name;
    private String age;

    public Cat(String name, String age)
    {
        this.name = name;
        this.age = age;
    }

    public Optional<String> getName()
    {
        return Optional.ofNullable(name);
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAge()
    {
        return age;
    }

    public void setAge(String age)
    {
        this.age = age;
    }
}
