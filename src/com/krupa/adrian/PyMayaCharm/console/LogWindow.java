package com.krupa.adrian.PyMayaCharm.console;

import com.intellij.diagnostic.logging.DefaultLogFilterModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentManager;
import com.krupa.adrian.PyMayaCharm.ConnectionManager;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LogWindow implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        final ContentManager contentManager = toolWindow.getContentManager();
        final ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();

        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(ConnectionManager.INSTANCE.getPythonSocket().getInputStream()));
            System.out.println("Connected");

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        final LogConsole console = new LogConsole(project, in, "Maya", false, new DefaultLogFilterModel(project));
        final Content content = contentFactory.createContent(console.getComponent(), "", false);
        contentManager.addContent(content);
        toolWindow.setAvailable(true, null);
        toolWindow.setToHideOnEmptyContent(true);
        toolWindow.activate(() -> {
            console.clear();
            console.activate();
        });
    }
}
