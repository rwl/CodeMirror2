package org.vaadin.codemirror2.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.user.client.DOM;

public class CodeMirrorOptionsJSNI extends JavaScriptObject {

  public static final String TAB_MODE_SPACES = "spaces";
  public static final String TAB_MODE_DEFAULT = "default";
  public static final String TAB_MODE_SHIFT = "shift";

  public static CodeMirrorOptionsJSNI newInstance() {
    return JavaScriptObject.createObject().cast();
  }

  protected CodeMirrorOptionsJSNI() {}

  public final native void setValue(String value) /*-{
  	this.value = value;
  }-*/;

  public final native void setMode(String mode) /*-{
  	this.mode = mode;
  }-*/;

  public final native void setMode(JavaScriptObject mode) /*-{
  	this.mode = mode;
  }-*/;

  public final native void setTheme(String theme) /*-{
  	this.theme = theme;
  }-*/;

  public final native void setIndentUnit(int unit) /*-{
  	this.indentUnit = unit;
  }-*/;

  public final native void setSmartIndent(boolean smart) /*-{
  	this.smartIndent = smart;
  }-*/;

  public final native void setTabSize(int size) /*-{
	  this.tabSize = size;
  }-*/;

  public final native void setIndentWithTabs(boolean indentWithTabs) /*-{
  	this.indentWithTabs = indentWithTabs;
  }-*/;

  public final native void setElectricChars(boolean electricChars) /*-{
  	this.electricChars = electricChars;
  }-*/;

  public final native void setKeyMap(String keyMap) /*-{
  	this.keyMap = keyMap;
  }-*/;

  public final native void setExtraKeys(JavaScriptObject extraKeys) /*-{
  	this.extraKeys = extraKeys;
  }-*/;

  public final native void setLineWrapping(boolean lineWrapping) /*-{
  	this.lineWrapping = lineWrapping;
  }-*/;

  public final native void setLineNumbers(boolean ln) /*-{
    this.lineNumbers = ln;
  }-*/;

  public final native void setFirstLineNumber(int firstLineNumber) /*-{
  	this.firstLineNumber = firstLineNumber;
  }-*/;

  public final native void setGutter(boolean gutter) /*-{
  	this.gutter = gutter;
  }-*/;

  public final native void setFixedGutter(boolean fixedGutter) /*-{
  	this.fixedGutter = fixedGutter;
  }-*/;

  public final native void setReadOnly(boolean readOnly) /*-{
        this.readOnly = readOnly;
  }-*/;

  public final native void setChangeCallback(Runnable cb) /*-{
        this.onChange = function() {
                cb.@java.lang.Runnable::run()();
        };
  }-*/;

  public final native void setCursorActivityCallback(Runnable cb) /*-{
        this.onCursorActivity = function() {
                cb.@java.lang.Runnable::run()();
        };
  }-*/;

  public final native void setGutterClickCallback(Runnable cb) /*-{
        this.onGutterClick = function() {
                cb.@java.lang.Runnable::run()();
        };
  }-*/;

  public final native void setFocusCallback(Runnable cb) /*-{
        this.onFocus = function() {
                cb.@java.lang.Runnable::run()();
        };
  }-*/;

  public final native void setBlurCallback(Runnable cb) /*-{
        this.onBlur = function() {
                cb.@java.lang.Runnable::run()();
        };
  }-*/;

  public final native void setScrollCallback(Runnable cb) /*-{
        this.onScroll = function() {
                cb.@java.lang.Runnable::run()();
        };
  }-*/;

  public final native void setHighlightCompleteCallback(Runnable cb) /*-{
        this.onHighlightComplete = function() {
                cb.@java.lang.Runnable::run()();
        };
  }-*/;

  public final native void setUpdateCallback(Runnable cb) /*-{
        this.onUpdate = function() {
                cb.@java.lang.Runnable::run()();
        };
  }-*/;

  public final native void setMatchBrackets(boolean matchBrackets) /*-{
  	this.matchBrackets = matchBrackets;
  }-*/;

  public final native void setWorkTime(int workTime) /*-{
  	this.workTime = workTime;
  }-*/;

  public final native void setWorkDelay(int workDelay) /*-{
  	this.workDelay = workDelay;
  }-*/;

  public final native void setPollInterval(int pollInterval) /*-{
  	this.pollInterval = pollInterval;
  }-*/;

  public final native void setUndoDepth(int undoDepth) /*-{
  	this.undoDepth = undoDepth;
  }-*/;

  public final native void setTabindex(int tabindex) /*-{
  	this.tabindex = tabindex;
  }-*/;

  public final native void setDocument(DOM document) /*-{
  	this.document = document;
  }-*/;

  public final native void setKeyEventCallback(Runnable cb) /*-{
        this.onKeyEvent = function() {
                cb.@java.lang.Runnable::run()();
        };
  }-*/;

}
