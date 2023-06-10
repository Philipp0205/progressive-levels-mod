package net.fabricmc.example.gui.skills;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.data.Texture;
import net.fabricmc.example.ExampleMod;
import net.fabricmc.example.stat.ModStats;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class SkillPanelBuilder {

    private static final Identifier BAR = new Identifier(ExampleMod.MOD_ID, "textures/gui/energy_bar.png");
    private static final Identifier BG = new Identifier(ExampleMod.MOD_ID, "textures/gui/energy_bar_bg.png");



    WPlainPanel panel;
    int width, height;

    private static final int MAX_PROGRESS = 10;
    private int progress;


    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {

        @Override
        public int get(int index) {

            if (index == 0) {
                return progress;
            } else if (index == 1) {
                return MAX_PROGRESS;
            }

            // Unknown property IDs will fall back to -1
            return -1;
        }

        @Override
        public void set(int index, int value) {
            if (index == 0) {
                progress = value;
            }
        }

        @Override
        public int size() {
            return 2;
        }

    };

    public SkillPanelBuilder() {
        panel = new WPlainPanel();
        width = 110;
        height = 40;

        panel.setSize(width, height);
        panel.setInsets(new Insets(5));
        panel.setBackgroundPainter(BackgroundPainter.SLOT);
    }

    public WPlainPanel build(String skillName, String currentLevel, String itemIdentifier, int progress,
            String totalCount) {
        this.progress = progress;
        WSprite iconSprite = new WSprite(new Identifier(itemIdentifier));
        WLabel name = new WLabel(Text.literal(skillName), 0x181919);

        WLabel level = new WLabel(Text.literal("Level" + currentLevel), 0xFFFFFF);
        WLabel unit = new WLabel(Text.literal("(" + totalCount + ")"), 0xd3d3d3);


        WBar energyBar = new WBar(BG, BAR, 0, 1, WBar.Direction.RIGHT);
        energyBar.setProperties(propertyDelegate);


        panel.add(iconSprite, 0, 0, 18, 18);
        panel.add(name, 30, 0, 1, 1);
        panel.add(level, 30, 10, 1, 1);
        panel.add(unit, 30, 20, 1, 1);
        panel.add(energyBar, 30, 30, 60,5);


        return panel;
    }


}
