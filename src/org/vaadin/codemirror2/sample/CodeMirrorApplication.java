package org.vaadin.codemirror2.sample;

import org.vaadin.codemirror2.CodeMirror;
import org.vaadin.codemirror2.client.ui.CodeMode;
import org.vaadin.codemirror2.client.ui.CodeTheme;

import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
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

        GridLayout hl = new GridLayout(2,5);
        hl.setSpacing(true);
        mainWindow.addComponent(hl);
        // # 1
        final CodeMirror code = new CodeMirror("Your Code",
                CodeMode.XML);
        code.setValue(SAMPLE_CODE);
        code.setWidth("600px");
        code.setHeight("600px");
        hl.addComponent(code);

        // # 2
        final CodeMirror code2 = new CodeMirror("Your Code Too",
                CodeMode.XML);
        code2.setValue(SAMPLE_CODE);
//        code2.setWidth("400px");
//        code2.setHeight("300px");
        hl.addComponent(code2);


        {
            final Select codeStyle = new Select("Select your mode");
            for (CodeMode cs : CodeMode.values()) {
                codeStyle.addItem(cs);
            }
            codeStyle.setNewItemsAllowed(false);
            codeStyle.setImmediate(true);
            codeStyle.setNullSelectionAllowed(false);
            hl.addComponent(codeStyle);

            codeStyle.addListener(new Property.ValueChangeListener() {

                private static final long serialVersionUID = 1L;

                public void valueChange(ValueChangeEvent event) {
                    code.setCodeMode((CodeMode) codeStyle.getValue());
                }
            });
            codeStyle.setValue(CodeMode.XML);
        }

        {
            final Select codeStyle = new Select("Select your mode too");
            for (CodeMode cs : CodeMode.values()) {
                codeStyle.addItem(cs);
            }
            codeStyle.setNewItemsAllowed(false);
            codeStyle.setImmediate(true);
            codeStyle.setNullSelectionAllowed(false);
            hl.addComponent(codeStyle);

            codeStyle.addListener(new Property.ValueChangeListener() {

                private static final long serialVersionUID = 1L;

                public void valueChange(ValueChangeEvent event) {
                    code2.setCodeMode((CodeMode) codeStyle.getValue());
                }
            });
            codeStyle.setValue(CodeMode.JAVA);
        }

        {
        	final Select codeTheme = new Select("Select your theme");
        	for (CodeTheme ct : CodeTheme.values()) {
        		codeTheme.addItem(ct);
        	}
        	codeTheme.setNewItemsAllowed(false);
        	codeTheme.setImmediate(true);
            codeTheme.setNullSelectionAllowed(false);
            hl.addComponent(codeTheme);

            codeTheme.addListener(new Property.ValueChangeListener() {

                private static final long serialVersionUID = 1L;

                public void valueChange(ValueChangeEvent event) {
                    code.setCodeTheme((CodeTheme) codeTheme.getValue());
                }
            });
            codeTheme.setValue(CodeTheme.DEFAULT);
        }

        {
        	final Select codeTheme = new Select("Select your theme too");
        	for (CodeTheme ct : CodeTheme.values()) {
        		codeTheme.addItem(ct);
        	}
        	codeTheme.setNewItemsAllowed(false);
        	codeTheme.setImmediate(true);
            codeTheme.setNullSelectionAllowed(false);
            hl.addComponent(codeTheme);

            codeTheme.addListener(new Property.ValueChangeListener() {

                private static final long serialVersionUID = 1L;

                public void valueChange(ValueChangeEvent event) {
                    code2.setCodeTheme((CodeTheme) codeTheme.getValue());
                }
            });
            codeTheme.setValue(CodeTheme.DEFAULT);
        }


        hl.addComponent(new Button("copy to -->", new Button.ClickListener() {

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

        {
        	CheckBox cb = new CheckBox("Show line numbers",
        			new Button.ClickListener() {

	            private static final long serialVersionUID = 1L;

	            public void buttonClick(ClickEvent event) {
	                code.setShowLineNumbers(event.getButton().booleanValue());
	            }
	        });
	        cb.setImmediate(true);
	        hl.addComponent(cb);
        }

        {
        	CheckBox cb = new CheckBox("Show line numbers",
        			new Button.ClickListener() {

	            private static final long serialVersionUID = 1L;

	            public void buttonClick(ClickEvent event) {
	                code2.setShowLineNumbers(event.getButton().booleanValue());
	            }
	        });
	        cb.setImmediate(true);
	        hl.addComponent(cb);
        }

        setMainWindow(mainWindow);
    }
}
