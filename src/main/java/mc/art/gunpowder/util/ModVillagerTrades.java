package mc.art.gunpowder.util;

import mc.art.gunpowder.listener.ItemListener;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

public class ModVillagerTrades {

    public static void modVillagerTrades() {

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FLETCHER, 5,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 7),
                            new ItemStack(ItemListener.EXPLODE_ARROW, 1),
                            3, 8, 0.02F));
                    });

    }

}
