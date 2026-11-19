package com.example.learnclient.modules;

/** Переключатель на командах: при включении, выключении и раз в 5 секунд (если заданы). */
public class CommandModule extends Module {
    private final String[] on, off, tick;
    private int ticks;

    public CommandModule(String name, Category category, String[] on, String[] off, String[] tick) {
        super(name, category);
        this.on = on; this.off = off; this.tick = tick;
    }

    @Override protected void onEnable() { for (String c : on) command(c); }
    @Override protected void onDisable() { for (String c : off) command(c); }
    @Override public void onTick() {
        if (tick.length > 0 && ticks++ % 100 == 0) for (String c : tick) command(c);
    }
}
