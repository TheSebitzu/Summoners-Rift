package net.sebitzu.summoners_rift.item.custom;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class GhostItem extends Item {
    public GhostItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {

        int ticksToGhost = 200; // 200 ticks (10 sec)

        if (!world.isClient()) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, ticksToGhost, 1));
        }

        ItemStack stack = user.getStackInHand(hand);
        stack.decrement(1);
        user.getItemCooldownManager().set(stack, (int) (ticksToGhost*1.5f));


        return ActionResult.SUCCESS;
    }
}
