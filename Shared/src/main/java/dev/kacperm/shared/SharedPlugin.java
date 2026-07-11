package dev.kacperm.shared;

public interface SharedPlugin {

    void loadConfigurations();
    void loadCommands();
    void loadListeners();
    void loadRunnables();
}