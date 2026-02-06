package com.challenges.item;

import com.challenges.Challenges;
import com.challenges.item.custom.DamageFly;
import com.challenges.item.custom.oneChunk;
import com.challenges.item.custom.onlyDirt;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item onechunk = registerItem("onechunk", new oneChunk(new Item.Settings()));
    public static final Item damagefly = registerItem("damagefly", new DamageFly(new Item.Settings()));
    public static final Item onlyDirt = registerItem("onlydirt", new onlyDirt(new Item.Settings()));


        private static Item registerItem(String name, Item item) {
            return Registry.register(Registries.ITEM, Identifier.of(Challenges.MOD_ID, name), item);
        }


    public static void registerModItems() {
        Challenges.LOGGER.info("Registering Mod Items for " + Challenges.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> {
            entries.add(onechunk);
            entries.add(damagefly);
            entries.add(onlyDirt);
        });
    }
}
