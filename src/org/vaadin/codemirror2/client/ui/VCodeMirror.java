package org.vaadin.codemirror2.client.ui;

import com.google.gwt.dom.client.Element;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.UIDL;
import com.vaadin.terminal.gwt.client.ui.VTextArea;

/**
 * CodeMirror client side integration widget.
 *
 */
public class VCodeMirror extends VTextArea {

    /** Set the CSS class name to allow styling. */
    public static final String CLASSNAME = "v-codemirror";

    private CodeStyle codeStyle;

    private String height;

    private String width;

    private CodeMirrorJSNI cm;

    private Util util = new Util("CodeMirror2");

    private boolean preventUpdate;

    /**
     * The constructor should first call super() to initialize the component
     * and then handle any initialization relevant to Vaadin.
     */
    public VCodeMirror() {
        super();
        setStyleName(CLASSNAME);
    }

    /**
     * Called whenever an update is received from the server
     */
    @Override
    public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
        super.updateFromUIDL(uidl, client);

        // Generic component stuff using a helper util
        util.setClient(client);
        util.setId(uidl.getId());
        util.setImmediate(uidl.getBooleanAttribute("immediate"));

        if (cm == null)
        	initCodeMirror();

        int cs = uidl.getIntAttribute("codestyle");
        boolean styleChange = codeStyle == null || codeStyle.getId() != cs;

        codeStyle = CodeStyle.byId(cs);

        if (styleChange) {
            if (util.debug())
                util.d("Style change: " + codeStyle.getMode());

        	cm.setOption("mode", codeStyle.getMode());
        }

        boolean sln = uidl.getBooleanAttribute("showLineNumbers");

        if (util.debug())
            util.d("Show line numbers: " + sln);

        cm.setOption("lineNumbers", sln);
    }

    private void initCodeMirror() {
    	CodeMirrorOptionsJSNI options = CodeMirrorOptionsJSNI.newInstance();

        options.setChangeCallback(new Runnable() {
            public void run() {
                cmUpdateTextArea();
            }
        });

        if (util.debug())
            util.d("Options: " + util.p(options));

        cm = CodeMirrorJSNI.fromTextArea(getElement(), options);

        if (util.debug())
            util.d("Created: " + util.p(cm));
    }

    private void cmUpdateCodeMirror() {
        preventUpdate = true;
        try {
            if (cm != null) {
                String t = super.getText();
                if (util.debug())
                    util.d("updateTextArea " + t);

                try {
                    cm.setValue(t);
                } catch (Exception e) {
                    util.d("updateTextArea failed: " + e);
                }
            }
        } finally {
            preventUpdate = false;
        }
    }

    private void cmUpdateTextArea() {
        String t = cm.getValue();
        if (util.debug())
            util.d("updateTextArea " + t);

        if (preventUpdate) {
            util.d("Self-initiated change prevented.");
            return;
        }
        super.setText(t);
        util.send("text", t);
    }

    @Override
    public void setText(String text) {
        if (util.debug())
            util.d("setText " + text);

        super.setText(text);
        cmUpdateCodeMirror();
    }

    @Override
    public String getText() {
        if (cm != null)
            return cm.getValue();

        return super.getText();
    }

    @Override
    public void setHeight(String height) {
        this.height = height;
        super.setHeight(height);
        if (cm != null) {
            Element scroller = cm.getScrollerElement();
            scroller.setPropertyString("height", height);
            cm.refresh();
        }
    }

    @Override
    public void setWidth(String width) {
        this.width = width;
        super.setWidth(width);
        if (cm != null) {
            Element scroller = cm.getScrollerElement();
            scroller.setPropertyString("width", width);
            cm.refresh();
        }
    }
}
