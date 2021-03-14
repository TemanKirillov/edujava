package tech.onehmh.edujava.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

/**
 * Тест класса {@link UserInfoNoPublicMembers}
 */
public class UserInfoNoPublicMembersTest
{
    /**
     * @throws JsonProcessingException Генерируется исключение из-за отстутствия данных для сериализации
     */
    @Test(expected = JsonProcessingException.class)
    public void test() throws JsonProcessingException
    {
        UserInfoNoPublicMembers userInfo = new UserInfoNoPublicMembers(1L, "Иван");
        String result = new ObjectMapper().writeValueAsString(userInfo);
        System.out.println(result);
    }

}