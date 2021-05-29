package tech.onehmh.edujava.h2jdbc;

import java.sql.*;
import java.util.Optional;

/**
 * Помощник для работы с таблицей CITY в БД
 */
public enum TableCityHelper
{
    INSTANCE;

    /**
     * Создать таблицу с использованием соединения
     * @param connection соединение к БД
     */
    public void createTable(Connection connection)
    {
        try (Statement statement = connection.createStatement())
        {
            statement.execute(
                    "CREATE TABLE CITY (" +
                            "ID INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                            "NAME VARCHAR(50) NOT NULL" +
                            ");"
            );
        }
        catch (SQLException e)
        {
            throw new IllegalStateException("Не удалось создать таблицу", e);
        }
    }

    /**
     * Записать город в таблицу
     *
     * @param connection соединение к БД
     * @param cityName имя города
     */
    public void writeCity(Connection connection, String cityName)
    {
        String sql = "INSERT INTO CITY(NAME) VALUES (?);";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, cityName);
            statement.execute();
        }
        catch (SQLException e)
        {
            throw new IllegalStateException("Не удалось произвести запись", e);
        }
    }

    /**
     * Удалить город из таблицы
     *
     * @param connection соединение к БД
     * @param cityName имя города
     */
    public void deleteCity(Connection connection, String cityName)
    {
        String sql = "DELETE FROM CITY WHERE NAME=(?);";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, cityName);
            statement.execute();
        }
        catch (SQLException e)
        {
            throw new IllegalStateException("Не удалось произвести удаление", e);
        }
    }

    /**
     * Получить город из БД по имени
     *
     * @param connection соединение к БД
     * @param cityName имя города
     * @return город
     */
    public Optional<CityEntity> getCityByName(Connection connection, String cityName)
    {
        String sql = "SELECT ID, NAME FROM CITY WHERE NAME = ?";
        ResultSet result;

        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, cityName);
            result = statement.executeQuery();
        }
        catch (SQLException e)
        {
            throw new IllegalStateException("Ошибка получения данных из БД", e);
        }

        try
        {
            result.next();
            CityEntity city = new CityEntity();
            city.setId(result.getLong(CityField.ID.getNameField()));
            city.setName(result.getString(CityField.NAME.getNameField()));
            return Optional.of(city);
        }
        catch (SQLException e)
        {
            return Optional.empty();
        }
    }
}
