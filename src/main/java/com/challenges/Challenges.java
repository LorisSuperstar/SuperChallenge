package com.challenges;

import com.challenges.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Challenges implements ModInitializer {
	public static final String MOD_ID = "challenges";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static boolean DamageFly = false;
    public static boolean onlyDirt = false;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
        ModItems.registerModItems();

        ServerLivingEntityEvents.AFTER_DEATH.register((livingEntity, damageSource) -> {
            if (livingEntity instanceof ServerPlayerEntity player) {
                player.changeGameMode(GameMode.SPECTATOR);
            }
        });

        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            if (DamageFly && entity instanceof ServerPlayerEntity player) {
                player.velocityModified = true;
                player.setVelocity(player.getVelocity().x, 50, player.getVelocity().z);
                entity.sendMessage(Text.literal("Flying or not : )"));
            }

            return true;
        });

        ServerTickEvents.END_WORLD_TICK.register(world -> {
            if (Challenges.onlyDirt) {
                for (ServerPlayerEntity player : world.getPlayers()) {
                    if (!world.getBlockState(player.getBlockPos().down()).isOf(Blocks.DIRT) && !world.getBlockState(player.getBlockPos().down()).isAir() && !player.isSpectator()) {
                        player.changeGameMode(GameMode.SPECTATOR);
                    }
                }
            }
        });
    }
}