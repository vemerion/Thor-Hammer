package mod.vemerion.thorhammer.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.thorhammer.model.HammerModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;

public class HammerTileEntityRenderer extends ItemStackTileEntityRenderer {
	private final HammerModel hammer = new HammerModel();

	@Override
	public void func_239207_a_(ItemStack itemStackIn, ItemCameraTransforms.TransformType transformType,
			MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		matrixStackIn.push();
		// matrixStackIn.translate(0.6f, 1.2f, 0.3f); // 0.3
		matrixStackIn.scale(1.0F, -1.0F, -1.0F);
		matrixStackIn.scale(0.5f, 0.5f, 0.5f);
		IVertexBuilder builder = ItemRenderer.getBuffer(bufferIn,
				this.hammer.getRenderType(HammerModel.TEXTURE_LOCATION), false, itemStackIn.hasEffect());
		this.hammer.render(matrixStackIn, builder, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStackIn.pop();
	}
}
