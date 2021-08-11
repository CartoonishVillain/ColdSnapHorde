package com.cartoonishvillain.coldsnaphorde.Client.Models.StandardModel;

import com.cartoonishvillain.coldsnaphorde.Entities.Mobs.BaseMob.ColdSnapSnowballer;
import com.cartoonishvillain.coldsnaphorde.Entities.Mobs.BaseMob.GenericHordeMember;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.fml.common.Mod;

import java.beans.ParameterDescriptor;

public class ColdSnapSnowballerModel<C extends GenericHordeMember> extends EntityModel<ColdSnapSnowballer> {
    private static final String LOWBODY = "lowbody";
    private static final String BODY = "body";
    private static final String LEFTHAND = "lefthand";
    private static final String HEAD = "head";
    private static final String HAT = "hat";
    private static final String RIGHTHAND = "righthand";
    private final ModelPart lowbody;
    private final ModelPart body;
    private final ModelPart left_hand;
    private final ModelPart right_hand;
    private final ModelPart head;
    private final ModelPart hat;

    public ColdSnapSnowballerModel(ModelPart part){
        super(RenderType::entityCutoutNoCull);
        this.lowbody = part.getChild(LOWBODY);
        this.body = part.getChild(BODY);
        this.left_hand = part.getChild(LEFTHAND);
        this.right_hand = part.getChild(RIGHTHAND);
        this.head = part.getChild(HEAD);
        this.hat = part.getChild(HAT);
        lowbody.setPos(0.0F, 24.0F, 0.0F);
        body.setPos(0.0F, 13.0F, 0.0F);
        left_hand.setPos(4.5F, 6.2F, 0.0F);
        right_hand.setPos(-4.5F, 6.2F, 0.0F);
        left_hand.setRotation(0.0F, 0.0F, 0.7854F);
        right_hand.setRotation( 0.0F, 0.0F, -0.7854F);
        head.setPos(0.0F, 4.0F, 0.0F);
        head.children.put(HAT, hat);
    }

    public static LayerDefinition createLayer(){
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        CubeDeformation cubeDeformation5 = new CubeDeformation(-0.5f);
        CubeDeformation cubeDeformation2dot5 = new CubeDeformation(-2.5f);
        partDefinition.addOrReplaceChild(LOWBODY, CubeListBuilder.create()
                .texOffs(0, 36).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, cubeDeformation5), PartPose.ZERO);
        partDefinition.addOrReplaceChild(BODY, CubeListBuilder.create()
                .texOffs(0, 16).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, cubeDeformation5)
                .texOffs(0, 61).addBox(-8.0F, -23.0F, -8.0F, 16.0F, 16.0F, 16.0F, cubeDeformation2dot5), PartPose.ZERO);
        partDefinition.addOrReplaceChild(LEFTHAND, CubeListBuilder.create()
                .texOffs(32, 0).addBox(-0.7879F, -0.495F, -1.0F, 12.0F, 2.0F, 2.0F, cubeDeformation5), PartPose.ZERO);
        partDefinition.addOrReplaceChild(RIGHTHAND, CubeListBuilder.create()
                .texOffs(32, 0).addBox(-11.2121F, -0.495F, -1.0F, 12.0F, 2.0F, 2.0F, cubeDeformation5), PartPose.ZERO);
        partDefinition.addOrReplaceChild(HEAD, CubeListBuilder.create()
                .texOffs(0,0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, cubeDeformation5), PartPose.ZERO);
        partDefinition.addOrReplaceChild(HAT, CubeListBuilder.create()
                .texOffs(63, 13).addBox(-5.0F, -8.0F, -5.0F, 10.0F, 2.0F, 10.0F, cubeDeformation5)
                .texOffs(31, 11).addBox(-4.0F, -13.0F, -4.0F, 8.0F, 6.0F, 8.0F, cubeDeformation5), PartPose.ZERO);
        return LayerDefinition.create(meshDefinition, 128, 128);
    }




    @Override
    public void setupAnim(ColdSnapSnowballer entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.body.yRot = netHeadYaw * ((float)Math.PI / 180F) * 0.25F;
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        lowbody.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        left_hand.render(matrixStack, buffer, packedLight, packedOverlay);
        right_hand.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}
