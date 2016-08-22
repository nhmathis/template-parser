
package groovy.com.urbancode.component.parser

import groovy.json.JsonSlurper

public class ParserHelper {
    def input
    File readme
    
    final def GENERIC_PIC = "https://ibm.box.com/shared/static/l4de9ku1wufswjx9ho79ez73dh0o8a1p.png"
    
    public ParserHelper (String inputFile) {
        this(inputFile, "README.md")
    }
    
    public ParserHelper (String inputFile, String readmeName) {
        confirmJson(inputFile)
        new File("releases").mkdir()
        readme = new File("releases" + File.separator + readmeName)
        if (readme.isFile()) {
            readme.delete()
        }
    }


    void addTitle() {
        def name = input.name
        def description = input.description
         
        readme << "# ${name} Template\r\n"
        readme << "Description: ${description}\r\n"
        readme << "\r\n"
        println "[Ok] Created Name and Description."
    }
    
    void addOverview() {
        readme << "### Overview\r\n"
        readme << "\tThe overview of ${input.name} goes here! \r\n"
        readme << "\r\n"
        println "[Ok] Created Overview placeholder."
    }
    
    void addEnvVarTable() {
        def envPropDefs = input.envPropDefs
        readme << "### Environment Variables \r\n"
        if (envPropDefs.size() > 0) {
            readme << "Label | Name | Required | Type | Description \r\n"
            readme << "------------ | ------------- | ------------- | ------------- | ------------- \r\n"
            for (env in envPropDefs) {
                readme << "${env.label} | ${env.name} | ${env.required} | ${env.type} | ${env.description} \r\n"
            }
            readme << "\r\n"
            println "[Ok] Created a table of ${envPropDefs.size()} Environment Properties."
        }
        else {
            readme << "No Environment Properties. \r\n"
            println "[Warning] No Environment Property Definitions (envPropDefs) were found!"
        }
    }
    
    void addProcesses() {
        def processes = input.processes
        def pName
        def pDesc
        if (processes.size() > 0) {
            for (p in processes) {
                pName = p.name
                pDesc = p.description
                readme << "### ${pName} Process \r\n"
                readme << "\t${pDesc} \r\n"
                readme << "![${pName} - Screenshot](${GENERIC_PIC}) \r\n"
                readme << "\r\n"
            }
            readme << "\r\n"
            println "[Ok] Created ${processes.size()} Processes."
        }
        else {
            readme << "No Processes. \r\n"
            println "[Warning] No Processes (processes) were found!"
        }
    }

    void addModeling() {
        readme << "### Modeling\r\n"
        readme << "\tMap IBM UrbanCode Deploy Applications, Components, Environments, and Resources to the integration's equivalents.\r\n"
        readme << "\r\n"
        readme << "\tApplication:[Description]\r\n"
        readme << "\r\n"
        readme << "\tEnvironment:[Description]\r\n"
        readme << "\r\n"
        readme << "\tComponent:[Description]\r\n"
        readme << "\r\n"
        readme << "\tResource:[Description]\r\n"
        readme << "\r\n"
        readme << "### Application, Environments, and the Components Screenshot\r\n"
        readme << "![Application, Environments, and the Components Model - Screenshot](${GENERIC_PIC})\r\n"
        readme << "\r\n"
        readme << "### Resource Tree Screenshot\r\n"
        readme << "![Resource Tree Model - Screenshot](${GENERIC_PIC})\r\n"
        readme << "\r\n"
        println "[Ok] Created Modeling Section"
    }

    void confirmJson(String inputFile) {
        File file = new File(inputFile)
        if (!file.isFile()) {
            throw new FileNotFoundException ("[Error] '${inputFile}' is not a file.")
        }
    
        JsonSlurper slurper = new JsonSlurper()
        try {
            this.input = slurper.parseText(file.text)
        } catch (IOException ex) {
            ex.printStackTrace()
            throw new IOException ("[Error] '${inputFile}' was not valid JSON. Please confirm it can be imported into IBM UrbanCode Deploy.")
        } catch (Exception ex) {
            ex.printStackTrace()
            throw new Exception ("[Error] Unknown error. Please create an GitHub issue and submit this error log if error is unclear.")
        }
        println "[Ok] Template '${inputFile}' is a valid .json file!"
    }
}