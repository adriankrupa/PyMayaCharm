package com.krupa.adrian.PyMayaCharm.settings;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;

@State(
        name = "SettingsProvider",
        storages = {
                @Storage(file = StoragePathMacros.WORKSPACE_FILE)
        }
)

public class SettingsProvider implements PersistentStateComponent<SettingsProvider.State> {
    private State myState = new State();

    public static SettingsProvider getInstance(Project project) {
        return ServiceManager.getService(project, SettingsProvider.class);
    }

    public int getPort() {
        return (myState.Port == -1 || myState.Port == 0) ? 4434 : myState.Port;
    }

    public void setPort(int port) {
        myState.Port = port;
    }

    public String getHost() {
        return (myState.Host == null || myState.Host.isEmpty()) ? "localhost" : myState.Host;
    }

    public void setHost(String host) {
        myState.Host = host;
    }

    @Nullable
    @Override
    public State getState() {
        return myState;
    }

    @Override
    public void loadState(State state) {
        myState.Host = state.Host;
        myState.Port = state.Port;
    }

    public static class State {
        public int Port;
        public String Host;
    }
}
