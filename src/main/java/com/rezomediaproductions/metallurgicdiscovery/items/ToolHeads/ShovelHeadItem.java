package com.rezomediaproductions.metallurgicdiscovery.items.ToolHeads;

import net.minecraft.nbt.TextComponentTagVisitor;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.internal.TextComponentMessageFormatHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ShovelHeadItem extends Item {
    public ShovelHeadItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack pStack) {
        return !(pStack.getTag() == null);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        if (pStack.hasTag()) {
            int speedAlloys = pStack.getTag().getInt("metallurgic_discovery.speedAlloys");
            int toughnessAlloys = pStack.getTag().getInt("metallurgic_discovery.toughnessAlloys");
            int sharpnessAlloys = pStack.getTag().getInt("metallurgic_discovery.sharpnessAlloys");
            int enchantAlloys = pStack.getTag().getInt("metallurgic_discovery.enchantAlloys");

            pTooltipComponents.add(Component.literal("Speed " + "x0." + speedAlloys));
            pTooltipComponents.add(Component.literal("Toughness " + "x0." + toughnessAlloys));
            pTooltipComponents.add(Component.literal("Sharpness " + "x0." + sharpnessAlloys));
            pTooltipComponents.add(Component.literal("Enchant " + "x0." + enchantAlloys));
        }
    }
}
