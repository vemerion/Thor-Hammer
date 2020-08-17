package mod.vemerion.thorhammer.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * Created using Tabula 8.0.0
 */
public class ThorHelmetModel<T extends LivingEntity> extends BipedModel<T> {
    public ModelRenderer leftWing;
    public ModelRenderer rightWing;

    public ThorHelmetModel(float modelSize) {
    	super(modelSize, 0, 32, 64);
        this.textureWidth = 32;
        this.textureHeight = 64;
        this.leftWing = new ModelRenderer(this, 0, 8);
        this.leftWing.setRotationPoint(4.5F, -8.0F, 0.0F);
        this.leftWing.addBox(0.0F, -12.0F, -4.0F, 0.0F, 20.0F, 8.0F, 0, 0, 0);
        this.rightWing = new ModelRenderer(this, 0, 8);
        this.rightWing.setRotationPoint(-4.5F, -8.0F, 0.0F);
        this.rightWing.addBox(0.0F, -12.0F, -4.0F, 0.0F, 20.0F, 8.0F, 0, 0, 0);
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, modelSize, modelSize, modelSize);
        this.bipedHead.addChild(this.leftWing);
        this.bipedHead.addChild(this.rightWing);   
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.bipedHead).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }
    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
