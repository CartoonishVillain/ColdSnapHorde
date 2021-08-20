package com.cartoonishvillain.coldsnaphorde.Client.Models.BaseModels;// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.cartoonishvillain.coldsnaphorde.Entities.Mobs.BaseMob.ColdSnapGunner;
import com.cartoonishvillain.coldsnaphorde.Entities.Mobs.BaseMob.GenericHordeMember;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ColdSnapGunnerModel<C extends GenericHordeMember> extends EntityModel<ColdSnapGunner> {
	private final ModelRenderer lowbody;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer head;
	private final ModelRenderer hat;
	private final ModelRenderer right_hand;
	private final ModelRenderer gun;

	public ColdSnapGunnerModel() {
		texWidth = 128;
		texHeight = 128;

		lowbody = new ModelRenderer(this);
		lowbody.setPos(0.0F, 24.0F, 0.0F);
		lowbody.texOffs(0, 36).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, -0.5F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 13.0F, 0.0F);
		body.texOffs(0, 16).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, -0.5F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(4.5F, 5.7F, 0.0F);
		setRotationAngle(left_hand, 0.0F, 0.0F, 1.1345F);
		left_hand.texOffs(32, 0).addBox(-0.6105F, -0.5038F, -1.0F, 12.0F, 2.0F, 2.0F, -0.5F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, 4.0F, 0.0F);
		head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, -0.5F, false);

		hat = new ModelRenderer(this);
		hat.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(hat);
		hat.texOffs(63, 13).addBox(-5.0F, -8.1F, -5.0F, 10.0F, 2.0F, 10.0F, -0.5F, false);
		hat.texOffs(31, 11).addBox(-4.0F, -13.1F, -4.0F, 8.0F, 6.0F, 8.0F, -0.5F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-4.5F, 5.7F, 0.0F);
		setRotationAngle(right_hand, -1.3728F, -1.2784F, 0.3372F);
		right_hand.texOffs(32, 0).addBox(-11.3895F, -0.5038F, -1.0F, 12.0F, 2.0F, 2.0F, -0.5F, true);

		gun = new ModelRenderer(this);
		gun.setPos(-10.6F, 0.4F, 0.0F);
		right_hand.addChild(gun);
		setRotationAngle(gun, 1.4904F, 0.2494F, 1.2553F);
		gun.texOffs(120, 124).addBox(-1.0F, -4.6F, -7.4F, 2.0F, 2.0F, 2.0F, -0.51F, false);
		gun.texOffs(103, 119).addBox(-1.0F, -5.0F, -6.9F, 2.0F, 4.0F, 5.0F, -0.5F, false);
		gun.texOffs(94, 115).addBox(-1.0F, -5.0F, -4.5F, 2.0F, 4.0F, 5.0F, -0.5F, false);
		gun.texOffs(114, 94).addBox(-1.0F, -2.0F, -4.5F, 2.0F, 4.0F, 5.0F, -0.5F, false);
		gun.texOffs(114, 102).addBox(-1.0F, -2.75F, -2.1F, 2.0F, 4.0F, 5.0F, -0.5F, false);
		gun.texOffs(112, 115).addBox(-0.5F, -3.5F, -6.1F, 2.0F, 4.0F, 5.0F, -0.5F, false);
		gun.texOffs(72, 119).addBox(-0.5F, -6.5F, -3.7F, 2.0F, 4.0F, 5.0F, -0.5F, false);
		gun.texOffs(120, 124).addBox(-1.0F, -3.4F, -7.4F, 2.0F, 2.0F, 2.0F, -0.51F, false);
	}

	@Override
	public void setupAnim(ColdSnapGunner entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.body.yRot = netHeadYaw * ((float)Math.PI / 180F) * 0.25F;
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		lowbody.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		left_hand.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		right_hand.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}