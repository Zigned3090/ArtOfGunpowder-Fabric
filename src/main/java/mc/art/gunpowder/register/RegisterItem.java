package mc.art.gunpowder.register;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static mc.art.gunpowder.ModMain.MODID;
import static mc.art.gunpowder.listener.ItemListener.*;

public class RegisterItem {

    public static void registerItem() {

        Registry.register(Registry.ITEM, new Identifier(MODID, "special_gunpowder"), SPECIAL_GUNPOWDER);
        Registry.register(Registry.ITEM, new Identifier(MODID, "detonator"), DETONATOR);
        Registry.register(Registry.ITEM, new Identifier(MODID, "detonator_2"), LVL_2_DETONATOR);
        Registry.register(Registry.ITEM, new Identifier(MODID, "sulphur"), SULPHUR);
        Registry.register(Registry.ITEM, new Identifier(MODID, "super_creeper_spawn_egg"), SUPER_CREEPER_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(MODID, "gunpowder_stick"), GUNPOWDER_STICK);
        Registry.register(Registry.ITEM, new Identifier(MODID, "explode_arrow"), EXPLODE_ARROW);
        Registry.register(Registry.ITEM, new Identifier(MODID, "special_bow"), SPECIAL_BOW);

    }

}
