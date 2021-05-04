package tech.onehmh.edujava.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

/**
 * Класс с бизнес-логикой, которая должна логироваться
 */
public class ProcessWithLogging
{
    private static final Logger LOGGER = Logger.getLogger(ProcessWithLogging.class);

    /**
     * Выполнить действие
     */
    public void process()
    {
        MDC.put("user", "Teman");

        try
        {
            LOGGER.info("Запущен метод process");
            throw new RuntimeException("Проблема получения данных");
        }
        catch (RuntimeException e)
        {
            LOGGER.error("Ошибка в методе process", e);
        }
        finally
        {
            LOGGER.trace("Метод process завершен");
        }
    }
}
