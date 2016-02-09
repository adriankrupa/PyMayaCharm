package com.krupa.adrian.PyMayaCharm.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.vfs.VirtualFile;

public abstract class MayaAction extends AnAction {
    private final FileTypeManager fileTypeManager;

    public MayaAction() {
        fileTypeManager = FileTypeManager.getInstance();
    }

    @Override
    public void update(AnActionEvent e) {
        super.update(e);
        VirtualFile currentFile = e.getData(LangDataKeys.VIRTUAL_FILE);
        if (currentFile == null) {
            e.getPresentation().setEnabled(false);
            return;
        }

        if (fileTypeManager.isFileOfType(currentFile, fileTypeManager.getFileTypeByExtension(".py")))
            e.getPresentation().setEnabled(true);
        else
            e.getPresentation().setEnabled(false);
    }
}
