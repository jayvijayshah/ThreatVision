package com.nvidia.hackathon.api.service.config.impl;

import com.nvidia.hackathon.api.service.config.ConfigService;
import com.nvidia.hackathon.config.ReloadableCache;

public class ConfigServiceImpl implements ConfigService {

	public static final ConfigServiceImpl instance = new ConfigServiceImpl();

	@Override
	public void reload(String cacheName) throws Exception {
		if (cacheName.equalsIgnoreCase("reloadablecache")) {
			ReloadableCache.instance.reload();
		}
	}
}
