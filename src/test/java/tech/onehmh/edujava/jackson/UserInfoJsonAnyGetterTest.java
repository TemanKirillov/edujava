package tech.onehmh.edujava.jackson;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Тест изменения сериализации полей типа Map с помощью аннотации {@link JsonAnyGetter}
 */
public class UserInfoJsonAnyGetterTest
{
    private final UserInfoJsonAnyGetter userInfo =
            new UserInfoJsonAnyGetter(1L, "Иван", "Кириллов");

    /**
     * Обычно поле типа Map сериализуется с корневым именем
     *
     * @throws JsonProcessingException незапланированная ошибка
     * @see UserInfoJsonAnyGetter#getMainProperties()
     */
    @Test
    public void getMainProperties() throws JsonProcessingException
    {
        String result = new ObjectMapper().writeValueAsString(userInfo);
        System.out.println(result);
        assertThat(result, containsString("\"mainProperties\""));
    }

    /**
     * Аннотация {@link JsonAnyGetter} убирает корневое имя, добавляя содержимое Map прямо в объект
     *
     * @throws JsonProcessingException незапланированная ошибка
     * @see UserInfoJsonAnyGetter#getOtherProperties()
     */
    @Test
    public void getOtherProperties() throws JsonProcessingException
    {
        String result = new ObjectMapper().writeValueAsString(userInfo);
        System.out.println(result);
        assertThat(result, containsString("\"lastName\""));
        assertThat(result, not(containsString("\"otherProperties\"")));
    }
}