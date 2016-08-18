# IBM UrbanCode Deploy Template Parser

### Overview
This script will parse an IBM UrbanCode Deploy component template and creates a markdown README file in a readable format.

### License
This plug-in is protected under the [Eclipse Public 1.0 License](http://www.eclipse.org/legal/epl-v10.html)

### To-Do:
 - [x] Simple Title from Name and Description
 - [x] Create Table of Environment Variables (envPropDefs)
    - Table Headers: Name | Label | Required | Type | Description
 - [x] Process Title and Description
 - [ ] Gather process screenshots 
    - 1) Scrape UCD
    - 2) Generate them in some other way
    - 3) [x] Using generic screenshot, manually update
 - [ ] Display Step Properties with associated steps
    - Needs to be easily legible
 - [ ] Connect [Templates-UCD](https://github.com/IBM-UrbanCode/Templates-UCD) to /test folder.
    - Run JUnit tests against these components