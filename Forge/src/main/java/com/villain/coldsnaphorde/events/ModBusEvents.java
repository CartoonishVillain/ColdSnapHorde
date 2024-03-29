package com.villain.coldsnaphorde.events;

import com.villain.coldsnaphorde.Constants;
import com.villain.coldsnaphorde.ForgeColdSnapHorde;
import com.villain.coldsnaphorde.FrostEffect;
import com.villain.coldsnaphorde.Register;
import com.villain.coldsnaphorde.capabilities.IWorldCapabilityManager;
import com.villain.coldsnaphorde.capabilities.PlayerCapabilityManager;
import com.villain.coldsnaphorde.entities.Spawns;
import com.villain.coldsnaphorde.entities.mobs.basemob.*;
import com.villain.coldsnaphorde.items.ColdSpawnEggItem;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {

    @SubscribeEvent
    public static void interModComms(InterModEnqueueEvent e){
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.CHARM.getMessageBuilder().build());
    }

    @SubscribeEvent
    public static void capabilityRegister(final RegisterCapabilitiesEvent event){
        event.register(IWorldCapabilityManager.class);
        event.register(PlayerCapabilityManager.class);
        ForgeColdSnapHorde.PLAYERCAPABILITYINSTANCE = CapabilityManager.get(new CapabilityToken<>() {});
        ForgeColdSnapHorde.WORLDCAPABILITYINSTANCE = CapabilityManager.get(new CapabilityToken<>() {});
    }

    @SubscribeEvent
    public static void effect(final RegistryEvent.Register<MobEffect> event){
        FrostEffect.init();
    }

    @SubscribeEvent
    public static void entityRegister(final RegistryEvent.Register<EntityType<?>> event){
        Spawns.PlacementManager();
    }

    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event){
        ColdSpawnEggItem.initSpawnEggs();
    }

    @SubscribeEvent
    public static void itemRegister(final RegistryEvent.Register<Item> event){
        ForgeColdSnapHorde.TOPHATS = new ArrayList<>(List.of(Register.TOPHAT.get(), Register.REDTOPHAT.get(), Register.BLUETOPHAT.get(), Register.GREENTOPHAT.get(), Register.PURPLETOPHAT.get()));
    }

    @SubscribeEvent
    public static void attributeAssigner(EntityAttributeCreationEvent event){
        event.put(Register.COLDSNAPSTABBER.get(), ColdSnapStabber.customAttributes().build());
        event.put(Register.COLDSNAPGUNNER.get(), ColdSnapGunner.customAttributes().build());
        event.put(Register.COLDSNAPSNOWBALLER.get(), ColdSnapSnowballer.customAttributes().build());
        event.put(Register.COLDSNAPGIFTER.get(), ColdSnapGifter.customAttributes().build());
        event.put(Register.COLDSNAPZAPPER.get(), ColdSnapZapper.customAttributes().build());
        event.put(Register.COLDSNAPBRAWLER.get(), ColdSnapBrawler.customAttributes().build());
        event.put(Register.COLDSNAPCOW.get(), ColdSnapCow.customAttributes().build());

        event.put(Register.PCOLDSNAPSTABBER.get(), ColdSnapStabber.customAttributes().build());
        event.put(Register.PCOLDSNAPGUNNER.get(), ColdSnapGunner.customAttributes().build());
        event.put(Register.PCOLDSNAPSNOWBALLER.get(), ColdSnapSnowballer.customAttributes().build());
        event.put(Register.PCOLDSNAPGIFTER.get(), ColdSnapGifter.customAttributes().build());
        event.put(Register.PCOLDSNAPZAPPER.get(), ColdSnapZapper.customAttributes().build());
        event.put(Register.PCOLDSNAPBRAWLER.get(), ColdSnapBrawler.customAttributes().build());

        event.put(Register.NCOLDSNAPSTABBER.get(), ColdSnapStabber.customAttributes().build());
        event.put(Register.NCOLDSNAPGUNNER.get(), ColdSnapGunner.customAttributes().build());
        event.put(Register.NCOLDSNAPSNOWBALLER.get(), ColdSnapSnowballer.customAttributes().build());
        event.put(Register.NCOLDSNAPGIFTER.get(), ColdSnapGifter.customAttributes().build());
        event.put(Register.NCOLDSNAPZAPPER.get(), ColdSnapZapper.customAttributes().build());
        event.put(Register.NCOLDSNAPBRAWLER.get(), ColdSnapBrawler.customAttributes().build());

        event.put(Register.ECOLDSNAPSTABBER.get(), ColdSnapStabber.customAttributes().build());
        event.put(Register.ECOLDSNAPGUNNER.get(), ColdSnapGunner.customAttributes().build());
        event.put(Register.ECOLDSNAPSNOWBALLER.get(), ColdSnapSnowballer.customAttributes().build());
        event.put(Register.ECOLDSNAPGIFTER.get(), ColdSnapGifter.customAttributes().build());
        event.put(Register.ECOLDSNAPZAPPER.get(), ColdSnapZapper.customAttributes().build());
        event.put(Register.ECOLDSNAPBRAWLER.get(), ColdSnapBrawler.customAttributes().build());

    }

}
