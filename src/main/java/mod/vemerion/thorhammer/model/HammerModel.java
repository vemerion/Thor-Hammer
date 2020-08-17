package mod.vemerion.thorhammer.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.thorhammer.ThorHammer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

// Made with Blockbench 3.6.3

public class HammerModel extends Model {
	public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(ThorHammer.MODID,
			"textures/entity/hammer.png");

	private final ModelRenderer hammer;
	private final ModelRenderer headBackTopGroup;
	private final ModelRenderer headBackLeftGroup;
	private final ModelRenderer headBackBottomGroup;
	private final ModelRenderer headBackRightGroup;
	private final ModelRenderer headFrontTopGroup;
	private final ModelRenderer headFrontLeftGroup;
	private final ModelRenderer headFrontBottomGroup;
	private final ModelRenderer headFrontRightGroup;

	public HammerModel() {
		super(RenderType::getEntitySolid);
		textureWidth = 64;
		textureHeight = 64;

		hammer = new ModelRenderer(this);
		hammer.setRotationPoint(0.0F, 24.0F, 0.0F);
		hammer.setTextureOffset(0, 22).addBox(-1.0F, -20.0F, -3.0F, 2.0F, 18.0F, 4.0F, 0.0F, false);
		hammer.setTextureOffset(18, 31).addBox(1.0F, -20.0F, -2.0F, 1.0F, 18.0F, 2.0F, 0.0F, false);
		hammer.setTextureOffset(12, 31).addBox(-2.0F, -20.0F, -2.0F, 1.0F, 18.0F, 2.0F, 0.0F, false);
		hammer.setTextureOffset(30, 22).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		hammer.setTextureOffset(6, 0).addBox(-3.0F, -4.0F, -2.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
		hammer.setTextureOffset(6, 0).addBox(2.0F, -4.0F, -2.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
		hammer.setTextureOffset(0, 0).addBox(-5.0F, -29.0F, -7.0F, 10.0F, 8.0F, 12.0F, 0.0F, false);
		hammer.setTextureOffset(24, 39).addBox(-4.0F, -30.0F, -7.0F, 8.0F, 10.0F, 12.0F, 0.0F, false);
		hammer.setTextureOffset(30, 30).addBox(-4.0F, -29.0F, -8.73F, 8.0F, 8.0F, 1.0F, 0.0F, false);
		hammer.setTextureOffset(12, 22).addBox(-4.0F, -29.0F, 5.73F, 8.0F, 8.0F, 1.0F, 0.0F, false);

		headBackTopGroup = new ModelRenderer(this);
		headBackTopGroup.setRotationPoint(0.0F, -30.0F, 5.0F);
		hammer.addChild(headBackTopGroup);
		setRotationAngle(headBackTopGroup, 1.0472F, 0.0F, 0.0F);
		headBackTopGroup.setTextureOffset(32, 0).addBox(-4.0F, 0.0F, -1.0F, 8.0F, 2.0F, 1.0F, 0.0F, false);

		headBackLeftGroup = new ModelRenderer(this);
		headBackLeftGroup.setRotationPoint(-5.0F, -25.0F, 5.0F);
		hammer.addChild(headBackLeftGroup);
		setRotationAngle(headBackLeftGroup, 0.0F, -1.0472F, 0.0F);
		headBackLeftGroup.setTextureOffset(0, 0).addBox(0.0F, -4.0F, -1.0F, 2.0F, 8.0F, 1.0F, 0.0F, false);

		headBackBottomGroup = new ModelRenderer(this);
		headBackBottomGroup.setRotationPoint(0.0F, -20.0F, 5.0F);
		hammer.addChild(headBackBottomGroup);
		setRotationAngle(headBackBottomGroup, 2.0944F, 0.0F, 0.0F);
		headBackBottomGroup.setTextureOffset(32, 0).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 2.0F, 1.0F, 0.0F, false);

		headBackRightGroup = new ModelRenderer(this);
		headBackRightGroup.setRotationPoint(5.0F, -25.0F, 5.0F);
		hammer.addChild(headBackRightGroup);
		setRotationAngle(headBackRightGroup, 0.0F, 1.0472F, 0.0F);
		headBackRightGroup.setTextureOffset(0, 0).addBox(-2.0F, -4.0F, -1.0F, 2.0F, 8.0F, 1.0F, 0.0F, false);

		headFrontTopGroup = new ModelRenderer(this);
		headFrontTopGroup.setRotationPoint(0.0F, -30.0F, -7.0F);
		hammer.addChild(headFrontTopGroup);
		setRotationAngle(headFrontTopGroup, -1.0472F, 0.0F, 0.0F);
		headFrontTopGroup.setTextureOffset(32, 0).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 2.0F, 1.0F, 0.0F, false);

		headFrontLeftGroup = new ModelRenderer(this);
		headFrontLeftGroup.setRotationPoint(-5.0F, -25.0F, -7.0F);
		hammer.addChild(headFrontLeftGroup);
		setRotationAngle(headFrontLeftGroup, 0.0F, 1.0472F, 0.0F);
		headFrontLeftGroup.setTextureOffset(0, 0).addBox(0.0F, -4.0F, 0.0F, 2.0F, 8.0F, 1.0F, 0.0F, false);

		headFrontBottomGroup = new ModelRenderer(this);
		headFrontBottomGroup.setRotationPoint(0.0F, -20.0F, -7.0F);
		hammer.addChild(headFrontBottomGroup);
		setRotationAngle(headFrontBottomGroup, 1.0472F, 0.0F, 0.0F);
		headFrontBottomGroup.setTextureOffset(32, 0).addBox(-4.0F, -2.0F, 0.0F, 8.0F, 2.0F, 1.0F, 0.0F, false);

		headFrontRightGroup = new ModelRenderer(this);
		headFrontRightGroup.setRotationPoint(5.0F, -25.0F, -7.0F);
		hammer.addChild(headFrontRightGroup);
		setRotationAngle(headFrontRightGroup, 0.0F, -1.0472F, 0.0F);
		headFrontRightGroup.setTextureOffset(0, 0).addBox(-2.0F, -4.0F, 0.0F, 2.0F, 8.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		hammer.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}