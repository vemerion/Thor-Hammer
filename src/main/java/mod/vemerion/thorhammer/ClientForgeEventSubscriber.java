package mod.vemerion.thorhammer;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickEmpty;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ThorHammer.MODID, bus = EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEventSubscriber {

	private static final net.minecraft.util.math.vector.Vector3d[] startPositions = new Vector3d[] {
			new Vector3d(1, 1, -1), new Vector3d(-1, 1, -0.5) };
	private static final Vector3d[] stopPositions = new Vector3d[] { new Vector3d(0.4, 0.4, -1.5),
			new Vector3d(-0.4, 0.4, -1.0) };

	private static final Vector3d[] startRotation = new Vector3d[] { new Vector3d(0, 0, 0), new Vector3d(0, 0, 0) };
	private static final Vector3d[] stopRotation = new Vector3d[] { new Vector3d(-80, 55, 0),
			new Vector3d(-80, -55, 0) };

	private static int i;
	private static boolean wasAttacking;

	@SubscribeEvent
	public static void hammerAnimations(RenderHandEvent event) {
		if (event.getItemStack().getItem().equals(ThorHammer.HAMMER)) {
			ClientPlayerEntity player = Minecraft.getInstance().player;
			if (event.getSwingProgress() > 0) {
				if (!wasAttacking)
					i = (i + 1) % 2;

				MatrixStack matrix = event.getMatrixStack();
				double progress = Math.sqrt(event.getSwingProgress());
				event.setCanceled(true);
				matrix.rotate(new Quaternion((float) MathHelper.lerp(progress, startRotation[i].x, stopRotation[i].x),
						(float) MathHelper.lerp(progress, startRotation[i].y, stopRotation[i].y),
						(float) MathHelper.lerp(progress, startRotation[i].z, stopRotation[i].z), true));
				matrix.translate(MathHelper.lerp(progress, startPositions[i].x, stopPositions[i].x),
						MathHelper.lerp(progress, startPositions[i].y, stopPositions[i].y),
						MathHelper.lerp(progress, startPositions[i].z, stopPositions[i].z));

				event.getItemStack().getItem().getItemStackTileEntityRenderer().func_239207_a_(event.getItemStack(),
						ItemCameraTransforms.TransformType.NONE, matrix, event.getBuffers(), event.getLight(),
						OverlayTexture.NO_OVERLAY);
				wasAttacking = true;
			} else if (player.isHandActive() && player.getActiveHand().equals(event.getHand())) {
				wasAttacking = false;

				MatrixStack matrix = event.getMatrixStack();
				double progress = Math.min(1,
						((double) event.getItemStack().getUseDuration()
								- ((double) player.getItemInUseCount() - (double) event.getPartialTicks() + 1.0d))
								/ 20d);

				event.setCanceled(true);
				matrix.rotate(new Quaternion((float) MathHelper.lerp(progress, 0, 45), 0, 0, true));
				matrix.translate(1, 0, MathHelper.lerp(progress, -1, -0.8));

				event.getItemStack().getItem().getItemStackTileEntityRenderer().func_239207_a_(event.getItemStack(),
						ItemCameraTransforms.TransformType.NONE, matrix, event.getBuffers(), event.getLight(),
						OverlayTexture.NO_OVERLAY);
			} else {
				wasAttacking = false;

			}
		}
	}

	@SubscribeEvent
	public static void hammerSwingSound(LeftClickEmpty event) {
		if (event.getEntity() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) event.getEntity();
			if (player.getHeldItemMainhand().getItem().equals(ThorHammer.HAMMER)) {
				player.playSound(ThorHammer.HAMMER_SWING_SOUND, 1, player.getRNG().nextFloat() * 0.5f + 0.5f);
			}
		}
	}
}
