package com.challenges.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;

public class oneChunk extends Item {
    public oneChunk(Settings settings) {
        super(settings);

    }

    boolean alreadyUsed = false;
    @Override


    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        WorldBorder border = world.getWorldBorder();


        if (user.isSneaking()) {
            border.setSize(59999968.0);

        }
        else if (!world.isClient() && world instanceof ServerWorld serverWorld) {


            MinecraftServer server = serverWorld.getServer();
            CommandManager commandManager = server.getCommandManager();


            ServerCommandSource source = user.getCommandSource()
                    .withLevel(4)
                    .withSilent();

            String structureId = "challenges:end_portal";
            String command = "";

            command = String.format("place template %s ^ ^ ^5", structureId);

            commandManager.executeWithPrefix(source, command);


            border.setCenter(user.getX(), user.getZ());
            border.setSize(16);
        }

        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
