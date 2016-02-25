package com.krupa.adrian.PyMayaCharm.console;

import com.intellij.diagnostic.logging.LogContentPreprocessor;
import com.intellij.diagnostic.logging.LogFragment;
import com.intellij.execution.process.ProcessOutputTypes;

import java.util.ArrayList;
import java.util.List;

public class LogPreProcessor implements LogContentPreprocessor {
    @Override
    public List<LogFragment> parseLogLine(String s) {
        System.out.println("Processing " + s.length() + ":" + s);
        if ((s.length() < 7 && s.contains("None")) || (s.length() < 3 && s.trim().length() == 0)) {
            return new ArrayList<>();
        }
        final List<LogFragment> lFrag = new ArrayList<>();
        lFrag.add(new LogFragment(s, ProcessOutputTypes.STDOUT));
        return lFrag;
    }
}
