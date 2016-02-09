package com.krupa.adrian.PyMayaCharm.console;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentManager;
import com.krupa.adrian.PyMayaCharm.resources.Strings;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.charset.Charset;

public class LogWindow implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        final ContentManager contentManager = toolWindow.getContentManager();
        final ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        final LogConsole console = new LogConsole(project, new File(Strings.LOG_FILE), Charset.defaultCharset(), 0L, "Maya", false, GlobalSearchScope.allScope(project));
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
