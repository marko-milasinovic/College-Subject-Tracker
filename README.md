# College-Subject-Tracker
An offline java application for tracking the time and date of lectures and other subject specific info throughout the semester

## Built and run with
* [Maven](https://mvnrepository.com/artifact/org.openjfx/javafx/16)
* [OpenJavaFX](https://openjfx.io/openjfx-docs/)
* [OpenJDK - runtime](https://www.openlogic.com/openjdk-downloads)

## Project core release status
- [] Main subject viewer screen (Main view screen, where the user can look at all subjects)
  -[] Current day's subject annotated
- [] Read data from file
- [] Write data to file
- [x] Framework for data manipulation
- [x] Data models
- [ ] Improved design
- [ ] First release

## Project feature release status
- [ ] Subject editor screen (where the user can edit subjects and their info)
- [ ] System specific save file paths (in ../user directory in a .json format.)
- [ ] Windows notification bar alerts
- [ ] Various optimisations e.g. Platform.runLater() 

## Models
**_Subject_**:
| Variable name | Variable type | Description | Limits |
|:----:|:----:|:-----------:|:-----------:|
| *Uuid* | UUID |	unique subject id | must be unique
| *SubjectLongName* | String	| subject name | max length of 127 characters (for easier sql storage)
| *SubjectShortName* | String | abbreviated subject name | max length of 10 characters
| *SubjectDescription* | String | short subject description | max length of 65,536 characters
| *Professor* | Person | person of Professor type |
| *Assistent* | Person | person of Assistent type | 
| *WebLinks* | List<WebLink> | list of links to subject materials | max length of 256 characters per link, with a String descriptor of the link provided
| *maxEspb* | Integer	|	espb value of the subject | only positive, up to 16
| *semesterId* | Integer	|	the semester number where the user got the subject | only positive, up to 16
| *LectureSchedule* | Schedule | time schedule with relevant info for lectures | presumably only 1 day per week for the given schedule
| *ExerciseSchedule* | Schedule | time schedule with relevant info for exercises | presumably only 1 day per week for the given schedule
| *LaboratorySchedule* | Schedule | time schedule with relevant info for laboratory exercises | presumably only 1 day per week for the given schedule

**_Person_**:
| Variable name | Variable type | Description |
|:----:|:----:|:-----------:|
| *Uuid* | UUID |	unique person id
| *FirstName* | String |	first name of the person
| *LastName* | String |	last name of the person
| *EMail* | String |	person's email
| *Occupation* | String |	given occupation, eg. Professor, Assistent, LabAssistent

 **_Schedule_**:
| Variable name | Variable type | Description |
|:----:|:----:|:-----------:|
| *DayOfWeek* | Enum |	day of week (mon/tue/wed...)
| *Time* | Integer |	start time of first lesson
| *Count* | Integer |	number of lessons for the given day

 **_DayOfWeek_** - Enum:
| Variable name | Variable type | Description |
|:----:|:----:|:-----------:|
| *Day* | String |	mon/tue/wed/thu/fri/sat/sun
| *Position* | Integer |	mon=1, tue=2 ...
| *ReadableDay* | String |	region localised day name

## Dependencies
* OpenJFX [JavaFX 16](https://mvnrepository.com/artifact/org.openjfx/javafx/16)
* Google's [Gson](https://mvnrepository.com/artifact/com.google.code.gson/gson) - a Java serialization/deserialization library to convert Java Objects into JSON and back
* Google's [Guava](https://mvnrepository.com/artifact/com.google.guava/guava) - a suite of core and expanded libraries that include utility classes, Google's collections, I/O classes

## License
Licensed under [GPLv3](https://www.gnu.org/licenses/gpl-3.0.html)
