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

        int ticks_to_burn = 200; // 200 ticks (10 sec)

        if (!entity.getWorld().isClient)
        {
            // Set any entity on fire
            entity.setFireTicks(ticks_to_burn);

            // If it's a player also set the hunger bar
            if (entity instanceof PlayerEntity player_character) {
                int food_level = player_character.getHungerManager().getFoodLevel();
                float saturation_level = player_character.getHungerManager().getSaturationLevel();
                player_character.getHungerManager().setFoodLevel(10);
                player_character.getHungerManager().setSaturationLevel(0);

                CompletableFuture<Void> function = CompletableFuture.runAsync(() -> {
                   try {
                       Thread.sleep(ticks_to_burn*50);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                });
                function.thenRun(() -> {
                   player_character.getHungerManager().setFoodLevel(food_level);
                   player_character.getHungerManager().setSaturationLevel(saturation_level);
                });
            }

            // Add cooldown and consumes a piece on use
            player.getItemCooldownManager().set(stack, (int) (ticks_to_burn*1.5f));
            stack.decrement(1);
        }
        return ActionResult.SUCCESS;
    }
}
