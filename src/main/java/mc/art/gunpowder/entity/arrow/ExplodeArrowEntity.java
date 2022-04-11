package mc.art.gunpowder.entity.arrow;

import mc.art.gunpowder.entity.ModEntityType;
import mc.art.gunpowder.listener.ItemListener;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class ExplodeArrowEntity extends PersistentProjectileEntity {

    public ExplodeArrowEntity(EntityType<? extends ExplodeArrowEntity> entityType, World world) {
        super((EntityType<? extends ExplodeArrowEntity>) entityType, world);
    }

    public ExplodeArrowEntity(World world, double x, double y, double z) {
        super(ModEntityType.EXPLODE_ARROW_ENTITY, x, y, z, world);
    }

    public ExplodeArrowEntity(World world, LivingEntity owner) {
        super(ModEntityType.EXPLODE_ARROW_ENTITY, owner, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ItemListener.EXPLODE_ARROW);
    }

    public void initFromStack(ItemStack stack) {
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if(!this.world.isClient) {
            world.createExplosion(this, getX(), getY(), getZ(), 4, Explosion.DestructionType.BREAK);
            this.world.sendEntityStatus(this, (byte) 4);
            this.remove(RemovalReason.KILLED);
        }
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if(hitResult.getType() != HitResult.Type.MISS) {
            world.createExplosion(this, getX(), getY(), getZ(), 4, Explosion.DestructionType.BREAK);
            this.world.sendEntityStatus(this, (byte) 4);
            this.remove(RemovalReason.KILLED);
        }
    }
}