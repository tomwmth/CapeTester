package club.cannoner.capetester;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import java.util.UUID;

public class CapeRenderer implements LayerRenderer<AbstractClientPlayer> {
    private final RenderPlayer playerRenderer;
    public static ResourceLocation capeLocation = null;

    public CapeRenderer(RenderPlayer playerRenderer) {
        this.playerRenderer = playerRenderer;
    }

    @Override
    public void doRenderLayer(AbstractClientPlayer playerEntity, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
        if (playerEntity.hasPlayerInfo() && !playerEntity.isInvisible() && capeLocation != null) {
            UUID playerUuid = playerEntity.getUniqueID();
            if (playerUuid == Minecraft.getMinecraft().thePlayer.getUniqueID()) {
                try {
                    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                    playerRenderer.bindTexture(capeLocation);
                    GlStateManager.pushMatrix();
                    GlStateManager.translate(0.0F, 0.0F, 0.125F);
                    double d0 = playerEntity.prevChasingPosX + (playerEntity.chasingPosX - playerEntity.prevChasingPosX) * (double)partialTicks - (playerEntity.prevPosX + (playerEntity.posX - playerEntity.prevPosX) * (double)partialTicks);
                    double d1 = playerEntity.prevChasingPosY + (playerEntity.chasingPosY - playerEntity.prevChasingPosY) * (double)partialTicks - (playerEntity.prevPosY + (playerEntity.posY - playerEntity.prevPosY) * (double)partialTicks);
                    double d2 = playerEntity.prevChasingPosZ + (playerEntity.chasingPosZ - playerEntity.prevChasingPosZ) * (double)partialTicks - (playerEntity.prevPosZ + (playerEntity.posZ - playerEntity.prevPosZ) * (double)partialTicks);
                    float f0 = playerEntity.prevRenderYawOffset + (playerEntity.renderYawOffset - playerEntity.prevRenderYawOffset) * partialTicks;
                    double d3 = (double) MathHelper.sin(f0 * 3.1415927F / 180.0F);
                    double d4 = (double)(-MathHelper.cos(f0 * 3.1415927F / 180.0F));
                    float f1 = (float)d1 * 10.0F;
                    f1 = MathHelper.clamp_float(f1, -6.0F, 32.0F);
                    float f2 = (float)(d0 * d3 + d2 * d4) * 100.0F;
                    float f3 = (float)(d0 * d4 - d2 * d3) * 100.0F;
                    if (f2 < 0.0F) {
                        f2 = 0.0F;
                    }
                    else if (f2 > 165.0F) {
                        f2 = 165.0F;
                    }
                    float f4 = playerEntity.prevCameraYaw + (playerEntity.cameraYaw - playerEntity.prevCameraYaw) * partialTicks;
                    f1 += MathHelper.sin((playerEntity.prevDistanceWalkedModified + (playerEntity.distanceWalkedModified - playerEntity.prevDistanceWalkedModified) * partialTicks) * 6.0F) * 32.0F * f4;
                    if (playerEntity.isSneaking()) {
                        f1 += 25.01F;
                        GlStateManager.translate(0.0F, 0.142F, -0.0178F);
                    }

                    GlStateManager.rotate(6.0F + f2 / 2.0F + f1, 1.0F, 0.0F, 0.0F);
                    GlStateManager.rotate(f3 / 2.0F, 0.0F, 0.0F, 1.0F);
                    GlStateManager.rotate(-f3 / 2.0F, 0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                    this.playerRenderer.getMainModel().renderCape(0.0625F);
                    GlStateManager.popMatrix();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}
