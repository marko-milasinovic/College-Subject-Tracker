# College-Subject-Tracker
An offline java application for tracking the time and date of lectures and other subject specific info throughout the semester

## Built and run with
* [Maven](https://mvnrepository.com/artifact/org.openjfx/javafx/16)
* [OpenJavaFX](https://openjfx.io/openjfx-docs/)
* [OpenJDK - runtime](https://www.openlogic.com/openjdk-downloads)

## Project core release status
- [ ] Main subject viewer screen (Main view screen, where the user can look at all subjects)
    - [ ] Current day's subject annotated
- [ ] Read data from file
- [ ] Write data to file
- [x] Data models
    - [x] Framework for data manipulation
- [ ] Improved design (e.g. icons)
    - [ ] Tooltips
- [ ] First release

## Project feature release status
- [ ] Subject editor screen (where the user can edit subjects and their info)
- [x] .txt human readable save files
    - [ ] optional .gson save files
- [ ] System specific save file paths (in ../user directory in a .json format.)
- [ ] Windows notification bar alerts
- [ ] Various optimisations e.g. Platform.runLater() 

## Main subject viewer screen
![Subject viewer screen_todo](/assets/subject_viewer_screen.png)

## Dependencies
* OpenJFX [JavaFX 16](https://mvnrepository.com/artifact/org.openjfx/javafx/16)
* Google's [Gson](https://mvnrepository.com/artifact/com.google.code.gson/gson) - a Java serialization/deserialization library to convert Java Objects into JSON and back
* Google's [Guava](https://mvnrepository.com/artifact/com.google.guava/guava) - a suite of core and expanded libraries that include utility classes, Google's collections, I/O classes

## License
Licensed under [GPLv3](https://www.gnu.org/licenses/gpl-3.0.html)
