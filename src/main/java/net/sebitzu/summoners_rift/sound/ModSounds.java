package net.sebitzu.summoners_rift.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.sebitzu.summoners_rift.SummonersRift;

public class ModSounds {

    public static final SoundEvent BARRIER_USE = registerSoundEvent("barrier_use");
    public static final SoundEvent CLEANSE_USE = registerSoundEvent("cleanse_use");
    public static final SoundEvent EXHAUST_USE = registerSoundEvent("exhaust_use");
    public static final SoundEvent FLASH_USE = registerSoundEvent("flash_use");
    public static final SoundEvent GHOST_USE = registerSoundEvent("ghost_use");
    public static final SoundEvent HEAL_USE = registerSoundEvent("heal_use");
    public static final SoundEvent IGNITE_USE = registerSoundEvent("ignite_use");
    public static final SoundEvent SMITE_USE = registerSoundEvent("smite_use");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(SummonersRift.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        SummonersRift.LOGGER.info("Registering ModSounds for " + SummonersRift.MOD_ID);
    }
}
