package tech.onehmh.edujava.h2jdbc;

/**
 * Поля таблицы CITY
 */
public enum CityField
{
    ID("ID"),
    NAME("NAME");

    private final String name;

    CityField(String name)
    {
        this.name = name;
    }

    public String getNameField()
    {
        return name;
    }
}
