package com.krupa.adrian.PyMayaCharm.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.krupa.adrian.PyMayaCharm.MayaConnectionInterface;

public class ConnectWithMayaAction extends MayaAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        System.out.println("Connecting to Maya");
        MayaConnectionInterface mci = new MayaConnectionInterface("localhost", 6002);
        mci.connectToMaya();
    }
}
