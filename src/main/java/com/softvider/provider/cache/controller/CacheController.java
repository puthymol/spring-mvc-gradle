package com.softvider.provider.cache.controller;

import com.softvider.provider.cache.model.Book;
import com.softvider.provider.cache.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/cache")
public class CacheController {
    private static final Logger log = LoggerFactory.getLogger(CacheController.class);

    @Inject
    CacheService cacheService;

    @Cacheable(cacheNames = "softvider_cache", keyGenerator = "getKeyGenerator")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Map<String, Object> Home() throws InterruptedException {
        return cacheService.GetData(null);
    }

    @Cacheable(cacheNames = "softvider_cache", keyGenerator = "getWithParamKeyGenerator")
    @RequestMapping(value = "/param/{param}", method = RequestMethod.GET)
    public Map<String, Object> Param(@PathVariable(value="param") String param) {
        return cacheService.GetData(param);
    }

    @Cacheable(cacheNames = "softvider_cache", keyGenerator = "postKeyGenerator")
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public Map<String, Object> Post(@RequestBody Book book) {
        return cacheService.PostData(book);
    }

    @CacheEvict(cacheNames = "softvider_cache", allEntries = true) // clear cache
    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public Map<String, Object> ClearCache() {
        Map<String, Object> jsonString = new HashMap<>();
        jsonString.put("cache", "cleared");
        log.info("Response => {}", jsonString);
        return jsonString;
    }
}
