package mc.art.gunpowder.entity.renderer;

import mc.art.gunpowder.entity.creeper.SuperCreeperEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import static mc.art.gunpowder.ModMain.MODID;

@Environment(EnvType.CLIENT)
public class SuperCreeperEntityRenderer extends MobEntityRenderer<SuperCreeperEntity, CreeperEntityModel<SuperCreeperEntity>> {
    private static final Identifier TEXTURE = new Identifier(MODID, "textures/entity/creeper/super_creeper.png");

    public SuperCreeperEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CreeperEntityModel(context.getPart(EntityModelLayers.CREEPER)), 0.5F);
    }

    protected void scale(SuperCreeperEntity superCreeperEntity, MatrixStack matrixStack, float f) {
        float g = superCreeperEntity.getClientFuseTime(f);
        float h = 1.0F + MathHelper.sin(g * 100.0F) * g * 0.01F;
        g = MathHelper.clamp(g, 0.0F, 1.0F);
        g *= g;
        g *= g;
        float i = (1.0F + g * 0.4F) * h;
        float j = (1.0F + g * 0.1F) / h;
        matrixStack.scale(i, j, i);
    }

    protected float getAnimationCounter(SuperCreeperEntity superCreeperEntity, float f) {
        float g = superCreeperEntity.getClientFuseTime(f);
        return (int)(g * 10.0F) % 2 == 0 ? 0.0F : MathHelper.clamp(g, 0.5F, 1.0F);
    }

    public Identifier getTexture(SuperCreeperEntity superCreeperEntity) {
        return TEXTURE;
    }

}
