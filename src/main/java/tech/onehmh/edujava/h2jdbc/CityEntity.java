package tech.onehmh.edujava.h2jdbc;

/**
 * Сущность хранения инфо о городе в БД
 */
public class CityEntity
{
    private long id;
    private String name;

    public CityEntity()
    {
    }

    public CityEntity(long id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
