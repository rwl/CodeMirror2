package org.vaadin.codemirror2.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;

public class CodeMirrorJSNI extends JavaScriptObject {

  protected CodeMirrorJSNI() { }

  public native static CodeMirrorJSNI newInstance(Element parent, CodeMirrorOptionsJSNI pars) /*-{
    return new $wnd.CodeMirror(parent, pars);
  }-*/;

  public native static CodeMirrorJSNI fromTextArea(Element textarea, CodeMirrorOptionsJSNI pars) /*-{
    return $wnd.CodeMirror.fromTextArea(textarea, pars);
  }-*/;

  public native static CodeMirrorJSNI fromTextArea(Element textarea) /*-{
    return $wnd.CodeMirror.fromTextArea(textarea);
  }-*/;

  public final native String getValue() /*-{
    return this.getValue();
  }-*/;

  public final native void setValue(String code) /*-{
	this.setValue(code);
  }-*/;

  public final native String getSelection() /*-{
	return this.getSelection();
  }-*/;

  public final native void replaceSelection(String text) /*-{
	this.replaceSelection(text);
  }-*/;

  public final native void focus() /*-{
	this.focus();
  }-*/;

  public final native void setOption(String option, JavaScriptObject value) /*-{
  	this.setOption(option, value);
  }-*/;

  public final native void setOption(String option, String value) /*-{
  	this.setOption(option, value);
  }-*/;

  public final native void setOption(String option, boolean value) /*-{
  	this.setOption(option, value);
  }-*/;

  public final native void setOption(String option, int value) /*-{
  	this.setOption(option, value);
  }-*/;

  public final native JavaScriptObject getOption(String option) /*-{
  	return this.getOption(option);
  }-*/;

  public final native JavaScriptObject cursorCoords(boolean start) /*-{
    return this.cursorCoords(start);
  }-*/;

  public final native JavaScriptObject charCoords(JavaScriptObject pos) /*-{
    return this.charCoords(pos);
  }-*/;

  public final native JavaScriptObject coordsChar(JavaScriptObject pos) /*-{
    return this.coordsChar(pos);
  }-*/;

  public final native void undo() /*-{
    this.undo();
  }-*/;

  public final native void redo() /*-{
    this.redo();
  }-*/;

  public final native JavaScriptObject historySize() /*-{
    return this.historySize();
  }-*/;

  public final native void clearHistory() /*-{
    this.clearHistory();
  }-*/;

  public final native void indentLine(int line, boolean dir) /*-{
  	this.indentLine(line, dir);
  }-*/;

  public final native JavaScriptObject getTokenAt(JavaScriptObject pos) /*-{
  	return this.getTokenAt(pos);
  }-*/;

  public final native JavaScriptObject markText(JavaScriptObject from,
		  JavaScriptObject to, String className) /*-{
	return this.markText(from, to, className);
  }-*/;

  public final native JavaScriptObject setBookmark(JavaScriptObject pos) /*-{
  	return this.setBookmark(pos);
  }-*/;

  public final native JavaScriptObject setMarker(int line, String text,
		  String className) /*-{
	return this.setMarker(line, text, className);
  }-*/;

  public final native void clearMarker(int line) /*-{
  	this.clearMarker(line);
  }-*/;

  public final native JavaScriptObject setLineClass(int line,
		  String className) /*-{
	return this.setLineClass(line, className);
  }-*/;

  public final native JavaScriptObject hideLine(int line) /*-{
  	return this.hideLine(line);
  }-*/;

  public final native JavaScriptObject showLine(int line) /*-{
  	return this.showLine(line);
  }-*/;

  public final native void onDeleteLine(int line, Runnable func) /*-{
  	this.onDeleteLine(line, func);
  }-*/;

  public final native JavaScriptObject lineInfo(int line) /*-{
  	return this.lineInfo(line);
  }-*/;

  public final native JavaScriptObject getLineHandle(int num) /*-{
  	return this.getLineHandle(num);
  }-*/;

  public final native void matchBrackets() /*-{
  	this.matchBrackets();
  }-*/;

  public final native int lineCount() /*-{
  	return this.lineCount();
  }-*/;

  public final native JavaScriptObject getCursor(boolean start) /*-{
  	return this.getCursor(start);
  }-*/;

  public final native boolean somethingSelected() /*-{
  	return this.somethingSelected();
  }-*/;

  public final native void setCursor(JavaScriptObject pos) /*-{
  	this.setCursor(pos);
  }-*/;

  public final native void setSelection(JavaScriptObject start,
		  JavaScriptObject end) /*-{
	this.setSelection(start, end);
  }-*/;

  public final native String getLine(int n) /*-{
	return this.getLine(n);
  }-*/;

  public final native void setLine(int n, String text) /*-{
  	this.setLine(n, text);
  }-*/;

  public final native void removeLine(int n) /*-{
  	this.removeLine(n);
  }-*/;

  public final native String getRange(JavaScriptObject from,
		  JavaScriptObject to) /*-{
	this.getRange(from, to);
  }-*/;

  public final native void replaceRange(String string, JavaScriptObject from,
		  JavaScriptObject to) /*-{
	this.replaceRange(string, from, to);
  }-*/;

  public final native JavaScriptObject posFromIndex(int index) /*-{
  	return this.posFromIndex(index);
  }-*/;

  public final native int indexFromPos(JavaScriptObject obj) /*-{
  	return this.indexFromPos(obj);
  }-*/;

  public final native void refresh() /*-{
  	return this.refresh();
  }-*/;

  public final native Element getScrollerElement() /*-{
  	return this.getScrollerElement();
  }-*/;

}
