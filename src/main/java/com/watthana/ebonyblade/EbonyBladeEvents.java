package com.watthana.ebonyblade;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class EbonyBladeEvents {
    public static void initialize() {
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(EbonyBladeEvents::onKilledOtherEntity);
        ServerPlayerEvents.AFTER_RESPAWN.register(EbonyBladeEvents::onAfterRespawn);
    }

    private static void onKilledOtherEntity(ServerLevel world, Entity entity, LivingEntity killedEntity, DamageSource damageSource) {
        if (!(entity instanceof Player player)) {
            return;
        }

        if (killedEntity instanceof Player) {
            return;
        }

        ItemStack mainHand = player.getMainHandItem();
        if (!mainHand.is(ModItems.EBONY_BLADE)) {
            return;
        }

        int currentKills = mainHand.getOrDefault(ModComponents.KILL_COUNT, 0);
        mainHand.set(ModComponents.KILL_COUNT, currentKills + 1);
        EbonyBladeItem.syncEbonyAttributes(mainHand);
    }

    private static void onAfterRespawn(ServerPlayer oldPlayer, ServerPlayer newPlayer, boolean alive) {
        resetEbonyBlades(newPlayer);
    }

    private static void resetEbonyBlades(ServerPlayer player) {
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);

            if (stack.is(ModItems.EBONY_BLADE)) {
                stack.set(ModComponents.KILL_COUNT, 0);
                EbonyBladeItem.syncEbonyAttributes(stack);
            }
        }
    }
}