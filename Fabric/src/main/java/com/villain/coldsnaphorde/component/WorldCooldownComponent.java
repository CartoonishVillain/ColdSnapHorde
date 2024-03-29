package com.villain.coldsnaphorde.component;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import net.minecraft.nbt.CompoundTag;

public class WorldCooldownComponent implements ComponentV3 {
    int levelBeaten = 0;
    public int getLevelBeaten() {return levelBeaten;}


    public void setLevelBeaten(int level) {this.levelBeaten = level;}


    @Override
    public void readFromNbt(CompoundTag nbt) {
        levelBeaten = nbt.getInt("levelbeaten");
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        tag.putInt("levelbeaten", levelBeaten);
    }
}
