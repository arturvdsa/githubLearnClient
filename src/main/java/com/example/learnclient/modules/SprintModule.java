package com.example.learnclient.modules;

import net.minecraft.client.network.ClientPlayerEntity;

public class SprintModule extends Module {
    public SprintModule() { super("Sprint", Category.MOVEMENT); }

    @Override public void onTick() {
        ClientPlayerEntity p = mc.player;
        if (p == null) return;
        if (mc.options.forwardKey.isPressed() && !p.isSneaking() && !p.horizontalCollision) p.setSprinting(true);
    }
}
