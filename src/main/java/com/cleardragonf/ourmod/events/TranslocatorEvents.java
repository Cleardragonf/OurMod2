package com.cleardragonf.ourmod.events;

import com.cleardragonf.ourmod.items.TranslocatorTuner;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TranslocatorEvents {
    @SubscribeEvent
    public static void onUse(PlayerInteractEvent.RightClickItem event){
        if(!event.getWorld().isClientSide()){
            TranslatableComponent text = new TranslatableComponent("Resetting");
            event.getPlayer().sendMessage(text, event.getPlayer().getUUID());
            event.getItemStack().removeTagKey("energypos");
        }

    }
}
