package com.example.learnclient.modules;

import net.minecraft.entity.attribute.EntityAttributes;

public class GiantModule extends Module {
    private final Setting scale = number("Scale", 4.0, 0.1, 16.0, 0.1);

    public GiantModule() { super("Giant", Category.PLAYER); }

    @Override protected void onEnable() { double v = scale.get(); onServer(sp -> setAttr(sp, EntityAttributes.SCALE, v)); }
    @Override protected void onDisable() { onServer(sp -> setAttr(sp, EntityAttributes.SCALE, 1.0)); }
    @Override protected void onSettingChanged(Setting s) { if (isEnabled()) onEnable(); }
}
