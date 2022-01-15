package mod.vemerion.thorhammer.entity;

import mod.vemerion.thorhammer.ThorHammer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class HammerEntity extends AbstractArrowEntity {

	private float hammerRotation;
	private float prevHammerRotation;

	public HammerEntity(EntityType<? extends HammerEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public HammerEntity(LivingEntity shooter, World worldIn) {
		super(ThorHammer.HAMMER_ENTITY, shooter, worldIn);
		this.setNoGravity(true);
	}

	@Override
	public void tick() {
		super.tick();
		prevHammerRotation = hammerRotation;
		hammerRotation -= 360f / 10;

		if (!world.isRemote && timeInGround == 0 && ticksExisted > 15 && ticksExisted % 3 == 0) {

			Entity shooter = func_234616_v_(); // getShooter()
			if (shooter != null && shooter.isAlive()) {
				shoot(shooter.getPosX() - getPosX(), shooter.getEyePosition(0).getY() - getPosY(),
						shooter.getPosZ() - getPosZ(), 2, 0);
			} else {
				this.setMotion(0, -1, 0);
			}
		}
	}

	public float getHammerRotation(float partialTicks) {
		if (timeInGround == 0) {
			return MathHelper.lerp(partialTicks, prevHammerRotation, hammerRotation);
		} else {
			return 30;
		}
	}

	protected void onEntityHit(EntityRayTraceResult result) {
		if (!world.isRemote && result.getEntity() instanceof LivingEntity) {
			LivingEntity struck = (LivingEntity) result.getEntity();
			if (!struck.getUniqueID().equals(func_234616_v_().getUniqueID())) {
				Vector2f xzPos = new Vector2f((float) struck.getPosX(), (float) struck.getPosZ());
				Vector3d Vector3d = this.getMotion().rotateYaw(isToTheLeftOf(xzPos) ? -90 : 90).mul(1.0D, 0.0D, 1.0D)
						.normalize().scale(0.8);
				struck.addVelocity(Vector3d.x, 0.1D, Vector3d.z);
				struck.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 6);
				playSound(SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, 1, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));
			}
		}
	}

	@Override
	public void onCollideWithPlayer(PlayerEntity entityIn) {
		if (!world.isRemote && ticksExisted > 10) {
			Entity shooter = func_234616_v_();
			if (shooter == null || shooter.getUniqueID() == entityIn.getUniqueID()) {
				if (entityIn.inventory.addItemStackToInventory(getArrowStack())) {
					entityIn.onItemPickup(this, 1);
					remove();
				} else {
					this.setMotion(0, -1, 0);
				}
			}
		}
	}

	private boolean isToTheLeftOf(Vector2f point) {
		Vector3d direction = getMotion().normalize();
		Vector2f forward = new Vector2f((float) direction.x, (float) direction.z);
		point = new Vector2f(point.x - (float) getPosX(), point.y - (float) getPosZ());
		return forward.x * point.y - forward.y * point.x > 0;
	}

	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack(ThorHammer.HAMMER);
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
