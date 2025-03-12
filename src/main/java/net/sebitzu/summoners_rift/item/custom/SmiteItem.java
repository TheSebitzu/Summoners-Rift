package net.sebitzu.summoners_rift.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.sebitzu.summoners_rift.sound.ModSounds;

public class SmiteItem extends Item {
    public SmiteItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        int ticks_to_exhaust = 200; // 200 ticks (10 sec)

        if (!entity.getWorld().isClient && !player.getItemCooldownManager().isCoolingDown(stack)) {

            if (entity instanceof PlayerEntity || entity instanceof HostileEntity) {
                entity.damage((ServerWorld) entity.getWorld(), entity.getWorld().getDamageSources().generic(), 5);
                player.getWorld().playSound(null, player.getPos().x, player.getPos().y, player.getPos().z, ModSounds.SMITE_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);

                // Add cooldown and consumes a piece on use
                player.getItemCooldownManager().set(stack, (int) (ticks_to_exhaust *1.5f));
                stack.decrement(1);
                return ActionResult.SUCCESS;
            }
            else {
                return ActionResult.PASS;
            }

        }
        return ActionResult.PASS;

    }
}
