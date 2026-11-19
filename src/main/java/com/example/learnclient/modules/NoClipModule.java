package com.example.learnclient.modules;

public class NoClipModule extends Module {
    public NoClipModule() { super("NoClip", Category.PLAYER); }

    // Режим наблюдателя: летаешь сквозь блоки и стены (одиночная игра).
    @Override protected void onEnable() { command("gamemode spectator " + me()); }
    @Override protected void onDisable() { command("gamemode survival " + me()); }
}
