import groovy.com.urbancode.component.parser.ParserHelper

checkInputArgs(this.args)
def componentInput = this.args[0]

ParserHelper ph = new ParserHelper(componentInput)
ph.addTitle()
ph.addOverview()
ph.addPluginVersionRequirements()
ph.addEnvVarTable()
ph.addProcesses()
ph.addModeling()

/**
 * param args Input parameters in String list format.
 * return Succesful, If input has one argument and is not '-help'
 * Crash with use output if one argument was not given or one of the arguments == '-help'
 */
public void checkInputArgs(def args) {
    if (args.contains("-help") || args.contains("-h") || args.size() != 1) {
        println "---- Component Parser Script ----- "
        println "===================================="
        println  ""
        println "Summary: This script will parse an IBM UrbanCode Deploy component template and creates a markdown README file in a readable format."
        println "===================================="
        println ""
        println "Script Format: groovy runner.groovy <componentTemplate.json>"
        println "Arg 1: <componentTemplate.json> = The component template to parse."
        System.exit(1)
    }
}
