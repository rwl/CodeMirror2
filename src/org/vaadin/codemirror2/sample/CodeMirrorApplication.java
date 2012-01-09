package org.vaadin.codemirror2.sample;

import org.vaadin.codemirror2.CodeMirror;
import org.vaadin.codemirror2.client.ui.CodeStyle;

import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Select;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class CodeMirrorApplication extends Application {
    private static final long serialVersionUID = -5399889479559695468L;
    private static final String NL = "\n\n";
    private static final String SAMPLE_CODE = "<xml is=\"fun\"></xml>" + NL
            + "function js(isMoreFun) {alert(\"Yay!\");}" + NL
            + "public void java(String isAlsoCool) {\n\twith(\"Vaadin!\");\n}"
            + NL +"select * from web where you = i;";

    @Override
    public void init() {
        Window mainWindow = new Window("CodeMirror Sample Application");

        GridLayout hl = new GridLayout(2,3);
        hl.setSpacing(true);
        mainWindow.addComponent(hl);
        // # 1
        final CodeMirror code = new CodeMirror("Your Code",
                CodeStyle.XML);
        code.setValue(SAMPLE_CODE);
        code.setWidth("400px");
        code.setHeight("300px");
        hl.addComponent(code);
        // #

        final CodeMirror code2 = new CodeMirror("Your Code Too",
                CodeStyle.XML);
        code2.setValue(SAMPLE_CODE);
        code2.setWidth("400px");
        code2.setHeight("300px");
        hl.addComponent(code2);


        {
            final Select codeStyle = new Select("Select your style");
            for (CodeStyle cs : CodeStyle.values()) {
                codeStyle.addItem(cs);
            }
            codeStyle.setNewItemsAllowed(false);
            codeStyle.setImmediate(true);
            hl.addComponent(codeStyle);

            codeStyle.addListener(new Property.ValueChangeListener() {

                private static final long serialVersionUID = 1L;

                public void valueChange(ValueChangeEvent event) {
                    code.setCodeStyle((CodeStyle) codeStyle.getValue());
                }
            });
            codeStyle.setValue(CodeStyle.XML);
        }

        {
            final Select codeStyle = new Select("Select your style too");
            for (CodeStyle cs : CodeStyle.values()) {
                codeStyle.addItem(cs);
            }
            codeStyle.setNewItemsAllowed(false);
            codeStyle.setImmediate(true);
            hl.addComponent(codeStyle);

            codeStyle.addListener(new Property.ValueChangeListener() {

                private static final long serialVersionUID = 1L;

                public void valueChange(ValueChangeEvent event) {
                    code2.setCodeStyle((CodeStyle) codeStyle.getValue());
                }
            });
            codeStyle.setValue(CodeStyle.JAVA);
        }


        hl.addComponent(new Button("copy to -->", new Button.ClickListener() {
;
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                code2.setValue(code.getValue());
            }
        }));
        hl.addComponent(new Button("<- copy to", new Button.ClickListener() {

            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                code.setValue(code2.getValue());
            }
        }));

        setMainWindow(mainWindow);
    }
}
