package com.krupa.adrian.PyMayaCharm.console;

import com.intellij.diagnostic.logging.LogContentPreprocessor;
import com.intellij.diagnostic.logging.LogFragment;
import com.intellij.execution.process.ProcessOutputTypes;
import com.intellij.openapi.util.Key;
import com.krupa.adrian.PyMayaCharm.resources.Strings;

import java.util.ArrayList;
import java.util.List;

public class LogPreProcessor implements LogContentPreprocessor {
    @Override
    public List<LogFragment> parseLogLine(String s) {
        final List<LogFragment> lFrag = new ArrayList<>();
        boolean checks = s.startsWith(Strings.PY_STD_ERR) || s.startsWith(Strings.MEL_STD_ERR) ||
                s.startsWith(Strings.MEL_STD_WRN) || s.startsWith(Strings.PY_STD_WRN);
        Key outType = (checks) ? ProcessOutputTypes.STDERR : ProcessOutputTypes.STDOUT;
        lFrag.add(new LogFragment(s, outType));
        return lFrag;
    }
}
