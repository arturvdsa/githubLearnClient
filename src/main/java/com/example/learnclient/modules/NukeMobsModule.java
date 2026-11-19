package com.example.learnclient.modules;

public class NukeMobsModule extends Module {
    private final Setting radius = number("Radius", 80, 10, 300, 10);

    public NukeMobsModule() { super("Nuke Mobs", Category.MISC); }

    @Override protected void onEnable() {
        command("execute at " + me() + " run kill @e[type=!player,distance=.." + radius.getInt() + "]");
        setEnabled(false);
    }
}
