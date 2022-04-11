package mc.art.gunpowder.world.ore;

import mc.art.gunpowder.listener.BlockListener;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.Arrays;

import static mc.art.gunpowder.ModMain.MODID;

public class ModOreFeatureConfig {

    private static ConfiguredFeature<?, ?> OVERWORLD_SULPHUR_ORE_CONFIGURE_FEATURE = new ConfiguredFeature(
            Feature.ORE, new OreFeatureConfig(
            OreConfiguredFeatures.STONE_ORE_REPLACEABLES, BlockListener.SULPHUR_ORE.getDefaultState(), 6));

    public static PlacedFeature OVERWORLD_SULPHUR_ORE_PLACED_FEATURE = new PlacedFeature(
            RegistryEntry.of(OVERWORLD_SULPHUR_ORE_CONFIGURE_FEATURE),
            Arrays.asList(
                    CountPlacementModifier.of(15), SquarePlacementModifier.of(),
                    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(56))));

    public static void modOreFeatureConfig() {

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MODID, "overworld_sulphur_ore"), OVERWORLD_SULPHUR_ORE_CONFIGURE_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(MODID, "overworld_sulphur_ore"), OVERWORLD_SULPHUR_ORE_PLACED_FEATURE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(
                Registry.PLACED_FEATURE_KEY, new Identifier(MODID, "overworld_sulphur_ore")));

    }

}
