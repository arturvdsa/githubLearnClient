package com.example.learnclient.modules;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;

/** Постоянный эффект зелья (одиночная игра). Настройка Level = уровень эффекта. */
public class EffectModule extends Module {
    private final RegistryEntry<StatusEffect> effect;
    private final Setting level;

    public EffectModule(String name, Category category, RegistryEntry<StatusEffect> effect, int amplifier) {
        super(name, category);
        this.effect = effect;
        this.level = number("Level", amplifier, 0, 50, 1);
    }

    @Override public void onTick() {
        int lv = level.getInt();
        onServer(sp -> sp.addStatusEffect(new StatusEffectInstance(effect, 40, lv, false, false, false)));
    }
    @Override protected void onDisable() { onServer(sp -> sp.removeStatusEffect(effect)); }
}
