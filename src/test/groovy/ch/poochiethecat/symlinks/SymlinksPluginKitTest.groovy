package ch.poochiethecat.symlinks

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.TaskOutcome

/**
 * @author Lino von Burg
 * @since 26.03.2016
 */
class SymlinksPluginKitTest extends AbstractKitTest {

    def "Check plugin execution"() {
        setup:
        build """
            plugins {
                id 'ch.poochiethecat.symlinks'
            }

            symlinks {
                foo '1'
                bar '2'
            }

            task printFoo() << {
                println "fooo: \$symlinks.foo"
            }

        """

        when: "run task"
        BuildResult result = run('printFoo')

        then: "task successful"
        result.task(':printFoo').outcome == TaskOutcome.SUCCESS
        result.output.contains('fooo: 1')
    }
}