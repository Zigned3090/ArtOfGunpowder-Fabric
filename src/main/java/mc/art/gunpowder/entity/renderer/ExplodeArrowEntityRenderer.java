package mc.art.gunpowder.entity.renderer;

import mc.art.gunpowder.entity.arrow.ExplodeArrowEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

import static mc.art.gunpowder.ModMain.MODID;

@Environment(EnvType.CLIENT)
public class ExplodeArrowEntityRenderer extends ProjectileEntityRenderer<ExplodeArrowEntity> {

    public static final Identifier TEXTURE = new Identifier(MODID, "textures/entity/projectile/explode_arrow.png");

    public ExplodeArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(ExplodeArrowEntity explodeArrowEntity) {
        return TEXTURE;
    }
}

