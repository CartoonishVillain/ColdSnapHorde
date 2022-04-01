package com.cartoonishvillain.coldsnaphorde.items.projectiles;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.Register;
import com.cartoonishvillain.coldsnaphorde.entities.projectiles.HealingSnowballEntity;
import com.cartoonishvillain.coldsnaphorde.entities.projectiles.LightningSnowEntity;
import com.cartoonishvillain.coldsnaphorde.items.Tier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HealingSnowball extends Item {
    Tier tier;
    public HealingSnowball(Tier tier) {
        super(new Properties().tab(ColdSnapHorde.TAB).stacksTo(16));
        this.tier = tier;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (playerIn.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isClientSide) {
            HealingSnowballEntity snowballentity = new HealingSnowballEntity(Register.HEALINGSNOWBALLPROJECTILE.get(), worldIn, playerIn, tier);
            snowballentity.setItem(itemstack);
            snowballentity.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 1.5F, 1.0F);
            worldIn.addFreshEntity(snowballentity);
        }

        playerIn.awardStat(Stats.ITEM_USED.get(this));
        if (!playerIn.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, worldIn.isClientSide());
    }
}