package mc.art.gunpowder.listener;

import mc.art.gunpowder.block.SulphurOreBlock;
import mc.art.gunpowder.block.Tnt2Block;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;

public class BlockListener {

    public static final Block TNT_2 = new Tnt2Block(FabricBlockSettings.copy(Blocks.TNT));

    public static final Block SULPHUR_ORE = new SulphurOreBlock(FabricBlockSettings.of(Material.STONE).strength(4.0F).requiresTool());

}
