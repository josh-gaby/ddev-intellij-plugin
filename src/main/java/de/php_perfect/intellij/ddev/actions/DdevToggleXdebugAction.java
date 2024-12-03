package de.php_perfect.intellij.ddev.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.project.Project;
import de.php_perfect.intellij.ddev.cmd.DdevRunner;
import de.php_perfect.intellij.ddev.cmd.Description;
import de.php_perfect.intellij.ddev.state.DdevStateManager;
import de.php_perfect.intellij.ddev.DdevIntegrationBundle;
import de.php_perfect.intellij.ddev.icons.DdevIntegrationIcons;
import de.php_perfect.intellij.ddev.state.State;
import org.jetbrains.annotations.NotNull;

public final class DdevToggleXdebugAction extends DdevRunAction {
    @Override
    protected void run(@NotNull Project project) {
        try {
            DdevRunner.getInstance().toggleXdebug(project);
        } catch (Exception ignored) {

        }
    }

    public void update(@NotNull AnActionEvent e) {
        super.update(e);
        final Project project = e.getProject();
        Presentation presentation = e.getPresentation();

        final State state = DdevStateManager.getInstance(project).getState();

        if (!state.isAvailable() || !state.isConfigured()) {
            return;
        }

        Description description = state.getDescription();

        if (description == null) {
            return;
        }

        if (description.getXdebugStatus()) {
            presentation.setIcon(DdevIntegrationIcons.DdevXdebugEnabled);
            presentation.setText(DdevIntegrationBundle.message("action.DdevIntegration.Run.DisableXdebug.text"));
        } else {
            presentation.setIcon(DdevIntegrationIcons.DdevXdebugDisabled);
            presentation.setText(DdevIntegrationBundle.message("action.DdevIntegration.Run.EnableXdebug.text"));
        }
    }

    @Override
    protected boolean isActive(@NotNull Project project) {
        final State state = DdevStateManager.getInstance(project).getState();

        if (!state.isAvailable() || !state.isConfigured()) {
            return false;
        }

        Description description = state.getDescription();

        if (description == null) {
            return true;
        }

        return  description.getStatus() == Description.Status.RUNNING;
    }
}
