package mc.art.gunpowder.register;

import mc.art.gunpowder.listener.ItemListener;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static mc.art.gunpowder.ModMain.MODID;
import static mc.art.gunpowder.listener.BlockListener.*;

public class RegisterBlock {

    public static void registerBlock() {

        Registry.register(Registry.BLOCK, new Identifier(MODID, "tnt_2"), TNT_2);
        Registry.register(Registry.ITEM, new Identifier(MODID, "tnt_2"), new BlockItem(TNT_2, new FabricItemSettings().group(ItemListener.ART_OF_GUNPOWDER)));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "sulphur_ore"), SULPHUR_ORE);
        Registry.register(Registry.ITEM, new Identifier(MODID, "sulphur_ore"), new BlockItem(SULPHUR_ORE, new FabricItemSettings().group(ItemListener.ART_OF_GUNPOWDER)));

    }

}
