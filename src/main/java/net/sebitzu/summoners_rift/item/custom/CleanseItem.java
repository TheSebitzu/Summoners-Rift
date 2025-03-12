package net.sebitzu.summoners_rift.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.sebitzu.summoners_rift.sound.ModSounds;

public class CleanseItem extends Item {
    public CleanseItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {

        if (!world.isClient()) {
            int tick_cooldown = 200; // 200 ticks (10 sec)

            user.clearStatusEffects();

            world.playSound(null, user.getPos().x, user.getPos().y, user.getPos().z, ModSounds.CLEANSE_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);

            ItemStack stack = user.getStackInHand(hand);
            stack.decrement(1);
            user.getItemCooldownManager().set(stack, (tick_cooldown));
        }

        return ActionResult.SUCCESS;
    }
}
