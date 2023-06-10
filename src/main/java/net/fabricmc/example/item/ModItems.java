package net.fabricmc.example.item;

import net.fabricmc.example.ExampleMod;
import net.fabricmc.example.item.items.GuiItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item CUSTOM_ITEM = registerItem("custom_item", new GuiItem(new FabricItemSettings()),
            ItemGroups.INGREDIENTS);


    private static Item registerItem(String name, Item item, ItemGroup itemGroup) {
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.add((item)));
        return Registry.register(Registries.ITEM, new Identifier("tutorial", name), item);
    }

    // Register items
    public static void registerModItems() {
        ExampleMod.LOGGER.info("Registering Mod Items for " + ExampleMod.MOD_ID);
    }
}
