package tech.onehmh.edujava.jackson;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Тесты для класса {@link UserInfoJsonValue}
 */
public class UserInfoJsonValueTest
{

    /**
     * Пользовательский json, который возвращает метод, который помечен аннотацией {@link JsonValue}
     * Аннотация делает прочие аннотации недействительными.
     *
     * @throws JsonProcessingException незапланированная ошибка
     * @see UserInfoJsonValue#toJson()
     */
    @Test
    public void toJson() throws JsonProcessingException
    {
        UserInfoJsonValue userInfo = new UserInfoJsonValue(1L, "Иван");
        String result = new ObjectMapper().writeValueAsString(userInfo);
        System.out.println(result);
        assertEquals("\"simple string\"", result);
        assertThat(result, not(containsString("user_info")));
    }

    /**
     * Добавление root-value для объекта. Пользовательское имя задаётся аннотацией {@link JsonRootName}
     *
     * @throws JsonProcessingException незапланированная ошибка
     * @see UserInfoJsonValue
     */
    @Test
    public void annoJsonRootName() throws JsonProcessingException
    {
        UserInfoJsonValue userInfo = new UserInfoJsonValue(1L, "Иван");
        String result = new ObjectMapper()
            .enable(SerializationFeature.WRAP_ROOT_VALUE)
            .writeValueAsString(userInfo);
        System.out.println(result);
        assertThat(result, containsString("\"user_info\""));
        assertThat(result, containsString("\"simple string\""));
    }
}