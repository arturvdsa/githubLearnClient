package com.example.learnclient.modules;

import net.minecraft.client.network.ClientPlayerEntity;

public class FlyModule extends Module {
    private final Setting horizontal = number("Speed", 0.9, 0.1, 3.0, 0.1);
    private final Setting vertical = number("Vertical", 0.6, 0.1, 2.0, 0.1);

    public FlyModule() { super("Fly", Category.MOVEMENT); }

    @Override public void onTick() {
        ClientPlayerEntity p = mc.player;
        if (p == null) return;
        double vy = 0;
        if (mc.options.jumpKey.isPressed()) vy = vertical.get();
        if (mc.options.sneakKey.isPressed()) vy = -vertical.get();
        double fwd = (mc.options.forwardKey.isPressed() ? 1 : 0) - (mc.options.backKey.isPressed() ? 1 : 0);
        double str = (mc.options.leftKey.isPressed() ? 1 : 0) - (mc.options.rightKey.isPressed() ? 1 : 0);
        double vx = 0, vz = 0;
        if (fwd != 0 || str != 0) {
            double r = Math.toRadians(p.getYaw());
            double dx = -Math.sin(r) * fwd + Math.cos(r) * str;
            double dz = Math.cos(r) * fwd + Math.sin(r) * str;
            double len = Math.sqrt(dx * dx + dz * dz);
            vx = dx / len * horizontal.get();
            vz = dz / len * horizontal.get();
        }
        p.setVelocity(vx, vy, vz);
        p.fallDistance = 0;
    }
}
