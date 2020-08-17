package mod.vemerion.thorhammer.item;

import com.google.common.collect.Multimap;

import mod.vemerion.thorhammer.ThorHammer;
import mod.vemerion.thorhammer.entity.HammerEntity;
import mod.vemerion.thorhammer.renderer.HammerTileEntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class HammerItem extends Item {
	private final float attackDamage;
	private final float attackSpeed;

	public HammerItem() {
		super(new Item.Properties().rarity(Rarity.EPIC).group(ItemGroup.COMBAT).maxStackSize(1)
				.setISTER(() -> HammerTileEntityRenderer::new));
		this.attackDamage = 6;
		this.attackSpeed = -2;
	}

	@Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);
		if (slot == EquipmentSlotType.MAINHAND) {
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER,
					"Weapon modifier", (double) this.attackDamage, AttributeModifier.Operation.ADDITION));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER,
					"Weapon modifier", (double) this.attackSpeed, AttributeModifier.Operation.ADDITION));
		}

		return multimap;
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		World world = attacker.world;
		target.knockBack(attacker, 2, (double) MathHelper.sin(attacker.rotationYaw * ((float) Math.PI / 180F)),
				(double) (-MathHelper.cos(attacker.rotationYaw * ((float) Math.PI / 180F))));
		world.playSound(null, attacker.getPosition(), ThorHammer.HAMMER_SWING_SOUND, SoundCategory.PLAYERS, 1,
				attacker.getRNG().nextFloat() * 0.5f + 0.5f);

		if (attacker.getRNG().nextDouble() < 0.05) {
			LightningBoltEntity lightning = new LightningBoltEntity(world, target.getPosX(), target.getPosY(),
					target.getPosZ(), false);
			world.addEntity(lightning);
		}
		return true;
	}

	@Override
	public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entityLiving;
			if (!worldIn.isRemote) {
				HammerEntity hammer = new HammerEntity(player, worldIn);
				hammer.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0);
				worldIn.addEntity(hammer);
				player.inventory.deleteStack(stack);
				entityLiving.world.playSound(null, entityLiving.getPosition(), ThorHammer.HAMMER_THROW_SOUND,
						SoundCategory.PLAYERS, 1, entityLiving.getRNG().nextFloat() * 0.2f + 0.8f);
			}
		}
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.SPEAR;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return Integer.MAX_VALUE;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		if (handIn == Hand.MAIN_HAND) {
			playerIn.setActiveHand(handIn);
			return ActionResult.resultConsume(itemstack);
		} else {
			return ActionResult.resultFail(itemstack);
		}
	}

}
