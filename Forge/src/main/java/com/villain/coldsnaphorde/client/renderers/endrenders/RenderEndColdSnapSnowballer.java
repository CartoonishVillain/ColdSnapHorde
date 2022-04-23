package com.villain.coldsnaphorde.client.renderers.endrenders;

import com.villain.coldsnaphorde.Constants;
import com.villain.coldsnaphorde.client.models.endmodel.EndColdSnapSnowballerModel;
import com.villain.coldsnaphorde.entities.mobs.basemob.ColdSnapSnowballer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import static com.villain.coldsnaphorde.client.RenderManager.ECOLDSNAPSNOWBALLER;

public class RenderEndColdSnapSnowballer extends MobRenderer<ColdSnapSnowballer, EndColdSnapSnowballerModel<ColdSnapSnowballer>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MOD_ID,"textures/entity/ecoldsnapsnowballer.png");

    public RenderEndColdSnapSnowballer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new EndColdSnapSnowballerModel<>(p_174304_.bakeLayer(ECOLDSNAPSNOWBALLER)), 0.5f);
    }

//    public RenderNetherColdSnapBrawler(EntityRenderDispatcher renderManagerIn) {
//        super(renderManagerIn, new ColdSnapBrawlerModel<>(), 0.5F);
//    }

    @Override
    public ResourceLocation getTextureLocation(ColdSnapSnowballer entity) {
        return TEXTURE;
    }
}
