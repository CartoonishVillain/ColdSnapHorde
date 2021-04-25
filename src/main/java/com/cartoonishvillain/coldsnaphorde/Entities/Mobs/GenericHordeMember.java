package com.cartoonishvillain.coldsnaphorde.Entities.Mobs;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.Entities.Mobs.Behaviors.GifterSurprise;
import com.cartoonishvillain.coldsnaphorde.Entities.Mobs.Behaviors.HordeMovementGoal;
import com.cartoonishvillain.coldsnaphorde.Register;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.PillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class GenericHordeMember extends MonsterEntity {
    private BlockPos target = null;
    private Boolean HordeMember = false;

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new HordeMovementGoal<>(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, ColdSnapGifter.class, 6.0F, 1.0D, 1.2D, this::avoid));
    }

    private boolean avoid(@Nullable LivingEntity entity) {
        if (entity instanceof ColdSnapGifter) {
            int timer = ((ColdSnapGifter) entity).getTimer();
            return timer < 50;
        }
        return false;
    }

    @Override
    public void onDeath(DamageSource cause) {
        int random = world.rand.nextInt(100);
        if(random > 80 && !world.isRemote() && isHordeMember()){
            ItemEntity itemEntity = new ItemEntity(world, this.getPosX(), this.getPosY(), this.getPosZ(), new ItemStack(Register.PRESENT.get(), 1));
            world.addEntity(itemEntity);
        }
        super.onDeath(cause);
    }

    protected GenericHordeMember(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SNOW_GOLEM_AMBIENT;
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SNOW_GOLEM_HURT;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SNOW_GOLEM_DEATH;
    }

    public BlockPos getTarget() {return target;}

    public boolean isHordeMember(){return HordeMember;}

    public void toggleHordeMember(BlockPos center) {this.target = center; HordeMember = true;}

    @Override
    public void livingTick() {
        super.livingTick();
        if (!this.world.isRemote()) {
            int i = MathHelper.floor(this.getPosX());
            int j = MathHelper.floor(this.getPosY());
            int k = MathHelper.floor(this.getPosZ());
            if (shouldOverHeat(this.world.getBiome(this.getPosition()).getTemperature(), ColdSnapHorde.cconfig.HEATPROT.get())) {
                this.attackEntityFrom(DamageSource.ON_FIRE, 1.0F);
            }

            if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this)) {
                return;
            }

            BlockState blockstate = Blocks.SNOW.getDefaultState();

            for(int l = 0; l < 4; ++l) {
                i = MathHelper.floor(this.getPosX() + (double)((float)(l % 2 * 2 - 1) * 0.25F));
                j = MathHelper.floor(this.getPosY());
                k = MathHelper.floor(this.getPosZ() + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));
                BlockPos blockpos = new BlockPos(i, j, k);
                if (this.world.isAirBlock(blockpos) && !shouldOverHeat(this.world.getBiome(this.getPosition()).getTemperature(), ColdSnapHorde.cconfig.SNOWTRAIL.get()) && blockstate.isValidPosition(this.world, blockpos)) {
                    this.world.setBlockState(blockpos, blockstate);
                }
            }
        }
    }


    protected boolean shouldOverHeat(float currentTemp, int protectionlevel){
        switch(protectionlevel){
            case 0:
                return currentTemp > 0.3f;
            case 1:
                return currentTemp > 0.9f;
            case 2:
                return currentTemp > 1.5f;
            case 3:
                return false;
            default:
                return true;
        }
    }
}
