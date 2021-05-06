package tech.onehmh.edujava.ehcache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;

/**
 * Облегчает доступ к кэшам
 */
public enum EhCacheHelper
{
    INSTANCE;

    /**
     * @return кэш пользователей id - имя пользователя
     */
    public Cache<String, String> getUserCache()
    {
        Cache<String, String> cache = getCacheManager().getCache("user", String.class, String.class);
        if (cache == null)
        {
            EhCacheManagerFactory.INSTANCE.reload();
        }
        return getCacheManager().getCache("user", String.class, String.class);
    }

    private CacheManager getCacheManager()
    {
        return EhCacheManagerFactory.INSTANCE.getCacheManager();
    }

}
