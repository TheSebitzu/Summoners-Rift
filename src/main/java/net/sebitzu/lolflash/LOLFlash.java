package net.sebitzu.lolflash;

import net.fabricmc.api.ModInitializer;

import net.sebitzu.lolflash.item.ModItems;
import net.sebitzu.lolflash.sound.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LOLFlash implements ModInitializer {
	public static final String MOD_ID = "lolflash";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItem();
		ModSounds.registerSounds();
	}
}