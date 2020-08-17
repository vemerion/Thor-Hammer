package mod.vemerion.thorhammer;

import mod.vemerion.thorhammer.entity.HammerEntity;
import mod.vemerion.thorhammer.item.HammerItem;
import mod.vemerion.thorhammer.item.ThorHelmet;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = ThorHammer.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {

	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(setup(new HammerItem(), "hammer"));
		event.getRegistry().register(setup(new ThorHelmet(), "thor_helmet"));
	}

	@SubscribeEvent
	public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
		EntityType<HammerEntity> hammer = EntityType.Builder
				.<HammerEntity>create(HammerEntity::new, EntityClassification.MISC).size(0.5f, 0.5f).build("hammer_entity");

		event.getRegistry().register(setup(hammer, "hammer_entity"));

	}

	@SubscribeEvent
	public static void onRegisterSound(RegistryEvent.Register<SoundEvent> event) {
		SoundEvent hammerSwingSound = new SoundEvent(new ResourceLocation(ThorHammer.MODID, "hammer_swing_sound"));
		SoundEvent hammerThrowSound = new SoundEvent(new ResourceLocation(ThorHammer.MODID, "hammer_throw_sound"));
	
		event.getRegistry().register(setup(hammerSwingSound, "hammer_swing_sound"));
		event.getRegistry().register(setup(hammerThrowSound, "hammer_throw_sound"));

	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
		return setup(entry, new ResourceLocation(ThorHammer.MODID, name));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}

}
