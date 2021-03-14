package tech.onehmh.edujava.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Перекрытие любых аннотаций jackson и выдача единственного значения при сериализации
 * + добавление корневого имени объекта
 */
@JsonRootName(value = "user_info")
public class UserInfoJsonValue
{
    @SuppressWarnings("unused")
    @JsonProperty
    private final Long id;

    @SuppressWarnings("unused")
    @JsonProperty
    private final String firstName;

    /**
     * Конструктор
     *
     * @param id идентификатор
     * @param firstName фамилия
     */
    public UserInfoJsonValue(Long id, String firstName)
    {
        this.id = id;
        this.firstName = firstName;
    }

    /**
     * Метод для получения объекта в сериализованном виде
     * @return строка с json
     */
    @JsonValue
    public String toJson()
    {
        return "simple string";
    }
}
