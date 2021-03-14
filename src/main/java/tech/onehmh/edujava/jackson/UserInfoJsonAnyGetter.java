package tech.onehmh.edujava.jackson;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс с сериализацией полей типа Map
 */
public class UserInfoJsonAnyGetter
{
    private final Map<String, String> mainProperties = new HashMap<>();
    private final Map<String, String> otherProperties = new HashMap<>();

    public UserInfoJsonAnyGetter(Long id, String firstName, String lastName)
    {
        mainProperties.put("id", id.toString());
        mainProperties.put("firstName", firstName);

        otherProperties.put("lastName", lastName);
    }

    @SuppressWarnings("unused")
    public Map<String, String> getMainProperties()
    {
        return mainProperties;
    }

    @SuppressWarnings("unused")
    @JsonAnyGetter
    public Map<String, String> getOtherProperties()
    {
        return otherProperties;
    }
}
