package net.sebitzu.summoners_rift;

import net.fabricmc.api.ModInitializer;

import net.sebitzu.summoners_rift.item.ModItems;
import net.sebitzu.summoners_rift.sound.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SummonersRift implements ModInitializer {
	public static final String MOD_ID = "summoners_rift";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItem();
		ModSounds.registerSounds();
	}
}