package com.github.peeftube.spiromodneo.core.screens;

import com.github.peeftube.spiromodneo.SpiroMod;
import com.github.peeftube.spiromodneo.util.RLUtility;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ManualCrusherScreen extends AbstractContainerScreen<ManualCrusherMenu>
{
    private static final ResourceLocation GUI_TEX =
            RLUtility.makeRL(SpiroMod.MOD_ID, "textures/gui/manual_crusher/manual_crusher_gui.png");

    public ManualCrusherScreen(ManualCrusherMenu menu, Inventory inv, Component title)
    { super(menu, inv, title); }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mX, int mY)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, GUI_TEX);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(GUI_TEX, x, y, 0, 0, imageWidth, imageHeight);
    }
}
