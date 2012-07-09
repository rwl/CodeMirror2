package org.vaadin.codemirror2.client.common;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Defines cursor location
 */
public class JsCursorLocation extends JavaScriptObject {

    protected JsCursorLocation() {}

    public static native JsCursorLocation create(int line, int ch) /*-{
        return {line: line, ch: ch};
    }-*/;

    public final native int getLine() /*-{
        return this.line;
    }-*/;

    public final native int getChar() /*-{
        return this.ch;
    }-*/;
}
