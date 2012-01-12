package org.vaadin.codemirror2.client.ui;

public enum CodeMode {

    TEXT(1, "rst"),
    XML(2, "xml"),
    JAVA(3, "clike"),
    JAVASCRIPT(4, "javascript"),
    CSS(5, "css"),
    SQL(6, "plsql"),
    PHP(7, "php"),
    PYTHON(8, "python"),
    LUA(9, "lua");

    private String mode;
    private int id;

    CodeMode(int id, String parser) {
        this.id = id;
        setMode(parser);
    }

    public static CodeMode byId(int id) {
        for (CodeMode s : CodeMode.values()) {
            if (s.id == id) {
                return s;
            }
        }
        return null;
    }

    private void setMode(String parser) {
        this.mode = parser;
    }

    public String getMode() {
        return mode;
    }

    public int getId() {
        return id;
    }

}
