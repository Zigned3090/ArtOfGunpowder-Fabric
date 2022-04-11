package mc.art.gunpowder.item;

import mc.art.gunpowder.entity.arrow.ExplodeArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ExplodeArrowItem extends Item {
    public ExplodeArrowItem(Settings settings) {
        super(settings);
    }

    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        ExplodeArrowEntity explodeArrowEntity = new ExplodeArrowEntity(world, shooter);
        explodeArrowEntity.initFromStack(stack);
        return explodeArrowEntity;
    }

}
