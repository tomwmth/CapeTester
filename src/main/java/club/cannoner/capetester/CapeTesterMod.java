package club.cannoner.capetester;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
        modid = "capetester",
        name = "CapeTester",
        version = "1.0",
        acceptedMinecraftVersions = "[1.8.9]",
        clientSideOnly = true,
        useMetadata = true
)
public class CapeTesterMod {
    @EventHandler
    public void init(FMLInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new CapeEvent());
        ClientCommandHandler.instance.registerCommand(new CapeCommand(this));
    }

    public void sendMessage(String text, boolean attachPrefix){
        String message = (attachPrefix ? "&7[&cCapeTester&7]&r " : "") + text;
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatColour.translateAlternateColorCodes('&', message)));
    }
}
