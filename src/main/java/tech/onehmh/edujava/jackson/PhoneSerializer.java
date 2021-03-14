package tech.onehmh.edujava.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Кастомный сериализатор для номера телефона.
 * Нужен, когда сериализация по умолчанию не подходит.
 */
public class PhoneSerializer extends JsonSerializer<String>
{
    @Override
    public void serialize(String phone, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException
    {
        jsonGenerator.writeString("+" + phone);
    }
}
