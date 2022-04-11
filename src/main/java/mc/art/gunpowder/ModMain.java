package mc.art.gunpowder;

import mc.art.gunpowder.entity.ModEntityType;
import mc.art.gunpowder.register.RegisterBlock;
import mc.art.gunpowder.register.RegisterItem;
import mc.art.gunpowder.util.ModLootTableModifiers;
import mc.art.gunpowder.util.ModVillagerTrades;
import mc.art.gunpowder.world.ore.ModOreFeatureConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModMain implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("ap");
	public static final String MODID = "art_gunpowder";

	static {
		ClientPlayConnectionEvents.JOIN.register(((handler, sender, client) -> {
			client.player.sendMessage(new TranslatableText("join.message").formatted(Formatting.AQUA), false);
		}));
	}

	@Override
	public void onInitialize() {

		RegisterItem.registerItem();
		RegisterBlock.registerBlock();
		ModEntityType.modEntityType();
		ModOreFeatureConfig.modOreFeatureConfig();
		ModLootTableModifiers.modLootTableModifiers();
		ModVillagerTrades.modVillagerTrades();

		LOGGER.info("ART OF GUNPOWDER!");
	}
}
