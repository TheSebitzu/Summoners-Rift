package net.sebitzu.lolflash.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sebitzu.lolflash.sound.ModSounds;


public class FlashItem extends Item {
    public FlashItem(Settings settings) {
        super(settings);
    }

    public static void particlesAndSounds(Vec3d initialLocation, Vec3d direction, Vec3d destination, World world) {
        // Particles
        for (int i = 0; i <= 5; i++) {
            Vec3d intermediatePoint = initialLocation.add(direction.multiply(i));
            ((ServerWorld)world).spawnParticles(ParticleTypes.ELECTRIC_SPARK, intermediatePoint.x, intermediatePoint.y + 1, intermediatePoint.z, 10, 0.1, 0.1, 0.1, 0.02);
        }
        // Sound
        world.playSound(null, initialLocation.x, initialLocation.y, initialLocation.z, ModSounds.FLASH_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
        world.playSound(null, destination.x, destination.y, destination.z, ModSounds.FLASH_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (!world.isClient) {
            if (!player.getItemCooldownManager().isCoolingDown(itemStack)) {

                // Direction looking
                Vec3d direction = player.getRotationVec(1);
                direction = new Vec3d(direction.x, 0, direction.z);

                // Player pos
                int tpDistance = 5;

                // For each dist from 5 to 0, in steps of .25
                for (float i = tpDistance; i >= 0; i-= 0.25f) {
                    Vec3d playerPos = player.getPos();

                    // Find where legs, head and aboveHead would be, don't change the y
                    Vec3d legs = playerPos.add(direction.multiply(i));
                    legs = new Vec3d(legs.x, playerPos.y, legs.z);
                    Vec3d head = legs.add(0, 1, 0);

                    // Check same y
                    if (world.isSpaceEmpty(player, new Box(legs.x - 0.3, legs.y, legs.z - 0.3, legs.x + 0.3, legs.y + 2, legs.z + 0.3))) {
                        player.requestTeleport(legs.x, legs.y, legs.z);
                        particlesAndSounds(playerPos, direction, legs, world);
                        break;
                    }
                    // Check y+1
                    else if (world.isSpaceEmpty(player, new Box(head.x - 0.3, head.y, head.z - 0.3, head.x + 0.3, head.y + 2, head.z + 0.3))) {
                        player.requestTeleport(head.x, head.y, head.z);
                        particlesAndSounds(playerPos, direction, head, world);
                        break;
                    }
                }
                itemStack.decrement(1);
                player.getItemCooldownManager().set(itemStack, 20); // 20 ticks (1 sec)
            }

        }

        return ActionResult.SUCCESS;
    }

}
