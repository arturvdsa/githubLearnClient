package com.example.learnclient.modules;



public class BodyguardsModule extends Module {
    public BodyguardsModule() { super("Bodyguards", Category.MISC); }

    @Override protected void onEnable() {
        String n = me();
        command("execute at " + n + " run summon iron_golem ~3 ~ ~");
        command("execute at " + n + " run summon iron_golem ~-3 ~ ~");
        command("execute at " + n + " run summon iron_golem ~ ~ ~3");
        setEnabled(false); // одноразовая кнопка: 3 железных голема рядом
    }
}
