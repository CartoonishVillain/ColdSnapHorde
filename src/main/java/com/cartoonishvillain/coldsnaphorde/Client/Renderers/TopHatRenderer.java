package com.cartoonishvillain.coldsnaphorde.Client.Renderers;

import com.cartoonishvillain.coldsnaphorde.Client.Models.TopHatModel;
import com.cartoonishvillain.coldsnaphorde.Items.TopHat;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class TopHatRenderer extends GeoArmorRenderer<TopHat> {
    public TopHatRenderer() {
        super(new TopHatModel());
    }

}
