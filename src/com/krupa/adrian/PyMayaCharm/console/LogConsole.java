package com.krupa.adrian.PyMayaCharm.console;

import com.intellij.diagnostic.logging.LogConsoleBase;
import com.intellij.diagnostic.logging.LogFilterModel;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Reader;

public class LogConsole extends LogConsoleBase {
    public LogConsole(@NotNull Project project, @Nullable Reader reader, String title, boolean buildInActions, LogFilterModel model) {
        super(project, reader, title, buildInActions, model);
        setContentPreprocessor(new LogPreProcessor());
    }

    @Override
    public boolean isActive() {
        return true;
    }


}
