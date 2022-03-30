package com.dev.service;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

@Component
public class CachingService {
	
	@Autowired
    CacheManager cacheManager;
	
	public void evictAllCaches() {
        cacheManager.getCacheNames()
          .parallelStream()
          .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }

}
