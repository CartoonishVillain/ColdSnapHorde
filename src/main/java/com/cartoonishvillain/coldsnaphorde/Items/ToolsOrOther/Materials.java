package com.cartoonishvillain.coldsnaphorde.Items.ToolsOrOther;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum Materials implements IItemTier {

    ICE(1, 200, 10f, 6.0f, 8, ()->{return Ingredient.fromItems(Items.PACKED_ICE);});

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private  final float attackDamage;
    private  final  int enchantability;
    private  final Supplier<Ingredient> repairMaterial;

    Materials(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial){
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getMaxUses() {
        return maxUses;
    }

    @Override
    public float getEfficiency() {
        return efficiency;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return repairMaterial.get();
    }

}
