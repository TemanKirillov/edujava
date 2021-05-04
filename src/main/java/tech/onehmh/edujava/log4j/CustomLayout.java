package tech.onehmh.edujava.log4j;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Макет для пользовательского представления сообщений лога
 */
public class CustomLayout extends Layout
{
    @Override
    public String format(LoggingEvent loggingEvent)
    {
        String result = "";
        result += "==========\n";
        result += "Уровень:   " + loggingEvent.getLevel().toString() + "\n";
        result += "Сообщение: " + loggingEvent.getMessage() + "\n";

        return result;
    }

    @Override
    public boolean ignoresThrowable()
    {
        return false;
    }

    @Override
    public void activateOptions()
    {
        // не требуется
    }
}
