package com.rezomediaproductions.metallurgicdiscovery.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.rezomediaproductions.metallurgicdiscovery.MetallurgicDiscovery;
import com.rezomediaproductions.metallurgicdiscovery.blocks.entity.BasicMetallurgyStationBlockEntity;
import com.rezomediaproductions.metallurgicdiscovery.network.MDNetworkMessages;
import com.rezomediaproductions.metallurgicdiscovery.network.packet.ForgeButtonPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class BasicMetallurgyStationScreen extends AbstractContainerScreen<BasicMetallurgyStationMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(MetallurgicDiscovery.MOD_ID,"textures/gui/basic_metallurgy_station_menu.png");

    public BasicMetallurgyStationScreen(BasicMetallurgyStationMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        imageWidth = 236;
        imageHeight = 208;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(@NotNull PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);

        renderForgeButton(pPoseStack, x, y);
        renderForgeFlames(pPoseStack, x, y);
        renderProgressArrowFirst(pPoseStack, x, y);
        renderProgressArrowSecond(pPoseStack, x, y);
    }

    @Override
    protected void renderLabels(@NotNull PoseStack matrixStack, int mouseX, int mouseY) {
        this.font.draw(matrixStack, this.title, 8.0F, 6.0F, 4210752);
        this.font.draw(matrixStack, this.playerInventoryTitle, 38.0F, (float) (this.imageHeight - 96 + 2), 4210752);
    }

    @Override // Test if mouse is clicked in the forge button bounds, if clicked and button is active, start crafting
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (isHovering(121, 63, 25, 13, mouseX, mouseY)) {
            MDNetworkMessages.sendToServer(new ForgeButtonPacket(menu.blockEntity.getBlockPos()));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }

        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    // Only render button if recipe is valid
    private void renderForgeButton(PoseStack pPoseStack, int x, int y) {
        if (menu.hasRecipe()) {
            blit(pPoseStack, x + 121, y + 63, 83, 225, 26, 13);
        }
    }

    // Render forge button flames when recipe is available and crafting is happening
    private void renderForgeFlames(PoseStack pPoseStack, int x, int y) {
        if (menu.hasRecipe() || menu.isCrafting()) {
            blit(pPoseStack, x + 112, y + 62, 74, 225, 9, 13); // Left flames
            blit(pPoseStack, x + 147, y + 62, 109, 225, 9, 13); // Right flames
        }
    }

    // Arrows for 4 alloy metals
    private void renderProgressArrowFirst(PoseStack pPoseStack, int x, int y) {
        if(menu.isCrafting()) {
            blit(pPoseStack, x + 60, y + 26, 0, 225, menu.getScaledProgressFirstWidth(), menu.getScaledProgressFirstHeight()); // Top Left
            blit(pPoseStack, x + 86, y + 26, 15, 225, menu.getScaledProgressFirstWidth(), menu.getScaledProgressFirstHeight()); // Top Right
            blit(pPoseStack, x + 60, y + 75, 30, 225, menu.getScaledProgressFirstWidth(), menu.getScaledProgressFirstHeight()); // Bottom Left
            blit(pPoseStack, x + 86, y + 75, 45, 225, menu.getScaledProgressFirstWidth(), menu.getScaledProgressFirstHeight()); // Bottom Right
        }
    }

    // Arrow for main output
    private void renderProgressArrowSecond(PoseStack pPoseStack, int x, int y) {
        if(menu.isCrafting()) {
            blit(pPoseStack, x + 96, y + 57, 51, 209, menu.getScaledProgressSecond(), 5);
        }
    }

    @Override
    public void render(@NotNull PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}
