package de.php_perfect.intellij.ddev.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsActions;
import de.php_perfect.intellij.ddev.cmd.Description;
import de.php_perfect.intellij.ddev.state.DdevStateManager;
import de.php_perfect.intellij.ddev.state.State;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

abstract class DdevAwareAction extends DumbAwareAction {
    DdevAwareAction() {
        super();
    }

    DdevAwareAction(@Nullable @NlsActions.ActionText String text, @Nullable @NlsActions.ActionDescription String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        final Project project = e.getProject();

        if (project == null) {
            e.getPresentation().setEnabled(false);
            return;
        }

        e.getPresentation().setEnabled(this.isActive(project));
    }

    protected abstract boolean isActive(@NotNull Project project);

    protected boolean isActiveHelper(@NotNull Project project, boolean requiresConfigured, boolean requireRunning) {
        final State state = DdevStateManager.getInstance(project).getState();

        if (!state.isAvailable() || (requiresConfigured && !state.isConfigured())) {
            return false;
        }

        Description description = state.getDescription();

        if (description == null) {
            return true;
        }

        if (requireRunning) {
            return description.getStatus() == Description.Status.RUNNING;
        } else {
            return description.getStatus() != Description.Status.RUNNING && description.getStatus() != Description.Status.STARTING;
        }
    }
}
