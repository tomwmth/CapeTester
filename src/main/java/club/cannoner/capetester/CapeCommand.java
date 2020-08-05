package club.cannoner.capetester;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

public class CapeCommand extends CommandBase {
    private CapeTesterMod mod;

    public CapeCommand(CapeTesterMod mod) {
        this.mod = mod;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "load":
                    try {
                        BufferedImage image = getCapeFromUrl(args[1]);
                        CapeRenderer.capeLocation = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation("skyreach", new DynamicTexture(image));
                        mod.sendMessage("&aCape successfully loaded.", true);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        mod.sendMessage("&cFailed to load the cape. Please double check your input.", true);
                    }
                    break;
                case "unload":
                    CapeRenderer.capeLocation = null;
                    mod.sendMessage("&aCape successfully unloaded.", true);
                    break;
                case "help":
                    mod.sendMessage(getHelpMessage(), false);
                    break;
                default:
                    mod.sendMessage("&cInvalid command.", true);
                    break;
            }
        }
        else {
            mod.sendMessage(getCommandUsage(sender), false);
        }
    }

    private BufferedImage getCapeFromUrl(String imageLink) throws IOException {
        return ImageIO.read(new URL(imageLink));
    }

    @Override
    public String getCommandName() {
        return "cape";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "&7&m----------------&7[&c&l CapeTester &7]&7&m----------------\n" +
                "&c/cape &7- Displays this help message.\n" +
                "&c/cape load [url] &7- Loads the provided URL as a cape.\n" +
                "&c/cape unload &7- Unloads the current cape.\n" +
                "&c/cape help &7- Displays some frequently asked questions.\n" +
                "&8&o            » Made by Cannoner (Tom) «\n" +
                "&7&m-----------------------------------------------";
    }

    public String getHelpMessage() {
        return "&7&m----------------&7[&c&l CapeTester &7]&7&m----------------\n" +
                "&c» Why does my game freeze?\n" +
                "&7Unfortunately loading capes on the fly isn't easy, so\n" +
                "&7your game will freeze for a bit.\n" +
                "&c» Why isn't my URL working?\n" +
                "&7Make sure you have a &c&l&ndirect&r &7link to the image. If the\n" +
                "&7link is right, it should end in &c.png&7.\n" +
                "&7&m-----------------------------------------------";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender obj) {
        return true;
    }

    @Override
    public boolean isUsernameIndex(String[] strings, int i) {
        return false;
    }

    @Override
    public int compareTo(ICommand obj) {
        return 0;
    }
}
