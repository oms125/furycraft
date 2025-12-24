package com.furycraft;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FuryCraft implements ModInitializer {
	public static final String MOD_ID = "furycraft";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		FuryCraftBlocks.init();
		FuryCraftItems.init();

		LOGGER.info("Hello Fabric world!");
	}
}