package com.krupa.adrian.PyMayaCharm.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.vfs.VirtualFile;
import com.krupa.adrian.PyMayaCharm.MayaConnectionInterface;

import java.io.File;
import java.io.IOException;

public class SendToMayaAction extends MayaAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        final Editor editor = anActionEvent.getData(LangDataKeys.EDITOR);
        if (editor == null)
            return;
        final SelectionModel selectionModel = editor.getSelectionModel();
        MayaConnectionInterface mci = new MayaConnectionInterface("localhost", 6002);
        if (selectionModel.hasSelection()) {
            mci.sendToMaya(selectionModel.getSelectedText());
        } else {
            ApplicationManager.getApplication().saveAll();
            final VirtualFile data = anActionEvent.getData(LangDataKeys.VIRTUAL_FILE);
            if (data != null) {
                try {
                    mci.sendFileToMaya(new File(data.getPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
