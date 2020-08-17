package mod.vemerion.thorhammer;

import mod.vemerion.thorhammer.entity.HammerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod(ThorHammer.MODID)
public class ThorHammer {
	public static final String MODID = "thor-hammer";
	
	@ObjectHolder(ThorHammer.MODID + ":hammer")
	public static final Item HAMMER = null;
	
	@ObjectHolder(ThorHammer.MODID + ":thor_helmet")
	public static final Item THOR_HELMET = null;

	
	@ObjectHolder(ThorHammer.MODID + ":hammer_swing_sound")
	public static final SoundEvent HAMMER_SWING_SOUND = null;

	@ObjectHolder(ThorHammer.MODID + ":hammer_throw_sound")
	public static final SoundEvent HAMMER_THROW_SOUND = null;
	
	@ObjectHolder(ThorHammer.MODID + ":hammer_entity")
	public static final EntityType<HammerEntity> HAMMER_ENTITY = null;
}
