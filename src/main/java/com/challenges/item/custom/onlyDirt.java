package com.challenges.item.custom;

import com.challenges.Challenges;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class onlyDirt extends Item {
    public onlyDirt(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {

            if (user.isSneaking()) {
                Challenges.onlyDirt = false;
                user.sendMessage(Text.literal("onlyDirt: not Active"));
            }
            else {
                user.sendMessage(Text.literal("OnlyDirt: Active for player"));
                Challenges.onlyDirt = true;
            }


        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
