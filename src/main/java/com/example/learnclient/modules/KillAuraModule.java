package com.example.learnclient.modules;

import com.example.learnclient.Friends;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

public class KillAuraModule extends Module {
    private final Setting range = number("Range", 6.0, 1, 12, 0.5);
    private final Setting players = bool("Players", true);

    public KillAuraModule() { super("Aura", Category.COMBAT); }

    @Override public void onTick() {
        ClientPlayerEntity p = mc.player;
        if (p == null || mc.world == null || mc.interactionManager == null) return;
        if (p.getAttackCooldownProgress(0.5f) < 1.0f) return;
        Entity best = null;
        double bestDist = range.get() * range.get();
        for (Entity e : mc.world.getEntities()) {
            if (!(e instanceof LivingEntity le) || e == p || !le.isAlive()) continue;
            if (e instanceof PlayerEntity pe && (!players.getBool() || Friends.has(pe.getName().getString()))) continue;
            double d = p.squaredDistanceTo(e);
            if (d < bestDist) { bestDist = d; best = e; }
        }
        if (best != null) {
            mc.interactionManager.attackEntity(p, best);
            p.swingHand(Hand.MAIN_HAND);
        }
    }
}
