package ch.poochiethecat.symlinks

import org.gradle.api.Project
import org.gradle.api.ProjectConfigurationException
import org.gradle.testfixtures.ProjectBuilder

/**
 * @author Lino von Burg
 * @since 26.03.2016
 */
class SymlinksPluginTest extends AbstractTest {

    def "Check extension registration"() {

        when: "plugin applied"
        Project project = ProjectBuilder.builder().build()
        project.plugins.apply "ch.poochiethecat.symlinks"

        then: "extension registered"
        project.extensions.findByType(SymlinksExtension)

    }

    def "Check extension validation"() {

        when: "plugin configured"
        Project project = project {
            apply plugin: "ch.poochiethecat.symlinks"

            symlinks {
                foo '1'
                bar '2'
            }
        }

        then: "validation pass"
        def symlinks = project.extensions.symlinks;
        symlinks.foo == '1'
        symlinks.bar == '2'
    }


    def "Check extension validation failure"() {

        when: "plugin configured"
        Project project = project {
            apply plugin: "ch.poochiethecat.symlinks"

            symlinks {
                foo '1'
            }
        }

        then: "validation failed"
        def ex = thrown(ProjectConfigurationException)
        ex.cause.message == 'symlinks.bar configuration required'
    }

}