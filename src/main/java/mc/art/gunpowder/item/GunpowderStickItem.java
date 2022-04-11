package mc.art.gunpowder.item;

import mc.art.gunpowder.entity.tnt.ThrownTntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class GunpowderStickItem extends Item {
    public GunpowderStickItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) {
            ThrownTntEntity thrownTntEntity = new ThrownTntEntity(world, user);
            thrownTntEntity.setItem(itemStack);
            thrownTntEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            thrownTntEntity.setFuse(160);
            world.spawnEntity(thrownTntEntity);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

}
