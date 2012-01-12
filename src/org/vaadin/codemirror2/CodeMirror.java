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

    private CodeMode codeMode = CodeMode.TEXT;
    private boolean showLineNumbers = false;
    private CodeTheme codeTheme = CodeTheme.DEFAULT;

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
}
