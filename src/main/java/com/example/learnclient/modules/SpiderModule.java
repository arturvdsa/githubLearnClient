package com.example.learnclient.modules;

import net.minecraft.client.network.ClientPlayerEntity;

public class SpiderModule extends Module {
    public SpiderModule() { super("Spider", Category.MOVEMENT); }

    @Override public void onTick() {
        ClientPlayerEntity p = mc.player;
        if (p == null || !p.horizontalCollision) return;
        var v = p.getVelocity();
        p.setVelocity(v.x, 0.25, v.z);
        p.fallDistance = 0;
    }
}
