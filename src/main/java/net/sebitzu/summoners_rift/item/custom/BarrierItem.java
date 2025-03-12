package net.sebitzu.summoners_rift.item.custom;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.sebitzu.summoners_rift.sound.ModSounds;

public class BarrierItem extends Item {
    public BarrierItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {

        if (!world.isClient() && !user.getItemCooldownManager().isCoolingDown(user.getStackInHand(hand))) {
            int tick_to_barrier = 50; // 200 ticks (10 sec)


            user.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, tick_to_barrier));

            world.playSound(null, user.getPos().x, user.getPos().y, user.getPos().z, ModSounds.BARRIER_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);

            ItemStack stack = user.getStackInHand(hand);
            stack.decrement(1);
            user.getItemCooldownManager().set(stack, (int) (tick_to_barrier*1.5f));
        }

        return ActionResult.SUCCESS;
    }
}
