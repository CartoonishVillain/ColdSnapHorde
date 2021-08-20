package com.cartoonishvillain.coldsnaphorde.Entities.Mobs.Behaviors;

import com.cartoonishvillain.coldsnaphorde.Entities.Mobs.BaseMob.GenericHordeMember;
import com.cartoonishvillain.coldsnaphorde.Register;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import javax.annotation.Nullable;
import java.util.*;

import static com.cartoonishvillain.coldsnaphorde.Entities.Mobs.BaseMob.GenericHordeMember.Infection;

public class GifterSurprise {

    World world;
    Entity exploder;
    double Entx;
    double Enty;
    double Entz;
    float radius;
    Vector3d position;
    DamageSource damageSource;
    ArrayList<BlockPos> blockPosArrayList = new ArrayList<>();
    int hordeVariant;
    ArrayList<Entity> effectedEntities = new ArrayList<>();



    public GifterSurprise(World world, @Nullable Entity exploder, double x, double y, double z, float radius) {
        this.world = world;
        this.Entx = x;
        this.Enty = y;
        this.Entz = z;
        this.exploder = exploder;
        this.radius = radius;
        this.damageSource = DamageSource.explosion((LivingEntity) exploder);
        this.position = new Vector3d(x, y, z);
        if(exploder instanceof GenericHordeMember){
            GenericHordeMember genericHordeMember = (GenericHordeMember) exploder;
            this.hordeVariant = genericHordeMember.getHordeVariant();
        }
    }


// The for loop equation for this class belongs under the following license
//    The MIT License (MIT)
//
//Copyright (c) 2021 b
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in
//all copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
//THE SOFTWARE.
// Due to them not having a very helpful name to find their work, the github repo is here: https://github.com/Luligabi1/ElementalCreepersRefabricated

    public void StageDetonation(){
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(Entx + x,Enty + y,Entz + z);
                    blockPosArrayList.add(blockPos);
                    ArrayList<Entity> entities = (ArrayList<Entity>) world.getEntities(exploder, new AxisAlignedBB(Entx+x+2, Enty+y+2, Entz+z+2, Entx+x-2, Enty+y-2, Entz+z-2), null);
                    for(Entity entity : entities){
                        if(!effectedEntities.contains(entity)) effectedEntities.add(entity);
                    }
                }
            }
        }
    }

    public void DetonateTest(){
        for(BlockPos blockPos : blockPosArrayList){
            world.setBlockAndUpdate(blockPos, Blocks.WATER.defaultBlockState());
        }
    }

    public void DetonateBlockDamage(){
        if(net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(exploder.level, exploder) && hordeVariant != 2){
            if(hordeVariant != 1){
                for (BlockPos blockPos : blockPosArrayList){
                    if((world.getBlockState(blockPos).equals(Blocks.AIR.defaultBlockState()) || world.getBlockState(blockPos).equals(Blocks.GRASS.defaultBlockState())) && !(world.getBlockState(blockPos.below()).equals(Blocks.AIR.defaultBlockState()) || world.getBlockState(blockPos.below()).equals(Blocks.GRASS.defaultBlockState()))){
                        world.setBlockAndUpdate(blockPos, Blocks.SNOW.defaultBlockState());
                    }else if(world.getBlockState(blockPos).equals(Blocks.WATER.defaultBlockState())) world.setBlockAndUpdate(blockPos, Blocks.ICE.defaultBlockState());
                    else if(world.getBlockState(blockPos).equals(Blocks.LAVA.defaultBlockState())) world.setBlockAndUpdate(blockPos, Blocks.OBSIDIAN.defaultBlockState());
                    else if(world.getBlockState(blockPos).getBlock().equals(Blocks.FARMLAND)) world.setBlockAndUpdate(blockPos, Blocks.DIRT.defaultBlockState());
                    else if(world.getBlockState(blockPos).equals(Blocks.ICE.defaultBlockState())) world.setBlockAndUpdate(blockPos, Blocks.PACKED_ICE.defaultBlockState());
                    else if(world.getBlockState(blockPos).getBlock().equals(Blocks.FIRE)) world.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
                }
            } else{
                for (BlockPos blockPos : blockPosArrayList){
                    if(world.getBlockState(blockPos).equals(Blocks.AIR.defaultBlockState())){
                        BlockState blockstate = Register.SLUSH.get().defaultBlockState();
                        if(blockstate.canSurvive(world, blockPos)){
                            world.setBlockAndUpdate(blockPos, blockstate);
                        }

                    }
                }
            }
        }
    }

    public void DetonateLivingHarm(){
        Vector3d vec3 = new Vector3d(this.Entx, this.Enty, this.Entz);
        for(Entity entity : effectedEntities){
            if (entity instanceof LivingEntity){
                float DamageRadius = radius * 1.5f;
                if(!entity.ignoreExplosion()){
                    double distanceFactor = Math.sqrt(entity.distanceToSqr(vec3)) / DamageRadius;
                    if(distanceFactor <= 1){
                        double directionalx = entity.getX() - this.Entx;
                        double directionaly = entity.getY() - this.Enty;
                        double directionalz = entity.getZ() - this.Entz;
                        double percentSeen =  Explosion.getSeenPercent(vec3, entity);
                        double damage = (1.0D - distanceFactor) * percentSeen;
                        if(hordeVariant != 2) {
                            double knockback = damage;
                            if (entity instanceof LivingEntity) {
                                knockback = ProtectionEnchantment.getExplosionKnockbackAfterDampener((LivingEntity) entity, damage);
                            }
                            entity.setDeltaMovement(entity.getDeltaMovement().add(directionalx * knockback, directionaly * knockback, directionalz * knockback));
                            if(hordeVariant == 3){Infection((LivingEntity) entity);}
                        }else{
                            ((LivingEntity) entity).randomTeleport(entity.getX() + entity.level.random.nextInt(10+10)-10,entity.getY() + entity.level.random.nextInt(10+10)-10,entity.getZ() + entity.level.random.nextInt(10+10)-10, true);
                        }
                        entity.hurt(this.damageSource, (float)((int)((damage * damage + damage) / 2.0D * 3.0D * (double)DamageRadius + 1.0D)));
                    }
                }
            }
        }
    }


}