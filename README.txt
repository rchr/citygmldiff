CityGMLDiff

0. Index
--------

1. Copyright
2. About
3. System Requirements
4. Usage
5. Developer
6. Disclaimer


1. Copyright
------------
(c) 2013 - 2014
Richard Redweik


2. About
--------

CityGMLDiff is a diff tool for CityGML documents. It was developed as a proof-of-concept in the Master thesis "Semantic Change Detection in CityGML Documents" at the Institute for Geodesy and Geoinformation Science (IGG), Technische Universitaet Berlin, Germany.
The tool is able to detect semantical (attribute) changes as well as geometrical changes between two CityGML documents.


3. System Requirements
----------------------

* Java >= 7
* Maven


4. Usage
--------

Since the program uses Maven for dependency management it needs to be installed first.
* Compile: Go into the top-level folder containing the "pom.xml". Call "mvn compile".
* Run: mvn exec:java -Dexec.mainClass="de.igg.citygmldiff.citygmldiff.main.Main" -Dexec.args="PATH_TO_FIRST_CITYGML_DOCUMENT PATH_TO_SECOND_CITYGML_DOCUMENT EDIT_SCRIPT_DESTINATION PATH_TO_FILE_SPECIFYING_UNCERTAIN_MATCHINGS"
The last argument (PATH_TO_FILE_SPECIFYING_UNCERTAIN_MATCHINGS) is optional. If not given it will not be used.


5. Developer
------------

Richard Redweik <richard.redweik@posteo.de>


6. Disclaimer
-------------

THIS SOFTWARE IS PROVIDED BY THE DEVELOPER "AS IS" AND "WITH ALL FAULTS."
THE DEVELOPER MAKES NO REPRESENTATIONS OR WARRANTIES OF ANY KIND CONCERNING THE
QUALITY, SAFETY OR SUITABILITY OF THE SOFTWARE, EITHER EXPRESSED OR
IMPLIED, INCLUDING WITHOUT LIMITATION ANY IMPLIED WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.

THE DEVELOPER MAKES NO REPRESENTATIONS OR WARRANTIES AS TO THE TRUTH, ACCURACY OR
COMPLETENESS OF ANY STATEMENTS, INFORMATION OR MATERIALS CONCERNING THE
SOFTWARE THAT IS CONTAINED ON AND WITHIN ANY OF THE WEBSITES OWNED AND
OPERATED BY THE DEVELOPER.

IN NO EVENT WILL THE DEVELOPER BE LIABLE FOR ANY INDIRECT, PUNITIVE, SPECIAL,
INCIDENTAL OR CONSEQUENTIAL DAMAGES HOWEVER THEY MAY ARISE AND EVEN IF
THE DEVELOPER HAVE BEEN PREVIOUSLY ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.