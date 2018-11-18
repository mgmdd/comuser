定义 <cache:annotation-driven/>，cache-manager= 指明使用的cachemgr，默认：cacheManager
在spring定义CacheManager实例bean。
spring自动识别使用了Cachable等标注的方法。
通过动态AOP进行缓存处理。

spring cache定义了一种缓存的抽象，可以由其他组件实现cache、cachemgr的具体实现
例如：EHCache、JCache等

org.springframework.cache.Cache
org.springframework.cache.CacheManager

#### @Cacheable
#### @CachePut
#### @CacheEvict 

##@@@@SEE
https://www.cnblogs.com/yudar/p/4809345.html
https://www.cnblogs.com/fashflying/p/6908028.html
