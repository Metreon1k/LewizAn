///  Мод написан студией LewizDev
///  Автор: Lewiz Dev (t.me/LewizDev)
///  t.me/LewizDev
///  discord.gg/27s7TMGeHy

package dev.lewiz.an;

public enum AnarchyMode {
    SOLO("Соло", 1, 7, 1, 0),
    DUO("Дуо", 8, 17, 2, 1),
    TRIO("Трио", 18, 24, 3, 2),
    CLAN("Клан", 25, 30, 16, 3);

    public static final int LIST_START = 18;

    public final String label;
    public final int first;
    public final int last;
    public final int teamSize;
    public final int tabSlot;

    AnarchyMode(String label, int first, int last, int teamSize, int tabSlot) {
        this.label = label;
        this.first = first;
        this.last = last;
        this.teamSize = teamSize;
        this.tabSlot = tabSlot;
    }

    public int listSlot(int number) {
        return LIST_START + number - first;
    }

    public static AnarchyMode of(int number) {
        for (AnarchyMode m : values()) {
            if (number >= m.first && number <= m.last) return m;
        }
        return null;
    }
}