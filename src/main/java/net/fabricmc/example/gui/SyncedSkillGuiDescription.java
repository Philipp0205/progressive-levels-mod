package net.fabricmc.example.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;

public class SyncedSkillGuiDescription extends SyncedGuiDescription {

    PlayerEntity player;

    public SyncedSkillGuiDescription(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory,
            ScreenHandlerContext context) {
        super(type, syncId, playerInventory);

        player = playerInventory.player;

        WGridPanel root = new WGridPanel();
        setRootPanel(root);

        WLabel label = new WLabel(Text.literal(player.getName().getString() + "'s Skills"), 0x181919);
        root.add(label, 0, 0, 1, 1);
    }
}
