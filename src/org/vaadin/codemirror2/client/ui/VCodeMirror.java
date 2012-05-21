package org.vaadin.codemirror2.client.ui;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.DOM;
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

    protected CodeMode codeMode;
    protected boolean showLineNumbers;
    protected boolean readOnly;
    protected boolean noCursor;
    protected boolean smartIndent;
    protected int indentUnit;
    protected int tabSize;
    protected CodeTheme codeTheme;

    protected String height;
    protected String width;
    protected CodeMirrorJSNI codeMirrorJSNI;
    protected Util util = new Util("CodeMirror2");
    protected boolean preventUpdate;

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

        if (codeMirrorJSNI == null)
        	initCodeMirror();

        // Code mode
        int cs = uidl.getIntAttribute("codeMode");
        boolean styleChange = codeMode == null || codeMode.getId() != cs;

        if (styleChange) {
            codeMode = CodeMode.byId(cs);

            if (util.debug())
                util.d("Style change: " + codeMode.getMode());

        	codeMirrorJSNI.setOption("mode", codeMode.getMode());
        }

        // Show line numbers
        boolean sln = uidl.getBooleanAttribute("showLineNumbers");
        boolean showChange = showLineNumbers != sln;

        if (showChange) {
        	showLineNumbers = sln;

	        if (util.debug())
	            util.d("Show line numbers: " + sln);

	        codeMirrorJSNI.setOption("lineNumbers", sln);
        }

        // Code theme
        int ct = uidl.getIntAttribute("codeTheme");
        boolean themeChange = codeTheme == null || codeTheme.getId() != ct;

        if (themeChange) {
        	codeTheme = CodeTheme.byId(ct);

        	if (util.debug())
        		util.d("Theme change: " + codeTheme.getTheme());

        	codeMirrorJSNI.setOption("theme", codeTheme.getTheme());
        }

        // Read only & noCursor
        boolean ro = uidl.getBooleanAttribute("readOnly");
        boolean nc = uidl.getBooleanAttribute("noCursor");
        boolean roChanged = ro != readOnly || nc != noCursor;

        if (roChanged) {
            readOnly = ro;
            noCursor = nc;

            if (util.debug()) {
                util.d("ReadOnly: " + readOnly);
                util.d("NoCursor readOnly: " + noCursor);
    }

            if (noCursor)
                codeMirrorJSNI.setOption("readOnly", "nocursor");
            else
                codeMirrorJSNI.setOption("readOnly", readOnly);
        }

        // Smart indent
        boolean si = uidl.getBooleanAttribute("smartIndent");
        boolean siChanged = si != smartIndent;

        if (siChanged) {
            smartIndent = si;

            if (util.debug()) {
                util.d("Smart indent: " + smartIndent);
            }
            codeMirrorJSNI.setOption("smartIndent", smartIndent);
        }

        //Indent unit & tab size
        int iu = uidl.getIntAttribute("indentUnit");
        int ts = uidl.getIntAttribute("tabSize");
        boolean indentsChanged = iu != indentUnit || ts != tabSize;
        if (indentsChanged) {
            indentUnit = iu;
            tabSize = ts;

            if (util.debug()) {
                util.d("Indent size: " + indentUnit);
                util.d("Tab size: " + tabSize);
            }
            codeMirrorJSNI.setOption("indentUnit", indentUnit);
            codeMirrorJSNI.setOption("tabSize", tabSize);
        }
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

        codeMirrorJSNI = CodeMirrorJSNI.fromTextArea(getElement(), options);

        if (height != null)
        	setHeight(height);

        if (width != null)
        	setWidth(width);

        if (util.debug())
            util.d("Created: " + util.p(codeMirrorJSNI));
    }

    private void cmUpdateCodeMirror() {
        preventUpdate = true;
        try {
            if (codeMirrorJSNI != null) {
                String t = super.getText();
                if (util.debug())
                    util.d("updateTextArea " + t);

                try {
                    codeMirrorJSNI.setValue(t);
                } catch (Exception e) {
                    util.d("updateTextArea failed: " + e);
                }
            }
        } finally {
            preventUpdate = false;
        }
    }

    private void cmUpdateTextArea() {
        String t = codeMirrorJSNI.getValue();
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
        if (codeMirrorJSNI != null)
            return codeMirrorJSNI.getValue();

        return super.getText();
    }

    @Override
    public void setHeight(String height) {
        this.height = height;
        super.setHeight(height);
        if (codeMirrorJSNI != null) {
            Element scroller = codeMirrorJSNI.getScrollerElement();
            DOM.setStyleAttribute(scroller, "height", height);
            codeMirrorJSNI.refresh();
        }
    }

    @Override
    public void setWidth(String width) {
        this.width = width;
        super.setWidth(width);
        if (codeMirrorJSNI != null) {
            Element scroller = codeMirrorJSNI.getScrollerElement();
            DOM.setStyleAttribute(scroller, "width", width);
            codeMirrorJSNI.refresh();
        }
    }
}
