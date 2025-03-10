package net.sebitzu.lolflash.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.sebitzu.lolflash.LOLFlash;

public class ModSounds {

    public static final SoundEvent FLASH_USE = registerSoundEvent("flash_use");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(LOLFlash.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        LOLFlash.LOGGER.info("Registering ModSounds for " + LOLFlash.MOD_ID);
    }
}
