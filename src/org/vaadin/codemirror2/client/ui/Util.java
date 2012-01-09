package org.vaadin.codemirror2.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.vaadin.terminal.gwt.client.ApplicationConnection;

public class Util {

    private static final String DEFAULT_DEBUG_ID = "Util";
    private String debugId;
    private ApplicationConnection client;
    private String id;
    private boolean immediate;

    public Util() {
        this(DEFAULT_DEBUG_ID);
    }

    public Util(String defaultDebugId) {
        debugId = defaultDebugId;
    }

    public static boolean isDebug() {
        return ApplicationConnection.isDebugMode();
    }

    public static void log(String msg) {
        ApplicationConnection.getConsole().log(msg);
    }

    public boolean debug() {
        return isDebug();
    }

    public void d(String msg) {
        if (debug()) {
            debug(msg);
        }
    }

    public void debug(String msg) {
        debug(debugId,id, msg);
    }

    private void debug(String debugId, String instanceId, String msg) {
        log("[" + debugId + ":"+instanceId+"] " + msg);
    }


    public void setClient(ApplicationConnection client) {
        this.client = client;
    }

    public ApplicationConnection getClient() {
        return client;
    }

    public void setId(String id) {
        this.id = id;
        if (debugId.equals(DEFAULT_DEBUG_ID)) {
            debugId = id;
        }
    }

    public String getId() {
        return id;
    }

    public void setImmediate(boolean immediate) {
        this.immediate = immediate;
    }

    public boolean isImmediate() {
        return immediate;
    }

    public void send(String name, String value) {
        getClient().updateVariable(getId(), name, value, isImmediate());
    }

    public void send(String name, String value, boolean now) {
        d("Send "+name+" = "+value);
        getClient().updateVariable(getId(), name, value, now);
    }

    public native String p(JavaScriptObject obj) /*-{
        var s = "";
        for (var i in obj) {
            if (typeof obj[i] != "function") {
                s += i + " = " + obj[i]+ "\n";
            } else {
                s += i + " = [function]\n";
            }
        }
        return s;
    }-*/;



}
