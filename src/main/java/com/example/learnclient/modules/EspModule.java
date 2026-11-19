package com.example.learnclient.modules;

import net.minecraft.entity.Entity;

public class EspModule extends Module {
    public EspModule() { super("Entity ESP", Category.RENDER); }

    @Override public void onTick() {
        if (mc.world == null) return;
        for (Entity e : mc.world.getEntities()) if (e != mc.player) e.setGlowing(true);
    }
    @Override protected void onDisable() {
        if (mc.world == null) return;
        for (Entity e : mc.world.getEntities()) if (e != mc.player) e.setGlowing(false);
    }
}
