package com.example.learnclient.modules;



public class AlwaysDayModule extends Module {
    public AlwaysDayModule() { super("Always Day", Category.MISC); }

    private int ticks;
    @Override public void onTick() {
        if (ticks++ % 100 == 0) { command("time set day"); command("weather clear"); }
    }
}
