package net.sebitzu.summoners_rift.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.sebitzu.summoners_rift.SummonersRift;
import net.sebitzu.summoners_rift.item.custom.FlashItem;
import net.sebitzu.summoners_rift.item.custom.IgniteItem;

import java.util.function.Function;

public class ModItems {

    public static final Item FLASH = register("flash", new Item.Settings(), FlashItem::new);
    public static final Item IGNITE = register("ignite", new Item.Settings(), IgniteItem::new);


    private static RegistryKey<Item> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(SummonersRift.MOD_ID, id));
    }

    public static Item register(String id, Item.Settings settings, Function<Item.Settings, Item> factory) {
        RegistryKey<Item> registryKey = keyOf(id);
        settings.registryKey(registryKey);
        Item item = factory.apply(settings);
        return Registry.register(Registries.ITEM, registryKey, item);
    }

    public static void registerModItem() {
        SummonersRift.LOGGER.info("Registering ModItems for " + SummonersRift.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> {
            entries.add(FLASH);
            entries.add(IGNITE);

        });
    }
}
