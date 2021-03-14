package tech.onehmh.edujava.jackson;

/**
 * Класс без геттеров и общедоступных полей для получения ошибки сериализации
 */
public final class UserInfoNoPublicMembers
{
    @SuppressWarnings("unused")
    private final Long id;

    @SuppressWarnings("unused")
    private final String firstName;

    public UserInfoNoPublicMembers(Long id, String firstName)
    {
        this.id = id;
        this.firstName = firstName;
    }
}
