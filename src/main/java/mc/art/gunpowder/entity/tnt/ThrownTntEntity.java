package mc.art.gunpowder.entity.tnt;

import mc.art.gunpowder.entity.ModEntityType;
import mc.art.gunpowder.listener.ItemListener;
import mc.art.gunpowder.util.ModSpawnPacket;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

import static mc.art.gunpowder.ModClient.SPAWN_PACKET;

public class ThrownTntEntity extends ThrownItemEntity {
    private static final TrackedData<Integer> FUSE;
    @Nullable
    private LivingEntity causingEntity;

    public ThrownTntEntity(EntityType<? extends ThrownTntEntity> entityType, World world) {
        super(entityType, world);
        this.intersectionChecked = true;
    }

    public ThrownTntEntity(World world, double x, double y, double z) {
        super(ModEntityType.DETONATOR_ENTITY, x, y, z, world);
    }

    public ThrownTntEntity(World world, LivingEntity owner) {
        super(ModEntityType.THROWN_TNT_ENTITY, owner, world);
    }

    public ThrownTntEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter) {
        this(ModEntityType.THROWN_TNT_ENTITY, world);
        this.setPosition(x, y, z);
        double d = world.random.nextDouble() * 6.2831854820251465D;
        this.setVelocity(-Math.sin(d) * 0.02D, 0.20000000298023224D, -Math.cos(d) * 0.02D);
        this.setFuse(80);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.causingEntity = igniter;
    }

    protected void initDataTracker() {
        this.dataTracker.startTracking(FUSE, 120);
    }

    protected MoveEffect getMoveEffect() {
        return MoveEffect.NONE;
    }

    public boolean collides() {
        return !this.isRemoved();
    }

    public void tick() {
        if (!this.hasNoGravity()) {
            this.setVelocity(this.getVelocity().add(0.0D, -0.04D, 0.0D));
        }

        this.move(MovementType.SELF, this.getVelocity());
        this.setVelocity(this.getVelocity().multiply(0.98D));
        if (this.onGround) {
            this.setVelocity(this.getVelocity().multiply(0.7D, -0.5D, 0.7D));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.world.isClient) {
                this.explode();
            }
        } else {
            this.updateWaterState();
            if (this.world.isClient) {
                this.world.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    private void explode() {
        float f = 4.0F;
        this.world.createExplosion(this, this.getX(), this.getBodyY(0.0625D), this.getZ(), 4.0F, Explosion.DestructionType.BREAK);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putShort("Fuse", (short)this.getFuse());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        this.setFuse(nbt.getShort("Fuse"));
    }

    @Nullable
    public LivingEntity getCausingEntity() {
        return this.causingEntity;
    }

    protected float getEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 0.15F;
    }

    public void setFuse(int fuse) {
        this.dataTracker.set(FUSE, fuse);
    }

    public int getFuse() {
        return (Integer)this.dataTracker.get(FUSE);
    }

    static {
        FUSE = DataTracker.registerData(TntEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if(!this.world.isClient) {
            world.createExplosion(this, getX(), getY(), getZ(), 4, Explosion.DestructionType.BREAK);
            this.world.sendEntityStatus(this, (byte) 4);
            this.remove(RemovalReason.KILLED);
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ItemListener.DETONATOR;
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return ModSpawnPacket.create(this, SPAWN_PACKET);
    }
}
