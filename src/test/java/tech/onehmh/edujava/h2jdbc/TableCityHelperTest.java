package tech.onehmh.edujava.h2jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Тесты работы с таблицей CITY
 */
public class TableCityHelperTest
{
    /**
     * Создать таблицу
     * @see TableCityHelper#createTable
     */
    @Test
    public void createTable() throws SQLException
    {
        try (Connection connection = H2ConnectionHelper.INSTANCE.createConnectionToEmptyDB())
        {
            assertTrue(H2ConnectionHelper.INSTANCE.getTables(connection).isEmpty());

            TableCityHelper.INSTANCE.createTable(connection);

            Set<String> tables = H2ConnectionHelper.INSTANCE.getTables(connection);
            assertEquals(1, tables.size());
            assertTrue(tables.contains("CITY"));
        }
    }

    /**
     * Заполнить таблицу значениями
     * @throws SQLException проблема добавления значений в таблицу
     * @see TableCityHelper#writeCity
     */
    @Test
    public void insertCities() throws SQLException
    {
        try (Connection connection = H2ConnectionHelper.INSTANCE.createConnectionToEmptyDB())
        {
            TableCityHelper.INSTANCE.createTable(connection);
            TableCityHelper.INSTANCE.writeCity(connection, "Вологда");

            Optional<CityEntity> city = TableCityHelper.INSTANCE.getCityByName(connection, "Вологда");
            assertTrue(city.isPresent());
            assertEquals("Вологда", city.get().getName());
        }
    }

    /**
     * Удаление города из таблицы БД
     * @throws SQLException ошибка при удалении
     * @see TableCityHelper#deleteCity
     */
    @Test
    public void deleteCities() throws SQLException
    {
        try (Connection connection = H2ConnectionHelper.INSTANCE.createConnectionToFilledDB())
        {
            // до удаления город в БД есть
            Optional<CityEntity> city = TableCityHelper.INSTANCE.getCityByName(connection, "Вологда");
            assertTrue(city.isPresent());
            assertEquals("Вологда", city.get().getName());

            TableCityHelper.INSTANCE.deleteCity(connection, "Вологда");

            // после удаления его уже там нет
            city = TableCityHelper.INSTANCE.getCityByName(connection, "Вологда");
            assertFalse(city.isPresent());
        }
    }
}