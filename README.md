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

## Database requirements
All data should be stored in a .txt/.csv file in a humanly readable format.
This allows for easy import/export of the database.

## Models
**_Subject_**:
| Name | Type | Description | Limits |
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
| *LectureSchedule* | Schedule | time schedule with relevant info for lectures | /
| *ExerciseSchedule* | Schedule | time schedule with relevant info for exercises | /
| *LaboratorySchedule* | Schedule | time schedule with relevant info for laboratory exercises | /

### Charts 
This chart is located on the main screen view page, and contains the following fields:
| Subject Name | Lectures | Exercises | Laboratories | Semester requirements
|:----:|:----:|:-----------:|:-----------:|:-----------:|
|*ex. Programming*| Monday: 16:15 - 19:00 | Wednesday: 17:15 - 20:00 | x | Exam-1 x out of 15 points, Exam-2 x out of 20 points

### Dependencies
```
<!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.8</version>
</dependency>
```

```
 <!-- https://mvnrepository.com/artifact/com.google.guava/guava -->
<dependency>
    <groupId>com.google.guava</groupId>
    <artifactId>guava</artifactId>
    <version>30.1.1-jre</version>
</dependency>

```
