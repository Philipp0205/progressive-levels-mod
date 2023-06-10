package net.fabricmc.example.item.items;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import net.fabricmc.example.gui.ExampleGuiDescription;
import net.fabricmc.example.gui.ExampleScreen;
import net.fabricmc.example.gui.SyncedSkillGuiDescription;
import net.fabricmc.example.skills.PlayerSkillManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

//public class GuiItem extends Item implements NamedScreenHandlerFactory  {
public class GuiItem extends Item {
    MinecraftClient client = MinecraftClient.getInstance();

    public GuiItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            MinecraftClient.getInstance().setScreen(new ExampleScreen(new ExampleGuiDescription(client, user)));
        }
        return super.use(world, user, hand);
    }


    //    @Nullable
    //    @Override
    //    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
    //        return new SyncedSkillGuiDescription(syncId, inv);
    //    }


}