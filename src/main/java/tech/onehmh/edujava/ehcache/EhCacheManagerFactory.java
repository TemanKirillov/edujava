package tech.onehmh.edujava.ehcache;

import org.ehcache.CacheManager;
import org.ehcache.Status;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

import java.net.URL;

/**
 * Облегчает доступ к {@link CacheManager}, поддерживает его актуальность
 */
public enum EhCacheManagerFactory
{
    INSTANCE;

    private CacheManager cacheManager;

    /**
     * @return менеджер кэша
     */
    public CacheManager getCacheManager()
    {
        if (cacheManager == null || cacheManager.getStatus() == Status.UNINITIALIZED)
        {
            reload();
        }
        return cacheManager;
    }

    /**
     * Загрузить менеджера кэша в начальном состоянии
     */
    public synchronized void reload()
    {
        if (cacheManager != null && cacheManager.getStatus() != Status.UNINITIALIZED)
        {
            cacheManager.close();
        }
        cacheManager = getNewCacheManager(getDefaultConfiguration());
    }

    private CacheManager getNewCacheManager(Configuration config)
    {
        CacheManager manager = CacheManagerBuilder.newCacheManager(config);
        manager.init();
        return manager;
    }

    private Configuration getDefaultConfiguration()
    {
        URL myUrl = EhCacheManagerFactory.class.getResource("/ehcache-config.xml");
        return new XmlConfiguration(myUrl);
    }
}
