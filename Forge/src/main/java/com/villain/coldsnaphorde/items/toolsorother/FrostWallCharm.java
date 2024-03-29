package com.villain.coldsnaphorde.items.toolsorother;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.villain.coldsnaphorde.items.Tier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
import java.util.UUID;

public class FrostWallCharm extends Item implements ICurioItem {
    Tier tier;
    public FrostWallCharm(Properties p_41383_, Tier tier) {
        super(p_41383_);
        this.tier = tier;
    }

    public Tier getTier() {
        return tier;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> atts = LinkedHashMultimap.create();
        switch (tier) {
            default -> {
                atts.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "knockback_res", 0.1, AttributeModifier.Operation.ADDITION));
            }
            case TWO -> {
                atts.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "knockback_res", 0.2, AttributeModifier.Operation.ADDITION));
            }
            case THREE -> {
                atts.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "knockback_res", 0.35, AttributeModifier.Operation.ADDITION));
            }
        }
        return atts;
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(new TranslatableComponent("itemtooltip.wallcharm.1").withStyle(ChatFormatting.AQUA));
        switch (tier) {
            default -> {
                p_41423_.add(new TranslatableComponent("itemtooltip.coldsnaphorde.tier.1").withStyle(ChatFormatting.AQUA));
            }
            case TWO -> {
                p_41423_.add(new TranslatableComponent("itemtooltip.coldsnaphorde.tier.2").withStyle(ChatFormatting.AQUA));
            }
            case THREE -> {
                p_41423_.add(new TranslatableComponent("itemtooltip.coldsnaphorde.tier.3").withStyle(ChatFormatting.AQUA));
            }
        }
    }
}
