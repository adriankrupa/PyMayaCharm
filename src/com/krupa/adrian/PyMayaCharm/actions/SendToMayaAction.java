package com.krupa.adrian.PyMayaCharm.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.vfs.VirtualFile;
import com.krupa.adrian.PyMayaCharm.MayaConnectionInterface;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

class TextTransferable implements Transferable {

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[0];
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return false;
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        return null;
    }
}

public class SendToMayaAction extends MayaAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        final Editor editor = anActionEvent.getData(LangDataKeys.EDITOR);
        if (editor == null)
            return;
        final SelectionModel selectionModel = editor.getSelectionModel();

        MayaConnectionInterface mci = new MayaConnectionInterface();
        if (selectionModel.hasSelection()) {
            mci.sendToMaya(selectionModel.getSelectedText());
        } else {
            ApplicationManager.getApplication().saveAll();
            final VirtualFile data = anActionEvent.getData(LangDataKeys.VIRTUAL_FILE);
            if (data != null) {
                try {
                    mci.sendFileToMaya(mci.createFile(new String(data.contentsToByteArray(), "UTF-8")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
