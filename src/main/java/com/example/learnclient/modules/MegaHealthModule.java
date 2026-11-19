package com.example.learnclient.modules;

import net.minecraft.entity.attribute.EntityAttributes;

public class MegaHealthModule extends Module {
    private final Setting health = number("Health", 200, 20, 1000, 20);

    public MegaHealthModule() { super("Mega Health", Category.PLAYER); }

    @Override protected void onEnable() {
        double h = health.get();
        onServer(sp -> { setAttr(sp, EntityAttributes.MAX_HEALTH, h); sp.setHealth((float) h); });
    }
    @Override protected void onDisable() {
        onServer(sp -> { setAttr(sp, EntityAttributes.MAX_HEALTH, 20.0); sp.setHealth(Math.min(sp.getHealth(), 20.0f)); });
    }
    @Override protected void onSettingChanged(Setting s) { if (isEnabled()) onEnable(); }
}
