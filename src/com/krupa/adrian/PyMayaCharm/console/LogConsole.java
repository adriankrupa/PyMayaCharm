package com.krupa.adrian.PyMayaCharm.console;

import com.intellij.diagnostic.logging.LogConsoleImpl;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.GlobalSearchScope;
import com.krupa.adrian.PyMayaCharm.resources.Strings;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

public class LogConsole extends LogConsoleImpl {
    public LogConsole(Project project, @NotNull File file, @NotNull Charset charset, long skippedContents, @NotNull String title, boolean buildInActions, GlobalSearchScope searchScope) {
        super(project, file, charset, skippedContents, title, buildInActions, searchScope);
        setContentPreprocessor(new LogPreProcessor());
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public synchronized void clear() {
        super.clear();
        final String mayaLogPath = Strings.LOG_FILE;
        try {
            PrintWriter writer = new PrintWriter(mayaLogPath);
            writer.print("");
            writer.close();
            System.out.println("Clear Log File");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
