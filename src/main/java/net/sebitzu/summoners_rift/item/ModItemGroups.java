package net.sebitzu.summoners_rift.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.sebitzu.summoners_rift.SummonersRift;

public class ModItemGroups {

    public static final ItemGroup SUMMONERS_RIFT_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(SummonersRift.MOD_ID, "summoners_rift_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.LOL_LOGO))
                    .displayName(Text.translatable("itemgroup.summoners_rift.summoners_rift_items"))
                    .entries((displayContext, entries) -> {

                        entries.add(ModItems.FLASH);
                        entries.add(ModItems.IGNITE);
                        entries.add(ModItems.EXHAUST);
                        entries.add(ModItems.GHOST);
                        entries.add(ModItems.CLEANSE);
                        entries.add(ModItems.HEAL);

                    }).build());

    public static void registerItemGroups() {
        SummonersRift.LOGGER.info("Registering Item Groups for " + SummonersRift.MOD_ID);
    }
}
