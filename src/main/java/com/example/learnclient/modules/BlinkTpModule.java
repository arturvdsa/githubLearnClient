package com.example.learnclient.modules;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class BlinkTpModule extends Module {
    private final Setting distance = number("Distance", 20, 5, 200, 5);

    public BlinkTpModule() { super("Blink TP", Category.MISC); }

    @Override protected void onEnable() {
        ClientPlayerEntity p = mc.player;
        if (p != null) {
            Vec3d look = p.getRotationVec(1.0f);
            double d = distance.get();
            double x = p.getX() + look.x * d, y = p.getY() + look.y * d, z = p.getZ() + look.z * d;
            onServer(sp -> sp.requestTeleport(x, y, z));
        }
        setEnabled(false);
    }
}
