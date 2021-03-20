package tech.onehmh.edujava.java.util.optional;

/**
 * Класс, у которого есть внутреннее состояние
 */
public class ClassWithInternalState
{
    private String data;

    public ClassWithInternalState(String data)
    {
        this.data = data;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public String getAndSet(String newValue)
    {
        String oldValue = data;
        data = newValue;
        return oldValue;
    }
}
