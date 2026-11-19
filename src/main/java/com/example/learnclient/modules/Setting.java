package com.example.learnclient.modules;

/** Настройка модуля: число (с шагом) или переключатель. Имена — латиницей (их же используют .set и конфиги). */
public class Setting {
    public final String name;
    public final double min, max, step;
    public final boolean isBool;
    private final Module owner;
    private double value;

    Setting(Module owner, String name, double def, double min, double max, double step, boolean isBool) {
        this.owner = owner; this.name = name; this.value = def;
        this.min = min; this.max = max; this.step = step; this.isBool = isBool;
    }

    public double get() { return value; }
    public int getInt() { return (int) Math.round(value); }
    public boolean getBool() { return value >= 0.5; }

    public void set(double v) {
        v = Math.max(min, Math.min(max, v));
        if (!isBool && step > 0) v = Math.round(v / step) * step;
        v = Math.round(v * 1000.0) / 1000.0;
        if (v == value) return;
        value = v;
        owner.onSettingChanged(this);
    }

    /** dir = -1 / +1 */
    public void change(int dir) {
        if (isBool) set(getBool() ? 0 : 1); else set(value + dir * step);
    }

    public String display() {
        if (isBool) return getBool() ? "ON" : "OFF";
        int dec = step >= 1 ? 0 : step >= 0.1 ? 1 : 2;
        return String.format(java.util.Locale.ROOT, "%." + dec + "f", value);
    }
}
