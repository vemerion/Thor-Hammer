package mod.vemerion.thorhammer;

import mod.vemerion.thorhammer.renderer.HammerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = ThorHammer.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {
	
	@SubscribeEvent
	public static void onRegister(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(ThorHammer.HAMMER_ENTITY, (renderManager) -> new HammerRenderer(renderManager));
	}

}
