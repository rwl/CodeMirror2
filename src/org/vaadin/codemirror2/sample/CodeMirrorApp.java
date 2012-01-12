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
import com.vaadin.ui.Label;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class CodeMirrorApp extends Application {

    private static final long serialVersionUID = -5399889479559695468L;

    private static final String NL = "\n\n";

    private static final String SAMPLE_CODE = "<xml is=\"fun\"></xml>" + NL
            + "function js(isMoreFun) {alert(\"Yay!\");}" + NL
            + "public void java(String isAlsoCool) {\n\twith(\"Vaadin!\");\n}"
            + NL +"select * from web where you = i;";

    @Override
    public void init() {
        Window mainWindow = new Window("CodeMirror Sample Application");
        setMainWindow(mainWindow);

        CodeMirror code = new CodeMirror("Your Code", CodeMode.XML);
        code.setValue(SAMPLE_CODE);
//        code.setSizeFull();
        code.setWidth("600px");
        code.setHeight("600px");
        mainWindow.addComponent(code);

//        Label l = new Label("foo");
//        mainWindow.addComponent(l);

//        TextArea ta = new TextArea();
//        ta.setWidth("600px");
//        ta.setHeight("600px");
//        mainWindow.addComponent(ta);
    }

}
