package tech.onehmh.edujava.log4j;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Аппендер для сохранения лога в списке
 */
public class ListAppender extends AppenderSkeleton
{
    private static final List<LoggingEvent> LIST_ORIGIN = new ArrayList<>();
    private static final List<String> LIST_PRETTY = new ArrayList<>();
    private String version;

    @Override
    protected void append(LoggingEvent loggingEvent)
    {
        LIST_ORIGIN.add(loggingEvent);
        if (getLayout() != null)
        {
            String event = getLayout().format(loggingEvent);
            LIST_PRETTY.add(event);
        }
    }

    /**
     * @return true, может работать без Layout, но выдает ошибку, если его нет; false - работает без Layout, переменную Layout игнорирует
     */
    @Override
    public boolean requiresLayout()
    {
        return true;
    }

    @Override
    public void close()
    {
        // не требуется
    }

    /**
     * @return список оригинальных сообщений лога
     */
    public static List<LoggingEvent> getListOrigin()
    {
        return LIST_ORIGIN;
    }

    /**
     * @return сообщения, которые прогнали через макет
     */
    public static List<String> getListPretty()
    {
        return LIST_PRETTY;
    }

    /**
     * @return версия (нужна для демонстрации передачи параметра из конфигурации)
     */
    public String getVersion()
    {
        return version;
    }

    /**
     * @param version версия (нужна для демонстрации передачи параметра из конфигурации)
     */
    @SuppressWarnings("unused")
    public void setVersion(String version)
    {
        this.version = version;
    }
}
