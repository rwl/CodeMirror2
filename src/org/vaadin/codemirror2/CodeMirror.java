package org.vaadin.codemirror2;

import org.vaadin.codemirror2.client.ui.CodeMode;
import org.vaadin.codemirror2.client.ui.CodeTheme;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.TextField;

/**
 * Server side component for the VCodeMirrorTextField2 widget.
 */
@com.vaadin.ui.ClientWidget(org.vaadin.codemirror2.client.ui.VCodeMirror.class)
public class CodeMirror extends TextField {

    private static final long serialVersionUID = -7716639260088636858L;

    protected CodeMode codeMode = CodeMode.TEXT;
    protected CodeTheme codeTheme = CodeTheme.DEFAULT;
    protected boolean showLineNumbers = false;
    protected boolean readOnly = false;
    protected boolean noCursor = false;
    protected boolean smartIndent = true;
    protected int indentUnit = 2;
    protected int tabSize = 4;

    public CodeMirror(String caption) {
        super(caption);
        setCodeMode(CodeMode.TEXT);
    }

    public CodeMirror(String caption, CodeMode codeStyle) {
        super(caption);
        setCodeMode(codeStyle);
    }

    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        super.paintContent(target);

        if (getCodeMode() != null)
        	target.addAttribute("codeMode", getCodeMode().getId());

        target.addAttribute("showLineNumbers", isShowLineNumbers());

        if (getCodeTheme() != null)
        	target.addAttribute("codeTheme", getCodeTheme().getId());

        target.addAttribute("readOnly", readOnly);
        target.addAttribute("noCursor", noCursor);
        target.addAttribute("smartIndent", smartIndent);
        target.addAttribute("indentUnit", indentUnit);
        target.addAttribute("tabSize", tabSize);
    }

    public void setCodeMode(CodeMode codeMode) {
        this.codeMode = codeMode;
        requestRepaint();
    }

    public CodeMode getCodeMode() {
        return codeMode;
    }

    public void setShowLineNumbers(boolean showLineNumbers) {
        this.showLineNumbers = showLineNumbers;
        requestRepaint();
    }

    public boolean isShowLineNumbers() {
        return showLineNumbers;
    }

	public void setCodeTheme(CodeTheme codeTheme) {
		this.codeTheme = codeTheme;
        requestRepaint();
	}

	public CodeTheme getCodeTheme() {
		return codeTheme;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isNocursor() {
        return noCursor;
    }

    public void setNocursor(boolean noCursor) {
        this.noCursor = noCursor;
    }

    public boolean isSmartIndent() {
        return smartIndent;
    }

    public void setSmartIndent(boolean smartIndent) {
        this.smartIndent = smartIndent;
    }

    public int getIndentUnit() {
        return indentUnit;
    }

    public void setIndentUnit(int indentUnit) {
        this.indentUnit = indentUnit;
    }

    public int getTabSize() {
        return tabSize;
    }

    public void setTabSize(int tabSize) {
        this.tabSize = tabSize;
	}
}
