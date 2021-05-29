package tech.onehmh.edujava.h2jdbc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Помощник по созданию соединений к БД H2
 */
public enum H2ConnectionHelper
{
    INSTANCE;

    private static final String JDBC_H2_PREFIX = "jdbc:h2:";
    private static final String DB_PATH = "h2jdbc/createDb.mv.db";
    private static final String DB_JDBC_PATH = "jdbc:h2:./h2jdbc/createDb";
    private static final String USER_H2_DEFAULT = "sa";
    private static final String PASSWORD_H2_DEFAULT = "";

    /**
     * Создать новую БД
     */
    public void createNewDb()
    {
        deleteDbIfExist();
        try
        {
            Connection conn = getConnection();
            conn.close();
        }
        catch (SQLException e)
        {
            throw new IllegalStateException("Проблема с созданием БД", e);
        }
    }

    /**
     * Создать подключение к пустой БД
     * @return подключение
     */
    public Connection createConnectionToEmptyDB()
    {
        createNewDb();
        return getConnection();
    }

    public Connection createConnectionToFilledDB()
    {
        createNewDb();
        Connection connection = getConnection();
        TableCityHelper.INSTANCE.createTable(connection);
        TableCityHelper.INSTANCE.writeCity(connection, "Вологда");
        return connection;
    }

    /**
     * @return Соединение к БД
     */
    @SuppressWarnings("squid:S2115")
    public Connection getConnection()
    {
        try
        {
            return DriverManager.getConnection(DB_JDBC_PATH, USER_H2_DEFAULT, PASSWORD_H2_DEFAULT);
        }
        catch (SQLException e)
        {
            throw new IllegalStateException("Проблема с соединением к БД", e);
        }
    }

    /**
     * Получить множество имён таблиц в БД
     * @param connection соединение к БД
     * @return имена таблиц
     */
    public Set<String> getTables(Connection connection)
    {
        Set<String> tables = new HashSet<>();
        try (Statement statement = connection.createStatement())
        {
            ResultSet result = statement.executeQuery("SHOW TABLES");
            while (result.next())
            {
                tables.add(result.getString(1));
            }
            return tables;
        }
        catch (SQLException e)
        {
            throw new IllegalStateException("Не удалось выгрузить список таблиц", e);
        }
    }

    private void deleteDbIfExist()
    {
        Path path = Paths.get(DB_PATH);
        try
        {
            Files.deleteIfExists(path);
        }
        catch (IOException e)
        {
            throw new IllegalStateException("Проблема с удалением БД", e);
        }
    }

}
