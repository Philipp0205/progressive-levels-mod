package net.fabricmc.example.gui;


import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.data.Texture;
import net.fabricmc.example.ExampleMod;
import net.fabricmc.example.gui.skills.SkillPanelBuilder;
import net.fabricmc.example.skills.Skill;
import net.fabricmc.example.skills.SkillData;
import net.fabricmc.example.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.stat.StatHandler;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ExampleGuiDescription extends LightweightGuiDescription {








    public ExampleGuiDescription(MinecraftClient client, PlayerEntity player) {

        assert client.player != null;
        System.out.println("playerid gui" + client.player.getUuidAsString());

        WPlainPanel root = new WPlainPanel();
        root.setSize(200, 100);
        root.setBackgroundPainter(BackgroundPainter.VANILLA);
        root.setInsets(Insets.ROOT_PANEL);

        setRootPanel(root);

        WLabel label = new WLabel(Text.literal(player.getName().getString() + "'s Skills"), 0x181919);
        WLabel description = new WLabel(Text.literal("Level up by doing activities"), 0x181919);

        int miningTotal = SkillData.totalCount(((IEntityDataSaver) client.player), "mining");
        int agilityTotal = SkillData.totalCount(((IEntityDataSaver) client.player), "agility");

        String miningTotalString = miningTotal +  " blocks";
        String agilityTotalString = agilityTotal + " cm";

        double miningLevel = SkillData.getSkillLevel(((IEntityDataSaver) client.player), "mining");
        double agilityLevel = SkillData.getSkillLevel(((IEntityDataSaver) client.player), "agility");

        int miningProgress = SkillData.getProgress(((IEntityDataSaver) client.player), "mining");
        int agilityProgress = SkillData.getProgress(((IEntityDataSaver) client.player), "agility");

        WPlainPanel miningSkill = new SkillPanelBuilder().build("Mining", String.valueOf(miningLevel),
                "minecraft:textures/item/diamond_pickaxe.png", miningProgress, miningTotalString);

        WPlainPanel agilitySkill = new SkillPanelBuilder().build("Agility", String.valueOf(agilityLevel),
                "minecraft:textures/item/diamond_boots.png", agilityProgress, agilityTotalString);

        WPlainPanel attackSkill = new SkillPanelBuilder().build("Attack", String.valueOf(miningLevel),
                "minecraft:textures/item/diamond_sword.png", 5, "nix");

        WPlainPanel defenseSkill = new SkillPanelBuilder().build("Defense", String.valueOf(miningLevel),
                "minecraft:textures/item/diamond_sword.png",5, "nix");




        root.add(label, 75, 0, 1, 1);
        root.add(description, 55, 30, 1, 1);

        root.add(miningSkill, 0, 45, miningSkill.getWidth(), miningSkill.getHeight());
        root.add(agilitySkill, 115, 45, agilitySkill.getWidth(), agilitySkill.getHeight());

        root.add(defenseSkill, 0, 95, defenseSkill.getWidth(), defenseSkill.getHeight());
        root.add(attackSkill, 115, 95, attackSkill.getWidth(), attackSkill.getHeight());

        root.validate(this);
    }
}
