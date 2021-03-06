package com.softvider.config.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

public class GetKeyGenerator implements KeyGenerator {
	private static final Logger log = LoggerFactory.getLogger(GetKeyGenerator.class);

	@Override
	public Object generate(Object target, Method method, Object... params) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(method.getName());
		log.info(stringBuilder.toString());
		return stringBuilder.toString();
	}
}
