package org.vaadin.codemirror2.client.ui;

public enum CodeTheme {

	DEFAULT(1, "default"),
	COBALT(2, "cobalt"),
	ECLIPSE(3, "eclipse"),
	ELEGANT(4, "elegant"),
	MONOKAI(5, "monokai"),
	NEAT(6, "neat"),
	NIGHT(7, "night"),
	RUBYBLUE(8, "rubyblue");

	private int id;
	private String theme;

	CodeTheme(int id, String theme) {
		this.id = id;
		this.theme = theme;
	}

    public static CodeTheme byId(int id) {
        for (CodeTheme s : CodeTheme.values()) {
            if (s.id == id) {
                return s;
            }
        }
        return null;
    }

    public String getTheme() {
    	return this.theme;
    }

    public int getId() {
    	return this.id;
    }

}
