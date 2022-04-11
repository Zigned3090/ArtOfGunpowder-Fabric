package mc.art.gunpowder.entity.creeper;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class SuperCreeperIgniteGoal extends Goal {

    private final SuperCreeperEntity superCreeperEntity;
    @Nullable
    private LivingEntity target;

    public SuperCreeperIgniteGoal(SuperCreeperEntity superCreeperEntity) {
        this.superCreeperEntity = superCreeperEntity;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    public boolean canStart() {
        LivingEntity livingEntity = this.superCreeperEntity.getTarget();
        return this.superCreeperEntity.getFuseSpeed() > 0 || livingEntity != null && this.superCreeperEntity.squaredDistanceTo(livingEntity) < 18.0D;
    }

    public void start() {
        this.superCreeperEntity.getNavigation().stop();
        this.target = this.superCreeperEntity.getTarget();
    }

    public void stop() {
        this.target = null;
    }

    public boolean shouldRunEveryTick() {
        return true;
    }

    public void tick() {
        if (this.target == null) {
            this.superCreeperEntity.setFuseSpeed(-1);
        } else if (this.superCreeperEntity.squaredDistanceTo(this.target) > 49.0D) {
            this.superCreeperEntity.setFuseSpeed(-1);
        } else if (!this.superCreeperEntity.getVisibilityCache().canSee(this.target)) {
            this.superCreeperEntity.setFuseSpeed(-1);
        } else {
            this.superCreeperEntity.setFuseSpeed(1);
        }
    }

}
