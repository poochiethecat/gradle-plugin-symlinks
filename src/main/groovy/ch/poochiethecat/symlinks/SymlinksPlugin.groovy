package ch.poochiethecat.symlinks

import com.google.common.base.Preconditions
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * symlinks plugin.
 *
 * @author Lino von Burg
 * @since 26.03.2016
 */
class SymlinksPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        SymlinksExtension extension = project.extensions.create('symlinks', SymlinksExtension)

        project.afterEvaluate {
            Preconditions.checkNotNull(extension.foo, 'symlinks.foo configuration required')
            Preconditions.checkNotNull(extension.bar, 'symlinks.bar configuration required')
        }
    }
}
