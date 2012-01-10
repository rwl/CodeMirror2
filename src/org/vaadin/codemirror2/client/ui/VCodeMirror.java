package org.vaadin.codemirror2.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
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

    private CodeMirrorOptionsJSNI options;

    private Util util = new Util("CodeMirror2");

//    private boolean initialized;

    private boolean preventUpdate;

    /**
     * The constructor should first call super() to initialize the component and
     * then handle any initialization relevant to Vaadin.
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

        int cs = uidl.getIntAttribute("codestyle");
        boolean styleChange = codeStyle == null || codeStyle.getId() != cs;
        setCodeStyle(CodeStyle.byId(cs));
        if (styleChange) {
            if (util.debug()) {
                util.d("Style change: " + codeStyle);
            }
            removeCodeMirror();
            initCodeMirror(uidl);
        }
    }

    private void initCodeMirror(UIDL uidl) {
        if (options == null) {
            options = CodeMirrorOptionsJSNI.newInstance();
        }
        optionsFromUIDL(options, uidl);
        cm = CodeMirrorJSNI.fromTextArea(getElement(), options);
        if (util.debug()) {
            util.d("Created: " + util.p(cm));
        }
    }

    private void optionsFromUIDL(CodeMirrorOptionsJSNI ops, UIDL uidl) {
//        String path = GWT.getModuleBaseURL();
//        ops.setPath(path);
//
//        JsArrayString css = evalArrayString("['css/docs.css', '"
//                + codeStyle.getCss() + "']");
//        for (int i = 0; i < css.length(); i++) {
//            String c = css.get(i);
//            if (!c.startsWith("http:") && !c.startsWith("/")) {
//                c = path + c;
//            }
//            css.set(i, c);
//        }
//        ops.setParserFile(evalArrayString(codeStyle.getParser()));
//        ops.setStylesheet(css);

    	ops.setMode(codeStyle.getMode());

        ops.setLineNumbers(uidl.getBooleanAttribute("showLineNumbers"));

//        int scanTimer = uidl.getIntAttribute("scanTimer");
//        ops.setContinuousScanning(scanTimer <= 0 ? "false" : "" + scanTimer);
//        ops.setWidth(width);
//        ops.setHeight(height);
//        ops.setContent("");; //TODO input prompt?

//        ops.setInitCallback(new Runnable() {
//            public void run() {
//                cmInitComplete();
//            }
//        });

        ops.setChangeCallback(new Runnable() {
            public void run() {
                cmUpdateTextArea();
            }
        });

        if (util.debug()) {
            util.d("Options: " + util.p(ops));
        }

    }

//    protected void cmInitComplete() {
//        initialized = cm != null && cm.isInitialized();
//        if (initialized) {
//            util.d("Init complete.");
//            cmUpdateCodeMirror();
//        }
//    }

    private void cmUpdateCodeMirror() {
        preventUpdate = true;
        try {
            if (cm != null) {
                String t = super.getText();
                if (util.debug()) {
                    util.d("updateTextArea " + t);
                }
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
        if (util.debug()) {
            util.d("updateTextArea " + t);
        }
        if (preventUpdate) {
            util.d("Self-initiated change prevented.");
            return;
        }
        super.setText(t);
        util.send("text", t);
    }

    private native static JsArrayString evalArrayString(String arrayStr) /*-{
      return eval(arrayStr);
    }-*/;

    public void setCodeStyle(CodeStyle codeStyle) {
        this.codeStyle = codeStyle;
    }

    public CodeStyle getCodeStyle() {
        return codeStyle;
    }

    private void removeCodeMirror() {
        util.d("Remove.");
        CodeMirrorJSNI toRemove = cm;
        cm = null;

        // Actual removal
        if (toRemove != null) {
            removeCodeMirror(toRemove);
        }
    }

    @Override
    public void setText(String text) {
        if (util.debug()) {
            util.d("setText " + text);
        }
        super.setText(text);
        cmUpdateCodeMirror();
    }

    @Override
    public String getText() {
        if (cm != null) {
            return cm.getValue();
        }
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

    /**
     * Remove CodeMirror from the DOM tree.
     *
     * @param cm
     */
    private native void removeCodeMirror(CodeMirrorJSNI cm)
    /*-{
      if (cm) {
        cm.frame.parentNode.parentNode.removeChild(cm.frame.parentNode);
        cm = null;
      }
    }-*/;
}
