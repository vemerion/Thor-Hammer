package mod.vemerion.thorhammer.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import mod.vemerion.thorhammer.ThorHammer;
import mod.vemerion.thorhammer.entity.HammerEntity;
import mod.vemerion.thorhammer.renderer.HammerTileEntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
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
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class HammerItem extends Item {
	private final float attackDamage;
	private final float attackSpeed;
	private Multimap<Attribute, AttributeModifier> attributeModifiers;

	public HammerItem() {
		super(new Item.Properties().rarity(Rarity.EPIC).group(ItemGroup.COMBAT).maxStackSize(1)
				.setISTER(() -> HammerTileEntityRenderer::new));
		this.attackDamage = 6;
		this.attackSpeed = -2;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		if (slot == EquipmentSlotType.MAINHAND) {
			if (attributeModifiers == null) {
				Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
				builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier",
						attackDamage, AttributeModifier.Operation.ADDITION));
				builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier",
						attackSpeed, AttributeModifier.Operation.ADDITION));
				this.attributeModifiers = builder.build();
			}
			return attributeModifiers;
		}

		return super.getAttributeModifiers(slot, stack);
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		World world = attacker.world;
		target.applyKnockback(2, (double) MathHelper.sin(attacker.rotationYaw * ((float) Math.PI / 180F)),
				(double) (-MathHelper.cos(attacker.rotationYaw * ((float) Math.PI / 180F))));
		world.playSound(null, attacker.getPosition(), ThorHammer.HAMMER_SWING_SOUND, SoundCategory.PLAYERS, 1,
				attacker.getRNG().nextFloat() * 0.5f + 0.5f);

		if (attacker.getRNG().nextDouble() < 0.05) {
			LightningBoltEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
			lightning.moveForced(Vector3d.copyCenteredHorizontally(target.getPosition()));
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
				hammer.func_234612_a_(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0);
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
