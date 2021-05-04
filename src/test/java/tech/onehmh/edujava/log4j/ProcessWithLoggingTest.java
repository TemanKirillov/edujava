package tech.onehmh.edujava.log4j;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Test;

import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Тесты логирования в классе {@link ProcessWithLogging}
 */
public class ProcessWithLoggingTest
{

    /**
     * Тест логирования с настройками из log4j.properties
     *     Смотри вывод в консоль и файлы
     */
    @Test
    public void process()
    {
        ProcessWithLogging process = new ProcessWithLogging();
        process.process();
    }

    /**
     * Выключить логирование
     */
    @Test
    public void loggingOff()
    {
        LogManager.getRootLogger().setLevel(Level.OFF);
        ProcessWithLogging process = new ProcessWithLogging();
        process.process();
    }

    /**
     * Выключить логирование с затиранием конфигурации
     *     и установкой rootLogger-a в OFF
     */
    @Test
    public void loggingOffWithPropertyConfigurator()
    {
        Properties props = new Properties();
        props.setProperty("log4j.rootLogger", "OFF");
        PropertyConfigurator.configure(props);
        ProcessWithLogging process = new ProcessWithLogging();
        process.process();
    }

    /**
     * Конфигурирование логгера с помощью {@link PropertyConfigurator}
     *     вместо файла log4j.properties
     */
    @Test
    public void configureByPropertyConfigurator()
    {
        Properties props = new Properties();
        props.setProperty("log4j.rootLogger", "TRACE, stdout");
        props.setProperty("main_pattern", "%d{yyyy-MM-dd HH:mm:ss} | %-5p | %c{2}:%L | %m%n");
        props.setProperty("log4j.appender.stdout.Threshold", "INFO");
        props.setProperty("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
        props.setProperty("log4j.appender.stdout.Target", "System.out");
        props.setProperty("log4j.appender.stdout.layout", "org.apache.log4j.PatternLayout");
        props.setProperty("log4j.appender.stdout.layout.ConversionPattern", "${main_pattern}");
        PropertyConfigurator.configure(props);
        ProcessWithLogging process = new ProcessWithLogging();
        process.process();
    }

    /**
     * Тест кастомного аппендера {@link ListAppender}
     */
    @Test
    public void testListAppenderListOrigin()
    {
        ProcessWithLogging process = new ProcessWithLogging();
        process.process();
        List<LoggingEvent> events = ListAppender.getListOrigin();
        assertEquals(3, events.size());
        LoggingEvent firstEvent = events.get(0);
        assertEquals("Запущен метод process", firstEvent.getMessage());
        assertEquals(Level.INFO, firstEvent.getLevel());
    }

    /**
     * Тест взаимодействия кастомного аппендера {@link ListAppender}
     *     с кастомным макетов {@link CustomLayout}
     */
    @Test
    public void testListAppenderWithCustomLayout()
    {
        ProcessWithLogging process = new ProcessWithLogging();
        process.process();
        List<String> events = ListAppender.getListPretty();
        assertEquals(3, events.size());
        String firstEvent = events.get(0);
        System.out.println(firstEvent);
    }

    /**
     * передача параметра из конфигурации в аппендер
     */
    @Test
    public void testParam()
    {
        ListAppender appender = (ListAppender)LogManager.getRootLogger().getAppender("list");
        String version = appender.getVersion();
        assertEquals("1.0.0", version);
    }

}