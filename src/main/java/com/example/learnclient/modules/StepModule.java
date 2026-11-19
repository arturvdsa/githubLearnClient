package com.example.learnclient.modules;

import net.minecraft.entity.attribute.EntityAttributes;

public class StepModule extends Module {
    private final Setting height = number("Height", 1.25, 0.6, 5.0, 0.25);

    public StepModule() { super("Step", Category.MOVEMENT); }

    private void setHeight(double h) { setAttr(mc.player, EntityAttributes.STEP_HEIGHT, h); }
    @Override protected void onEnable() { setHeight(height.get()); }
    @Override protected void onDisable() { setHeight(0.6); }
    @Override protected void onSettingChanged(Setting s) { if (isEnabled()) setHeight(height.get()); }
}
