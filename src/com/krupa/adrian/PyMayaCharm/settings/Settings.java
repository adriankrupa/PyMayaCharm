package com.krupa.adrian.PyMayaCharm.settings;

import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.util.text.StringUtil;

import javax.swing.*;

public class Settings {
    private final String mayaDefaultString;
    private final SettingsProvider settingsProvider;
    private JPanel myPanel;
    private JTextField hostField;
    private JTextField portField;
    private JTextArea mayaString;

    public Settings(SettingsProvider provider) {
        settingsProvider = provider;
        mayaDefaultString = mayaString.getText();
    }

    public JComponent createPanel() {
        return myPanel;
    }

    public boolean isModified() {
        return getPythonCommandPort() != settingsProvider.getPort() ||
                !getPythonHost().equals(settingsProvider.getHost());
    }

    public int getPythonCommandPort() {
        return StringUtil.parseInt(portField.getText(), -1);
    }

    public void setPythonCommandPort(Integer value) {
        portField.setText(value.toString());
    }

    public String getPythonHost() {
        return hostField.getText();
    }

    public void setPythonHost(String value) {
        hostField.setText(value);
    }

    public void apply() {
        settingsProvider.setPort(getPythonCommandPort());
        settingsProvider.setHost(getPythonHost());
    }

    public void reset() {
        setPythonCommandPort(settingsProvider.getPort());
        setPythonHost(settingsProvider.getHost());
    }
}
