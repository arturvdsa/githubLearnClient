package com.example.learnclient.modules;

import net.minecraft.client.network.ClientPlayerEntity;

public class BunnyHopModule extends Module {
    public BunnyHopModule() { super("Bunny Hop", Category.MOVEMENT); }

    @Override public void onTick() {
        ClientPlayerEntity p = mc.player;
        if (p == null || !p.isOnGround() || p.isSneaking()) return;
        boolean moving = mc.options.forwardKey.isPressed() || mc.options.backKey.isPressed()
                || mc.options.leftKey.isPressed() || mc.options.rightKey.isPressed();
        if (!moving) return;
        var v = p.getVelocity();
        p.setVelocity(v.x, 0.42, v.z);
    }
}
