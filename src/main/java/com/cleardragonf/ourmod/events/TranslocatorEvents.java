package com.cleardragonf.ourmod.events;

import com.cleardragonf.ourmod.items.TranslocatorTuner;
import com.cleardragonf.ourmod.setup.Registration;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TranslocatorEvents {
    @SubscribeEvent
    public static void onUse(PlayerInteractEvent.RightClickItem event){
        if(!event.getWorld().isClientSide()){
            if(event.getItemStack().is(Registration.TRANSLOCATOR_TUNER.get())){
                TranslatableComponent text = new TranslatableComponent("Resetting");
                event.getPlayer().sendMessage(text, event.getPlayer().getUUID());
                event.getItemStack().removeTagKey("energypos");
            }

        }

    }
}
