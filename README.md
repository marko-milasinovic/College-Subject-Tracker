# College-Subject-Tracker
An offline java application for tracking the time and date of lectures and other subject specific info throughout the semester

## Built with
* [Maven](https://mvnrepository.com/artifact/org.openjfx/javafx/11.0.2)
* [OpenJavaFX](https://openjfx.io/openjfx-docs/)
* [OpenJDK - runtime](https://www.openlogic.com/openjdk-downloads)

## UX Requirements
The app should have 2 screens:
* Main view screen, where the user can look at all subjects
  * with today's subject annotated  
* Edit screen, where the user can edit subjects and their info
* (Possible) Minimised screen, that consists of a daily lecture list with the relevant info

## Data storage
Data will be stored in ../user directory in a .json format.

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
| *Espb* | Integer	|	espb value of the subject | only positive, up to 16
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
| *Day* | Enum |	day of week (mon/tue/wed...)
| *Time* | Integer |	start time of first lesson
| *Count* | Integer |	number of lessons for the given day

 **_Day_** - Enum:
| Variable name | Variable type | Description |
|:----:|:----:|:-----------:|
| *DayOfWeek* | Enum |	mon/tue/wed/thu/fri/sat/sun
| *Position* | Integer |	mon=1, tue=2 ...
| *ReadableDay* | String |	region localised day name

## Dependencies
* OpenJFX (JavaFX 16)[https://mvnrepository.com/artifact/org.openjfx/javafx/16]
* Google's (Gson)[https://mvnrepository.com/artifact/com.google.code.gson/gson]
* Google's (Guava)[https://mvnrepository.com/artifact/com.google.guava/guava]

## License
Licensed under [GPLv3](https://www.gnu.org/licenses/gpl-3.0.html)
