package de.php_perfect.intellij.ddev.icons;

import com.intellij.ui.IconManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public final class DdevIntegrationIcons {
    public static final @NotNull Icon DdevLogoColor = IconManager.getInstance().getIcon("/icons/ddevLogoColor.svg", DdevIntegrationIcons.class.getClassLoader());
    public static final @NotNull Icon DdevLogoMono = IconManager.getInstance().getIcon("/icons/ddevLogoGrey.svg", DdevIntegrationIcons.class.getClassLoader());
    public static final @NotNull Icon DdevXdebugEnabled = IconManager.getInstance().getIcon("/icons/ddevXdebugEnabled.svg", DdevIntegrationIcons.class.getClassLoader());
    public static final @NotNull Icon DdevXdebugDisabled = IconManager.getInstance().getIcon("/icons/ddevXdebugDisabled.svg", DdevIntegrationIcons.class.getClassLoader());

    private DdevIntegrationIcons() {
    }
}
