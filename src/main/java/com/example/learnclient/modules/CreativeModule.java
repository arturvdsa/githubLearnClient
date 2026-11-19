package com.example.learnclient.modules;

public class CreativeModule extends Module {
    public CreativeModule() { super("Creative", Category.PLAYER); }

    @Override protected void onEnable() { command("gamemode creative " + me()); }
    @Override protected void onDisable() { command("gamemode survival " + me()); }
}
