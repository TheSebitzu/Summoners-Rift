package net.sebitzu.lolflash.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.sebitzu.lolflash.LOLFlash;
import net.sebitzu.lolflash.item.custom.FlashItem;

import java.util.function.Function;

public class ModItems {

    public static final Item FLASH = register("flash", new Item.Settings(), FlashItem::new);

    private static RegistryKey<Item> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LOLFlash.MOD_ID, id));
    }

    public static Item register(String id, Item.Settings settings, Function<Item.Settings, Item> factory) {
        RegistryKey<Item> registryKey = keyOf(id);
        settings.registryKey(registryKey);
        Item item = factory.apply(settings);
        return Registry.register(Registries.ITEM, registryKey, item);
    }

    public static void registerModItem() {
        LOLFlash.LOGGER.info("Registering ModItems for " + LOLFlash.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> {
            entries.add(FLASH);

        });
    }
}
