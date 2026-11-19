package com.example.learnclient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.TreeSet;

import net.fabricmc.loader.api.FabricLoader;

/** Список друзей: Aura и Multi Aura их не трогают. Хранится в config/learnclient/friends.txt */
public final class Friends {
    private static final Set<String> NAMES = new TreeSet<>();
    private Friends() {}

    private static Path file() throws IOException {
        Path d = FabricLoader.getInstance().getConfigDir().resolve("learnclient");
        Files.createDirectories(d);
        return d.resolve("friends.txt");
    }

    public static boolean has(String name) { return name != null && NAMES.contains(name.toLowerCase()); }
    public static boolean add(String name) { boolean r = NAMES.add(name.toLowerCase()); save(); return r; }
    public static boolean remove(String name) { boolean r = NAMES.remove(name.toLowerCase()); save(); return r; }
    public static void clear() { NAMES.clear(); save(); }
    public static Set<String> all() { return NAMES; }

    public static void load() {
        try {
            Path f = file();
            if (Files.exists(f)) for (String l : Files.readAllLines(f, StandardCharsets.UTF_8))
                if (!l.isBlank()) NAMES.add(l.trim().toLowerCase());
        } catch (IOException ignored) {}
    }

    private static void save() {
        try { Files.write(file(), NAMES, StandardCharsets.UTF_8); } catch (IOException ignored) {}
    }
}
