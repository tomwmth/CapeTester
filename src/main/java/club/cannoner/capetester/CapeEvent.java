package club.cannoner.capetester;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.UUID;

public class CapeEvent {
    @SubscribeEvent
    public void onPlayerRender(RenderPlayerEvent.Pre e) {
        UUID playerUuid = e.entityPlayer.getUniqueID();
        if (playerUuid == Minecraft.getMinecraft().thePlayer.getUniqueID()) {
            e.renderer.addLayer(new CapeRenderer(e.renderer));
        }
    }

}
