package mod.vemerion.thorhammer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ThorHammer.MODID, bus = EventBusSubscriber.Bus.FORGE)
public class ForgeEventSubscriber {
	
	@SubscribeEvent
	public static void hammerImmuneToLightning(EntityStruckByLightningEvent event) {
		if (event.getEntity() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) event.getEntity();
			if (player.getHeldItemMainhand().getItem().equals(ThorHammer.HAMMER)) {
				event.setCanceled(true);
			}
		}
	}


}
