package net.sebitzu.summoners_rift.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.sebitzu.summoners_rift.SummonersRift;

public class ModSounds {

    public static final SoundEvent FLASH_USE = registerSoundEvent("flash_use");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(SummonersRift.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        SummonersRift.LOGGER.info("Registering ModSounds for " + SummonersRift.MOD_ID);
    }
}
