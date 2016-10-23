package jp.suesan.enums;

public enum BrowserEnum {
    chrome(1),
    firefox(2),
    internetexplorer(3);

    private final int id;

    private BrowserEnum(final int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
}