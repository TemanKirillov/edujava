package tech.onehmh.edujava.jackson;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Основной класс для тестирования аннотаций Jackson
 */
@JsonPropertyOrder(value = {"id_user_info"})
public class UserInfo
{
    private final Long id;
    private final String firstName;
    @SuppressWarnings("unused")
    @JsonProperty
    private final String lastName;
    @SuppressWarnings("unused")
    @JsonSerialize(using = PhoneSerializer.class)
    private final String phone;

    /**
     * Конструктор
     *
     * @param id идентификатор
     * @param firstName имя
     * @param lastName фамилия
     * @param phone номер телефона
     */
    public UserInfo(Long id, String firstName, String lastName, String phone)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    @JsonGetter("id_user_info")
    public Long getId()
    {
        return id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Метод возвращает константное значение, без обращения к полю
     *
     * @return строка с версией
     */
    public String getVersion()
    {
        return "1.0";
    }
}
