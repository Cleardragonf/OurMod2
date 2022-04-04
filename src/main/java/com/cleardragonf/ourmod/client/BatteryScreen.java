package com.cleardragonf.ourmod.client;

import com.cleardragonf.ourmod.OurMod;
import com.cleardragonf.ourmod.blocks.NaqudahGenerator.Battery.BatteryContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BatteryScreen extends AbstractContainerScreen<BatteryContainer> {

    private final ResourceLocation GUI = new ResourceLocation(OurMod.MODID, "textures/gui/battery.png");

    public BatteryScreen(BatteryContainer container, Inventory inv, Component name){
        super(container, inv, name);
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {
        drawString(matrixStack, Minecraft.getInstance().font, "Energy: " + menu.getEnergy(), 10,10,0xffffff);
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0,GUI);
        int relX = (this.width - this.imageWidth) /2;
        int relY = (this.height - this.imageHeight) /2;
        this.blit(matrixStack, relX, relY, 0,0,this.imageWidth, this.imageHeight);
    }
}