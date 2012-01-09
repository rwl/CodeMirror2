package org.vaadin.codemirror2;

import org.vaadin.codemirror2.client.ui.CodeStyle;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.TextField;

/**
 * Server side component for the VCodeMirrorTextField2 widget.
 */
@com.vaadin.ui.ClientWidget(org.vaadin.codemirror2.client.ui.VCodeMirror.class)
public class CodeMirror extends TextField {

    private static final long serialVersionUID = -7716639260088636858L;

    private CodeStyle codeStyle;
    private boolean showLineNumbers;

    public CodeMirror(String caption) {
        super(caption);
        setCodeStyle(CodeStyle.TEXT);
    }

    public CodeMirror(String caption, CodeStyle codeStyle) {
        super(caption);
        setCodeStyle(codeStyle);
    }

    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        super.paintContent(target);
        target.addAttribute("codestyle", getCodeStyle().getId());
        target.addAttribute("showLineNumbers", isShowLineNumbers());
    }

    public void setCodeStyle(CodeStyle codeStyle) {
        this.codeStyle = codeStyle;
        requestRepaint();
    }

    public CodeStyle getCodeStyle() {
        return codeStyle;
    }

    public void setShowLineNumbers(boolean showLineNumbers) {
        this.showLineNumbers = showLineNumbers;
        requestRepaint();
    }

    public boolean isShowLineNumbers() {
        return showLineNumbers;
    }
}
