package com.example.learnclient.modules;

import net.minecraft.entity.attribute.EntityAttributes;

public class NoFallModule extends Module {
    public NoFallModule() { super("No Fall", Category.MOVEMENT); }

    @Override protected void onEnable() { onServer(sp -> setAttr(sp, EntityAttributes.SAFE_FALL_DISTANCE, 1024.0)); }
    @Override protected void onDisable() { onServer(sp -> setAttr(sp, EntityAttributes.SAFE_FALL_DISTANCE, 3.0)); }
}
