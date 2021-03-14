package tech.onehmh.edujava.jackson;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Основные тесты сериализации с помощью аннотаций Jackson
 */
public class UserInfoTest
{
    private final UserInfo userInfo = new UserInfo(1L, "Иван", "Кириллов", "79211234455");

    /**
     * Сериализация значения по геттеру с именем по умолчанию
     *
     * @throws JsonProcessingException незапланированная ошибка
     * @see UserInfo#getFirstName()
     */
    @Test
    public void fieldNameByDefault() throws JsonProcessingException
    {
        String result = new ObjectMapper().writeValueAsString(userInfo);
        System.out.println(result);
        assertThat(result, CoreMatchers.containsString("\"firstName\""));
    }

    /**
     * Сериализация значения по геттеру, который возвращает статическое значение (не поле)
     *
     * @throws JsonProcessingException незапланированная ошибка
     * @see UserInfo#getVersion()
     */
    @Test
    public void getterWithoutField() throws JsonProcessingException
    {
        String result = new ObjectMapper().writeValueAsString(userInfo);
        System.out.println(result);
        assertThat(result, CoreMatchers.containsString("\"version\""));
    }

    /**
     * Переопеределение имени поля с помощью аннотации {@link JsonGetter}
     *
     * @throws JsonProcessingException незапланированная ошибка
     * @see UserInfo#getId()
     */
    @Test
    public void annoJsonGetter() throws JsonProcessingException
    {
        String result = new ObjectMapper().writeValueAsString(userInfo);
        System.out.println(result);
        assertThat(result, CoreMatchers.containsString("\"id_user_info\""));
    }

    /**
     * Переопеределение имени поля с помощью аннотации {@link JsonProperty}
     * Позволяет обходиться без геттеров
     *
     * @throws JsonProcessingException незапланированная ошибка
     * @see UserInfo#lastName
     */
    @Test
    public void annoJsonProperty() throws JsonProcessingException
    {
        String result = new ObjectMapper().writeValueAsString(userInfo);
        System.out.println(result);
        assertThat(result, CoreMatchers.containsString("\"lastName\""));
    }

    /**
     * Задать порядок полей при сериализации с помощью аннотации {@link JsonPropertyOrder}
     * Здесь на первую позицию перемещено поле id_user_info
     *
     * @throws JsonProcessingException незапланированная ошибка
     * @see UserInfo
     */
    @Test
    public void annoJsonPropertyOrder() throws JsonProcessingException
    {
        String result = new ObjectMapper().writeValueAsString(userInfo);
        System.out.println(result);
        assertThat(result, CoreMatchers.startsWith("{\"id_user_info\""));
    }

    /**
     * Задать кастомный способ сериализации поля с помощью аннотации {@link JsonSerialize}
     *    и сериализатора {@link PhoneSerializer}
     *
     * @throws JsonProcessingException незапланированная ошибка
     * @see UserInfo#phone
     */
    @Test
    public void annoJsonSerialize() throws JsonProcessingException
    {
        String result = new ObjectMapper().writeValueAsString(userInfo);
        System.out.println(result);
        assertThat(result, CoreMatchers.containsString("\"+79211234455\""));
    }
}