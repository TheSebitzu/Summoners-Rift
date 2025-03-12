package net.sebitzu.summoners_rift.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.sebitzu.summoners_rift.SummonersRift;
import net.sebitzu.summoners_rift.item.custom.*;

import java.util.function.Function;

public class ModItems {

    public static final Item FLASH = register("flash", new Item.Settings(), FlashItem::new);
    public static final Item IGNITE = register("ignite", new Item.Settings(), IgniteItem::new);
    public static final Item EXHAUST = register("exhaust", new Item.Settings(), ExhaustItem::new);
    public static final Item GHOST = register("ghost", new Item.Settings(), GhostItem::new);
    public static final Item CLEANSE = register("cleanse", new Item.Settings(), CleanseItem::new);
    public static final Item HEAL = register("heal", new Item.Settings(), HealItem::new);
    public static final Item BARRIER = register("barrier", new Item.Settings(), BarrierItem::new);
    public static final Item SMITE = register("smite", new Item.Settings(), SmiteItem::new);
    public static final Item LOL_LOGO = register("lol_logo", new Item.Settings(), Item::new);


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
    }
}
