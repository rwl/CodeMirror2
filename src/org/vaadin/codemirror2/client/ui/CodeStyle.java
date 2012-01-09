package org.vaadin.codemirror2.client.ui;

public enum CodeStyle {

    TEXT(1, "'mode/rst/rst.js'", "lib/codemirror.css"),
    XML(2, "'mode/xml/xml.js'", "lib/codemirror.css"),
    JAVA(3, "'mode/clike/clike.js'", "lib/codemirror.css"),
    JAVASCRIPT(4, "'mode/javascript/javascript.js'", "lib/codemirror.css"),
    CSS(5, "'mode/css/css.js'", "lib/codemirror.css"),
    SQL(6, "'mode/plsql/plsql.js'", "lib/codemirror.css"),
    PHP(7, "'mode/php/php.js'", "lib/codemirror.css"),
    PYTHON(8, "'mode/python/python.js'", "lib/codemirror.css"),
    LUA(9, "'mode/lua/lua.js'", "lib/codemirror.css");

    private String parser;
    private String css;
    private int id;

    CodeStyle(int id, String parser, String css) {
        this.id = id;
        setParser(parser);
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

    private void setParser(String parser) {
        this.parser = parser;
    }

    public String getParser() {
        return parser;
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
