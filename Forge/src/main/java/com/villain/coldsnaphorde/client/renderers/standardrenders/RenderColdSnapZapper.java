package com.villain.coldsnaphorde.client.renderers.standardrenders;

import com.villain.coldsnaphorde.Constants;
import com.villain.coldsnaphorde.client.models.standardmodel.ColdSnapZapperModel;
import com.villain.coldsnaphorde.entities.mobs.basemob.ColdSnapZapper;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import static com.villain.coldsnaphorde.client.RenderManager.COLDSNAPZAPPER;

public class RenderColdSnapZapper extends MobRenderer<ColdSnapZapper, ColdSnapZapperModel<ColdSnapZapper>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/entity/coldsnapzapper.png");
    protected static final ResourceLocation ETEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/entity/ecoldsnapzapper.png");
    protected static final ResourceLocation PTEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/entity/pcoldsnapzapper.png");


    public RenderColdSnapZapper(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new ColdSnapZapperModel<>(p_174304_.bakeLayer(COLDSNAPZAPPER)), 0.5f);
    }

//    public RenderNetherColdSnapBrawler(EntityRenderDispatcher renderManagerIn) {
//        super(renderManagerIn, new ColdSnapBrawlerModel<>(), 0.5F);
//    }

    @Override
    public ResourceLocation getTextureLocation(ColdSnapZapper entity) {
        if (entity.getHordeVariant() == 0)
            return TEXTURE;
        else if(entity.getHordeVariant() == 2) return ETEXTURE;
        else if(entity.getHordeVariant() == 3) {return PTEXTURE;}
        else return TEXTURE;
    }
}
