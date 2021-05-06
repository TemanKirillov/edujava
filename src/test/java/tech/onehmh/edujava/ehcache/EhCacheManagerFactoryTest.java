package tech.onehmh.edujava.ehcache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Тесторование кэширования с помощью {@link org.ehcache}
 */
public class EhCacheManagerFactoryTest
{
    /**
     * Ключа нет в кэше
     */
    @Test
    public void noValueInCache()
    {
        Cache<String, String> cache = EhCacheHelper.INSTANCE.getUserCache();
        String user = cache.get("1");
        assertNull(user);
    }

    /**
     * Положить значение в кэш, а потом извлечь его оттуда
     */
    @Test
    public void putAndGet()
    {
        Cache<String, String> cache = EhCacheHelper.INSTANCE.getUserCache();
        cache.put("1", "Teman");
        assertEquals("Teman", cache.get("1"));
    }

    /**
     * Откидывание значения из кэша из-за ограничения размера
     * Какое значение будет удалено выбирается случайно, а не по порядку появления
     */
    @Test
    public void clearCache()
    {
        Cache<String, String> cache = EhCacheHelper.INSTANCE.getUserCache();
        cache.put("1", "Teman");
        cache.put("2", "Liza");
        assertTrue(cache.get("1") == null || cache.get("2") == null);
    }

    /**
     * Удалить значение из кэша
     */
    @Test
    public void removeValue()
    {
        Cache<String, String> cache = EhCacheHelper.INSTANCE.getUserCache();
        cache.put("1", "Teman");
        cache.remove("1");
        assertNull(cache.get("1"));
    }

    /**
     * Закрытие кэша делает его непригодным для использования
     */
    @Test(expected = IllegalStateException.class)
    public void removeCache()
    {
        CacheManager cacheManager = EhCacheManagerFactory.INSTANCE.getCacheManager();
        Cache<String, String> cache = cacheManager.getCache("user", String.class, String.class);
        cache.put("1", "Teman");
        cacheManager.removeCache("user");
        cache.get("1");
    }

    /**
     * Занесение значения в персистентный кэш и извлечение его оттуда
     */
    @Test
    public void persist()
    {
        CacheManager cacheManager = EhCacheManagerFactory.INSTANCE.getCacheManager();
        Cache<String, String> cache = cacheManager.getCache("userPersist", String.class, String.class);
        cache.put("1", "Teman");
        cacheManager.close();

        cacheManager = EhCacheManagerFactory.INSTANCE.getCacheManager();
        cache = cacheManager.getCache("userPersist", String.class, String.class);
        assertEquals("Teman", cache.get("1"));
    }
}