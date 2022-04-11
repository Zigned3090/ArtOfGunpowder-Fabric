package mc.art.gunpowder.listener;

import mc.art.gunpowder.entity.ModEntityType;
import mc.art.gunpowder.item.*;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;

import static mc.art.gunpowder.ModMain.MODID;

public class ItemListener {

    public static final ItemGroup ART_OF_GUNPOWDER = FabricItemGroupBuilder.build(new Identifier(MODID, "mod_item_group"),
            () -> new ItemStack(ItemListener.SPECIAL_GUNPOWDER));

    public static final Item SPECIAL_GUNPOWDER = new SpecialGunpowderItem(new FabricItemSettings().group(ItemListener.ART_OF_GUNPOWDER));

    public static final Item DETONATOR = new DetonatorItem(new FabricItemSettings().group(ItemListener.ART_OF_GUNPOWDER));
    public static final Item LVL_2_DETONATOR = new Lvl2DetonatorItem(new FabricItemSettings().group(ItemListener.ART_OF_GUNPOWDER));
    public static final Item SULPHUR = new Item(new FabricItemSettings().group(ItemListener.ART_OF_GUNPOWDER));

    public static final Item SUPER_CREEPER_SPAWN_EGG = new SpawnEggItem(ModEntityType.SUPER_CREEPER_ENTITY, 8913436, 13829169, new FabricItemSettings().group(ItemListener.ART_OF_GUNPOWDER));

    public static final Item GUNPOWDER_STICK = new GunpowderStickItem(new FabricItemSettings().maxCount(1));

    public static final Item EXPLODE_ARROW = new ExplodeArrowItem(new FabricItemSettings().maxCount(16).group(ItemListener.ART_OF_GUNPOWDER));
    public static final Item SPECIAL_BOW = new SpecialBowItem(new FabricItemSettings().maxDamage(64).group(ItemListener.ART_OF_GUNPOWDER));

}
