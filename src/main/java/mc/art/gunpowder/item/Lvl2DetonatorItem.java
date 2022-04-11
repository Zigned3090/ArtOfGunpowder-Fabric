package mc.art.gunpowder.item;

import mc.art.gunpowder.entity.detonator.Lvl2DetonatorEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Lvl2DetonatorItem extends Item {
    public Lvl2DetonatorItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.getItemCooldownManager().set(this, 20);
        if (!world.isClient) {
            Lvl2DetonatorEntity lvl2DetonatorEntity = new Lvl2DetonatorEntity(world, user);
            lvl2DetonatorEntity.setItem(itemStack);
            lvl2DetonatorEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(lvl2DetonatorEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

}
