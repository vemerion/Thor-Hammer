package mod.vemerion.thorhammer.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.thorhammer.ThorHammer;
import mod.vemerion.thorhammer.entity.HammerEntity;
import mod.vemerion.thorhammer.model.HammerModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class HammerRenderer extends EntityRenderer<HammerEntity> {
	public static final ResourceLocation HAMMER_TEXTURES = new ResourceLocation(ThorHammer.MODID,
			"textures/entity/hammer.png");
	private final HammerModel hammerModel = new HammerModel();

	public HammerRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	public void render(HammerEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.push();
		if (entityIn.getMotion().length() > 0.01) {
			matrixStackIn.rotate(Vector3f.YP.rotationDegrees(
					MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90.0F));
			matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(entityIn.getHammerRotation(partialTicks)));
			matrixStackIn.rotate(Vector3f.YP.rotationDegrees(90));
		} else {

		}

		IVertexBuilder ivertexbuilder = net.minecraft.client.renderer.ItemRenderer.getBuffer(bufferIn,
				this.hammerModel.getRenderType(this.getEntityTexture(entityIn)), false, false);
		this.hammerModel.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F,
				1.0F, 1.0F);
		matrixStackIn.pop();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	/**
	 * Returns the location of an entity's texture.
	 */
	@Override
	public ResourceLocation getEntityTexture(HammerEntity entity) {
		return HAMMER_TEXTURES;
	}
}
