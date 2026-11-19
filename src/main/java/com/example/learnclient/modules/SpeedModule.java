package com.example.learnclient.modules;

import net.minecraft.client.network.ClientPlayerEntity;

public class SpeedModule extends Module {
    private final Setting speed = number("Speed", 0.45, 0.1, 2.0, 0.05);

    public SpeedModule() { super("Speed", Category.MOVEMENT); }

    @Override public void onTick() {
        ClientPlayerEntity p = mc.player;
        if (p == null) return;
        double fwd = (mc.options.forwardKey.isPressed() ? 1 : 0) - (mc.options.backKey.isPressed() ? 1 : 0);
        double str = (mc.options.leftKey.isPressed() ? 1 : 0) - (mc.options.rightKey.isPressed() ? 1 : 0);
        if (fwd == 0 && str == 0) return;
        double r = Math.toRadians(p.getYaw());
        double dx = -Math.sin(r) * fwd + Math.cos(r) * str;
        double dz = Math.cos(r) * fwd + Math.sin(r) * str;
        double len = Math.sqrt(dx * dx + dz * dz);
        var v = p.getVelocity();
        p.setVelocity(dx / len * speed.get(), v.y, dz / len * speed.get());
    }
}
