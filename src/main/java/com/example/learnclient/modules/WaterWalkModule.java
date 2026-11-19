package com.example.learnclient.modules;

import net.minecraft.client.network.ClientPlayerEntity;

public class WaterWalkModule extends Module {
    public WaterWalkModule() { super("Water Walk", Category.MOVEMENT); }

    @Override public void onTick() {
        ClientPlayerEntity p = mc.player;
        if (p == null || !p.isTouchingWater() || p.isSneaking()) return;
        var v = p.getVelocity();
        p.setVelocity(v.x, Math.max(v.y, 0.12), v.z);
    }
}
