package com.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.service.CachingService;

@RestController
@CrossOrigin
public class CacheController {
	
	@Autowired
    CachingService cachingService;

    @GetMapping("limparTodosCaches")
    public void clearAllCaches() {
        cachingService.evictAllCaches();
    }

}
