package com.example.learnclient.modules;


public class GodModeModule extends Module {
    public GodModeModule() { super("God Mode", Category.PLAYER); }

    @Override protected void onEnable() { onServer(sp -> sp.setInvulnerable(true)); }
    @Override protected void onDisable() { onServer(sp -> sp.setInvulnerable(false)); }
    @Override public void onTick() {
        onServer(sp -> {
            sp.setHealth(sp.getMaxHealth());
            sp.getHungerManager().setFoodLevel(20);
            sp.getHungerManager().setSaturationLevel(20.0f);
            sp.setAir(sp.getMaxAir());
            sp.extinguish();
        });
    }
}
