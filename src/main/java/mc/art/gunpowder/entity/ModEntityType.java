package mc.art.gunpowder.entity;

import mc.art.gunpowder.entity.arrow.ExplodeArrowEntity;
import mc.art.gunpowder.entity.creeper.SuperCreeperEntity;
import mc.art.gunpowder.entity.detonator.DetonatorEntity;
import mc.art.gunpowder.entity.detonator.Lvl2DetonatorEntity;
import mc.art.gunpowder.entity.tnt.ThrownTntEntity;
import mc.art.gunpowder.entity.tnt.Tnt2Entity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static mc.art.gunpowder.ModMain.MODID;

public class ModEntityType {

    public static final EntityType<DetonatorEntity> DETONATOR_ENTITY = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(MODID, "detonator"),
            FabricEntityTypeBuilder.<DetonatorEntity>create(SpawnGroup.MISC, DetonatorEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F)).build());

    public static final EntityType<Lvl2DetonatorEntity> LVL_2_DETONATOR_ENTITY = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(MODID, "detonator_2"),
            FabricEntityTypeBuilder.<Lvl2DetonatorEntity>create(SpawnGroup.MISC, Lvl2DetonatorEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F)).build());

    public static final EntityType<Tnt2Entity> LVL_2_TNT_ENTITY = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(MODID, "tnt_2"),
            FabricEntityTypeBuilder.<Tnt2Entity>create(SpawnGroup.MISC, Tnt2Entity::new)
                    .dimensions(EntityDimensions.fixed(0.98F, 0.7F)).build());

    public static final EntityType<SuperCreeperEntity> SUPER_CREEPER_ENTITY = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(MODID, "super_creeper"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SuperCreeperEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6F, 1.7F)).trackRangeBlocks(7).build());

    public static final EntityType<ThrownTntEntity> THROWN_TNT_ENTITY = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(MODID, "thrown_tnt"),
            FabricEntityTypeBuilder.<ThrownTntEntity>create(SpawnGroup.MISC, ThrownTntEntity::new)
                    .dimensions(EntityDimensions.fixed(0.98F, 0.7F)).build());

    public static final EntityType<ExplodeArrowEntity> EXPLODE_ARROW_ENTITY = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(MODID, "explode_arrow"),
            FabricEntityTypeBuilder.<ExplodeArrowEntity>create(SpawnGroup.MISC, ExplodeArrowEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.5F)).build());

    public static void modEntityType() {
        FabricDefaultAttributeRegistry.register(SUPER_CREEPER_ENTITY, SuperCreeperEntity.createMobAttributes());
    }

}
