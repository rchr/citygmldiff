# citygml-diff

A diff tool for CityGML documents. It was developed as a proof-of-concept in the Master thesis "Semantic Change Detection in CityGML Documents" at the Institute for Geodesy and Geoinformation Science (IGG), Technische Universitaet Berlin, Germany.
The tool is able to detect semantical (attribute) changes as well as geometrical changes between two CityGML documents.

System Requirements:
* Java >= 7
* Maven

Usage:
Since the program uses Maven for dependency management it needs to be installed first.
* Compile: Go into the top-level folder containing the "pom.xml". Call "mvn compile".
* Run: mvn exec:java -Dexec.mainClass="de.igg.citygmldiff.citygmldiff.main.Main" -Dexec.args="PATH_TO_FIRST_CITYGML_DOCUMENT PATH_TO_SECOND_CITYGML_DOCUMENT EDIT_SCRIPT_DESTINATION PATH_TO_FILE_SPECIFYING_UNCERTAIN_MATCHINGS"
The last argument (PATH_TO_FILE_SPECIFYING_UNCERTAIN_MATCHINGS) is optional. If not given it will not be used.
