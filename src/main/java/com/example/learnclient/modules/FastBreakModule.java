package com.example.learnclient.modules;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class FastBreakModule extends Module {
    private final Setting level = number("Level", 9, 0, 50, 1);

    public FastBreakModule() { super("Fast Break", Category.PLAYER); }

    @Override public void onTick() {
        int lv = level.getInt();
        onServer(sp -> sp.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 40, lv, false, false, false)));
    }
    @Override protected void onDisable() { onServer(sp -> sp.removeStatusEffect(StatusEffects.HASTE)); }
}
