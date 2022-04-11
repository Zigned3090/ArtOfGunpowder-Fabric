package mc.art.gunpowder.util;

import mc.art.gunpowder.listener.ItemListener;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {

    private static final Identifier ABANDONED_MINESHAFT = new Identifier("minecraft", "chests/abandoned_mineshaft");

    public static void modLootTableModifiers() {

        LootTableLoadingCallback.EVENT.register(((resourceManager, manager, id, supplier, setter) -> {
            if(ABANDONED_MINESHAFT.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(1.0F))
                        .with(ItemEntry.builder(ItemListener.SULPHUR))
                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(6, 15)).build());
                supplier.withPool(poolBuilder.build());
            }}));

    }

}
