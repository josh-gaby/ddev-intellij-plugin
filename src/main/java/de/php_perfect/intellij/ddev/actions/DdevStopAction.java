package de.php_perfect.intellij.ddev.actions;

import com.intellij.openapi.project.Project;
import de.php_perfect.intellij.ddev.cmd.DdevRunner;
import org.jetbrains.annotations.NotNull;

public final class DdevStopAction extends DdevRunAction {
    @Override
    protected void run(@NotNull Project project) {
        DdevRunner.getInstance().stop(project);
    }

    @Override
    protected boolean isActive(@NotNull Project project) {
        return this.isActiveHelper(project, true, true);
    }
}
