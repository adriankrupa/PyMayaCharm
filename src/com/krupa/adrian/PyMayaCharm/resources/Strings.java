package com.krupa.adrian.PyMayaCharm.resources;

import com.intellij.openapi.application.PathManager;

import java.text.MessageFormat;

public final class Strings {

    public static final String LOG_FILE = PathManager.getPluginTempPath() + "/mayaLog.txt";
    public static final String CONNECT_MESSAGE = MessageFormat.format("import maya.cmds as cmds; cmds.cmdFileOutput(o=\"{0}\")", LOG_FILE);
    public static final String EXECFILE = "python(\"execfile (\\\"{0}\\\")\")";

    public static final String PY_STD_ERR = "# Error: ";
    public static final String MEL_STD_ERR = "// Error: ";

    public static final String PY_STD_WRN = "# Warning: ";
    public static final String MEL_STD_WRN = "// Warning: ";

    private Strings() {
    }
}
