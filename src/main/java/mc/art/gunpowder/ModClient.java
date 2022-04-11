package mc.art.gunpowder;

import mc.art.gunpowder.entity.ModEntityType;
import mc.art.gunpowder.entity.renderer.*;
import mc.art.gunpowder.util.ModModelPredicateProvider;
import mc.art.gunpowder.util.ModSpawnPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

import java.util.UUID;

import static mc.art.gunpowder.ModMain.MODID;

public class ModClient implements ClientModInitializer {

    public static final Identifier SPAWN_PACKET = new Identifier(MODID, "spawn_packet");

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.INSTANCE.register(ModEntityType.DETONATOR_ENTITY, (ctx) -> new DetonatorEntityRenderer(ctx));
        EntityRendererRegistry.INSTANCE.register(ModEntityType.LVL_2_DETONATOR_ENTITY, (ctx) -> new Lvl2DetonatorEntityRenderer(ctx));
        EntityRendererRegistry.INSTANCE.register(ModEntityType.LVL_2_TNT_ENTITY, (ctx) -> new Tnt2EntityRenderer(ctx));
        EntityRendererRegistry.INSTANCE.register(ModEntityType.THROWN_TNT_ENTITY, (ctx) -> new ThrownTntEntityRenderer(ctx));
        EntityRendererRegistry.INSTANCE.register(ModEntityType.SUPER_CREEPER_ENTITY, (ctx) -> new SuperCreeperEntityRenderer(ctx));
        EntityRendererRegistry.INSTANCE.register(ModEntityType.EXPLODE_ARROW_ENTITY, (ctx) -> new ExplodeArrowEntityRenderer(ctx));

        ModModelPredicateProvider.registerModModels();
        receiveEntityPacket();

    }

    public void receiveEntityPacket() {

        ClientSidePacketRegistry.INSTANCE.register(SPAWN_PACKET, (ctx, byteBuf) -> {
            EntityType<?> et = Registry.ENTITY_TYPE.get(byteBuf.readVarInt());
            UUID uuid = byteBuf.readUuid();
            int entityId = byteBuf.readVarInt();
            Vec3d pos = ModSpawnPacket.PacketBufUtil.readVec3d(byteBuf);
            float pitch = ModSpawnPacket.PacketBufUtil.readAngle(byteBuf);
            float yaw = ModSpawnPacket.PacketBufUtil.readAngle(byteBuf);
            ctx.getTaskQueue().execute(() -> {
                if (MinecraftClient.getInstance().world == null)
                    throw new IllegalStateException("Tried to spawn entity in a null world!");
                Entity e = et.create(MinecraftClient.getInstance().world);
                if (e == null)
                    throw new IllegalStateException("Failed to create instance of entity \"" + Registry.ENTITY_TYPE.getId(et) + "\"!");
                e.updateTrackedPosition(pos);
                e.setPos(pos.x, pos.y, pos.z);
                e.setPitch(pitch);
                e.setYaw(yaw);
                e.setId(entityId);
                e.setUuid(uuid);
                MinecraftClient.getInstance().world.addEntity(entityId, e);
            });
        });
    }

}
