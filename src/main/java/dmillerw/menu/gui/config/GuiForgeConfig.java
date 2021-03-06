package dmillerw.menu.gui.config;

import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import dmillerw.menu.MineMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmillerw
 */
public class GuiForgeConfig extends GuiConfig {

    private static List<IConfigElement> getElements() {
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        list.addAll((new ConfigElement(MineMenu.configuration.getCategory("visual"))).getChildElements());
        list.addAll((new ConfigElement(MineMenu.configuration.getCategory("general"))).getChildElements());
        return list;
    }

    public GuiForgeConfig(GuiScreen parent) {
        super(
                parent,
                getElements(),
                "MineMenu",
                false,
                false,
                GuiConfig.getAbridgedConfigPath(MineMenu.configuration.toString())
        );
    }
}
