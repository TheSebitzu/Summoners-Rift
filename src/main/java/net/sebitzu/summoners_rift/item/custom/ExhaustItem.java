package net.sebitzu.summoners_rift.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

import java.util.concurrent.CompletableFuture;

public class ExhaustItem extends Item {
    public ExhaustItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        int ticks_to_exhaust = 200; // 200 ticks (10 sec)

        if (!entity.getWorld().isClient)
        {
            // Adds exhaust to target entity
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, ticks_to_exhaust, 1));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, ticks_to_exhaust, 1));

            // Add cooldown and consumes a piece on use
            player.getItemCooldownManager().set(stack, (int) (ticks_to_exhaust *1.5f));
            stack.decrement(1);
        }

        return ActionResult.SUCCESS;
    }
}
