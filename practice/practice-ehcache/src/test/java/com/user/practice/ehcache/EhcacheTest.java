package com.user.practice.ehcache;

import com.user.service.BeanService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//  https://blog.csdn.net/vbirdbest/article/details/72763048

@ContextConfiguration(locations = "classpath*:spring/*.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EhcacheTest {
    @Autowired
    @Qualifier("ehCacheCacheManager")
    private EhCacheCacheManager ehcacheManager;

    private CacheManager cacheManager;

    @Before
    public void setup() {
//        EhCacheCacheManager ehcacheManager = BeanService.getService("ehCacheCacheManager");
        this.cacheManager = ehcacheManager.getCacheManager();
    }


    @Test
    public void testcache() {
        Cache nameCache = cacheManager.getCache("name_cache");
        NameOper oper = new NameOper();

        Element ele = new Element("nameopen", oper);
        nameCache.put(ele);

        NameOper nameopen = (NameOper) nameCache.get("nameopen").getObjectValue();
        System.out.println(nameopen.getTimestamp());
        Assert.assertEquals(oper.getTimestamp(), nameopen.getTimestamp());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Element element = nameCache.get("nameopen");
        Assert.assertNull(element);
    }


    @Test
    public void testcache1() {
        NameOper oper = BeanService.getService(NameOper.class);
        Assert.assertNotNull(oper);
        System.out.println(oper.getTimestamp());
        System.out.println(oper.getTimestamp());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(oper.getTimestamp());
    }
}
