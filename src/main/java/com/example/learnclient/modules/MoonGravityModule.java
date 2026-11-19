package com.example.learnclient.modules;

import net.minecraft.entity.attribute.EntityAttributes;

public class MoonGravityModule extends Module {
    private final Setting gravity = number("Gravity", 0.02, 0.0, 0.08, 0.01);

    public MoonGravityModule() { super("Moon Gravity", Category.MOVEMENT); }

    @Override protected void onEnable() { setAttr(mc.player, EntityAttributes.GRAVITY, gravity.get()); }
    @Override protected void onDisable() { setAttr(mc.player, EntityAttributes.GRAVITY, 0.08); }
    @Override protected void onSettingChanged(Setting s) { if (isEnabled()) onEnable(); }
}
