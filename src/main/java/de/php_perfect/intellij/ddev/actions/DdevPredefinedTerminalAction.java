package de.php_perfect.intellij.ddev.actions;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import de.php_perfect.intellij.ddev.terminal.DdevTerminalRunner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.terminal.TerminalTabState;
import org.jetbrains.plugins.terminal.TerminalToolWindowManager;

public final class DdevPredefinedTerminalAction extends DdevAwareAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();

        if (project == null) {
            return;
        }

        DdevTerminalRunner runner = new DdevTerminalRunner(project);
        TerminalTabState tabState = new TerminalTabState();
        tabState.myTabName = "DDEV Web Container";

        TerminalToolWindowManager.getInstance(project).createNewSession(runner, tabState);
    }

    @Override
    protected boolean isActive(@NotNull Project project) {
        return this.isActiveHelper(project, true, true);
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }
}
