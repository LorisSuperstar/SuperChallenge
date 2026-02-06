package com.challenges.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;

public class oneChunk extends Item {
    public oneChunk(Settings settings) {
        super(settings);

    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        WorldBorder border = world.getWorldBorder();

        border.setCenter(user.getX(), user.getZ());
        border.setSize(16);

        if (user.isSneaking()) {
            border.setSize(59999968.0);
        }

        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
