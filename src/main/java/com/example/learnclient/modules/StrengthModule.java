package com.example.learnclient.modules;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class StrengthModule extends Module {
    private final Setting level = number("Level", 30, 0, 100, 1);

    public StrengthModule() { super("One Hit", Category.COMBAT); }

    @Override public void onTick() {
        int lv = level.getInt();
        onServer(sp -> sp.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 40, lv, false, false, false)));
    }
    @Override protected void onDisable() { onServer(sp -> sp.removeStatusEffect(StatusEffects.STRENGTH)); }
}
