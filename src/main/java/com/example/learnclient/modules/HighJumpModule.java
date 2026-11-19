package com.example.learnclient.modules;

import net.minecraft.entity.attribute.EntityAttributes;

public class HighJumpModule extends Module {
    private final Setting power = number("Power", 1.0, 0.42, 3.0, 0.1);

    public HighJumpModule() { super("High Jump", Category.MOVEMENT); }

    @Override protected void onEnable() { setAttr(mc.player, EntityAttributes.JUMP_STRENGTH, power.get()); }
    @Override protected void onDisable() { setAttr(mc.player, EntityAttributes.JUMP_STRENGTH, 0.42); }
    @Override protected void onSettingChanged(Setting s) { if (isEnabled()) onEnable(); }
}
