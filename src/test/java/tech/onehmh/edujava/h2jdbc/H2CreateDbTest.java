package tech.onehmh.edujava.h2jdbc;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Тест создания БД H2
 */
public class H2CreateDbTest
{
    private static final String JDBC_H2_PREFIX = "jdbc:h2:";
    private static final String DB_PATH = "h2jdbc/createDb.mv.db";
    private static final String DB_JDBC_PATH = "jdbc:h2:./h2jdbc/createDb";
    private static final String USER_H2_DEFAULT = "sa";
    private static final String PASSWORD_H2_DEFAULT = "";

    /**
     * Создание БД при первом созданием соединения
     *
     * @throws SQLException ошибка подключения к БД
     */
    @Test
    public void createDb() throws SQLException
    {
        deleteDbIfExist();
        Connection conn = DriverManager.getConnection(DB_JDBC_PATH, USER_H2_DEFAULT, PASSWORD_H2_DEFAULT);
        conn.close();
        assertTrue(Files.exists(Paths.get(DB_PATH)));
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
            throw new RuntimeException("Проблема с удалением БД", e);
        }
        assertFalse(Files.exists(Paths.get(DB_PATH)));
    }
}
