package com.example.learnclient.modules;

import com.example.learnclient.Friends;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

public class MultiAuraModule extends Module {
    private final Setting range = number("Range", 8.0, 1, 16, 0.5);
    private final Setting targets = number("Targets", 10, 1, 30, 1);
    private final Setting players = bool("Players", true);

    public MultiAuraModule() { super("Multi Aura", Category.COMBAT); }

    @Override public void onTick() {
        ClientPlayerEntity p = mc.player;
        if (p == null || mc.world == null || mc.interactionManager == null) return;
        int hits = 0;
        double r2 = range.get() * range.get();
        for (Entity e : mc.world.getEntities()) {
            if (hits >= targets.getInt()) break;
            if (!(e instanceof LivingEntity le) || e == p || !le.isAlive()) continue;
            if (e instanceof PlayerEntity pe && (!players.getBool() || Friends.has(pe.getName().getString()))) continue;
            if (p.squaredDistanceTo(e) > r2) continue;
            mc.interactionManager.attackEntity(p, e);
            hits++;
        }
        if (hits > 0) p.swingHand(Hand.MAIN_HAND);
    }
}
