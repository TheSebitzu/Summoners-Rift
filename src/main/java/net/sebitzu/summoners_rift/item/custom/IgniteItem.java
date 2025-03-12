package net.sebitzu.summoners_rift.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

import java.util.concurrent.CompletableFuture;

public class IgniteItem extends Item {
    public IgniteItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {

        int ticksToBurn = 200; // 200 ticks (10 sec)

        if (!entity.getWorld().isClient)
        {
            // Set any entity on fire
            entity.setFireTicks(ticksToBurn);

            // If it's a player also set the hunger bar
            if (entity instanceof PlayerEntity playerCharacter) {
                int foodLevel = playerCharacter.getHungerManager().getFoodLevel();
                float saturationLevel = playerCharacter.getHungerManager().getSaturationLevel();
                playerCharacter.getHungerManager().setFoodLevel(10);
                playerCharacter.getHungerManager().setSaturationLevel(0);

                CompletableFuture<Void> function = CompletableFuture.runAsync(() -> {
                   try {
                       Thread.sleep(ticksToBurn *50);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                });
                function.thenRun(() -> {
                   playerCharacter.getHungerManager().setFoodLevel(foodLevel);
                   playerCharacter.getHungerManager().setSaturationLevel(saturationLevel);
                });
            }

            // Add cooldown and consumes a piece on use
            player.getItemCooldownManager().set(stack, (int) (ticksToBurn *1.5f));
            stack.decrement(1);
        }
        return ActionResult.SUCCESS;
    }
}
