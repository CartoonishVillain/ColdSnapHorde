package com.villain.coldsnaphorde.client.renderers.standardrenders;

import com.villain.coldsnaphorde.Constants;
import com.villain.coldsnaphorde.entities.mobs.basemob.ColdSnapCow;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderColdSnapCow extends MobRenderer<ColdSnapCow, CowModel<ColdSnapCow>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/entity/coldsnapcow.png");
    protected static final ResourceLocation HARVESTTEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/entity/harvestcoldsnapcow.png");

    public RenderColdSnapCow(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new CowModel<ColdSnapCow>(p_174304_.bakeLayer(ModelLayers.COW)), 0.5f);
    }

//    public RenderNetherColdSnapBrawler(EntityRenderDispatcher renderManagerIn) {
//        super(renderManagerIn, new ColdSnapBrawlerModel<>(), 0.5F);
//    }

    @Override
    public ResourceLocation getTextureLocation(ColdSnapCow entity) {
        if(entity.getHarvestTimer() > 0) return TEXTURE;
        else return HARVESTTEXTURE;
    }
}
