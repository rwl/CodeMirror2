package org.vaadin.codemirror2.client.ui;

public enum CodeStyle {

    TEXT(1, "rst", "lib/codemirror.css"),
    XML(2, "'xml", "lib/codemirror.css"),
    JAVA(3, "clike", "lib/codemirror.css"),
    JAVASCRIPT(4, "javascript", "lib/codemirror.css"),
    CSS(5, "css", "lib/codemirror.css"),
    SQL(6, "plsql", "lib/codemirror.css"),
    PHP(7, "php", "lib/codemirror.css"),
    PYTHON(8, "python", "lib/codemirror.css"),
    LUA(9, "lua", "lib/codemirror.css");

    private String mode;
    private String css;
    private int id;

    CodeStyle(int id, String parser, String css) {
        this.id = id;
        setMode(parser);
        setCss(css);
    }

    public static CodeStyle byId(int id) {
        for (CodeStyle s : CodeStyle.values()) {
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

    private void setCss(String css) {
        this.css = css;
    }

    public String getCss() {
        return css;
    }

    public int getId() {
        return id;
    }

}
