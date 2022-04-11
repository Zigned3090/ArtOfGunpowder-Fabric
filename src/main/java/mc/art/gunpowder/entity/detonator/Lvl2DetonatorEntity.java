package mc.art.gunpowder.entity.detonator;

import mc.art.gunpowder.entity.ModEntityType;
import mc.art.gunpowder.listener.ItemListener;
import mc.art.gunpowder.util.ModSpawnPacket;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import static mc.art.gunpowder.ModClient.SPAWN_PACKET;

public class Lvl2DetonatorEntity extends ThrownItemEntity {
    public Lvl2DetonatorEntity(EntityType<? extends Lvl2DetonatorEntity> entityType, World world) {
        super(entityType, world);
    }

    public Lvl2DetonatorEntity(World world, double x, double y, double z) {
        super(ModEntityType.LVL_2_DETONATOR_ENTITY, x, y, z, world);
    }

    public Lvl2DetonatorEntity(World world, LivingEntity owner) {
        super(ModEntityType.LVL_2_DETONATOR_ENTITY, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemListener.LVL_2_DETONATOR;
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return ModSpawnPacket.create(this, SPAWN_PACKET);
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if(!this.world.isClient) {
            world.createExplosion(this, getX(), getY(), getZ(), 8, Explosion.DestructionType.BREAK);
            this.world.sendEntityStatus(this, (byte) 8);
            this.remove(RemovalReason.KILLED);
        }
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if(!this.world.isClient) {
            world.createExplosion(this, getX(), getY(), getZ(), 8, Explosion.DestructionType.BREAK);
            this.world.sendEntityStatus(this, (byte) 8);
            this.remove(RemovalReason.KILLED);
        }
    }

}
