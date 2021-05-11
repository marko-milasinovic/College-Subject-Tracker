# College-Subject-Tracker
An offline java application for tracking the time and date of lectures and other subject specific info throughout the semester

## Built with
* [Maven](https://maven.apache.org/what-is-maven.html)
* [OpenJavaFX](https://wiki.openjdk.java.net/display/OpenJFX)

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
| *Id* | Integer	|	unique subject id | only positive, up to 2 147 483 647
| *SubjectName* | String	| subject name | max length of 127 characters (for easier sql storage)
| *AbbreviatedName* | String | abbreviated subject name | max length of 10 characters
| *Description* | String | short subject description | max length of 65,536 characters
| *SemesterPlan* | String | text explaining the plan for the semester | max length of 65,536 characters
| *ProfessorName* | String | name of the Professor | max length of 127 characters
| *AssistentName* | String | name of the Assistent | max length of 127 characters
| *LabAssistentName* | String | name of the Lab Assistent | max length of 127 characters
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

### Additional demands
x
