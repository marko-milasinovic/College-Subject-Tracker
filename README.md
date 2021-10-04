
<h2 align="center"> College Subject Tracker </h2> 

<h3 align="center"> Offline java application for tracking the time of lectures and other subject specific info throughout the semester </h3>

<p align="center">
<img src="https://img.shields.io/badge/Support-Windows%20x64-blue?logo=Windows&style=flat-square">
<img src="https://img.shields.io/github/license/marko-milasinovic/College-Subject-Tracker?style=flat-square">
</p>

# [💾Download Latest Stable Build](https://github.com/marko-milasinovic/College-Subject-Tracker/releases)
Supported Languages: English

## Built and run with
* [Maven](https://mvnrepository.com/artifact/org.openjfx/javafx/16)
* [OpenJavaFX](https://openjfx.io/openjfx-docs/)
* [OpenJDK - runtime](https://www.openlogic.com/openjdk-downloads)

## Project core release status
- [ ] Main subject viewer screen (Main view screen, where the user can look at all subjects)
    - [ ] Auto updated current subject information
    - [x] Coloured current day's subjects in table
- [x] Read data from file
    - [x] Write data to file
- [x] Data models
    - [x] Framework for data manipulation
- [ ] Improved design (e.g. icons)
    - [ ] Tooltips
- [ ] First release

## Project feature release status
- [ ] Subject editor screen (Edit view screen, where the user can add/edit/delete subjects and their info)
- [x] .txt human readable save files
    - [ ] optional .gson save files
- [ ] System specific save file paths (in ../user directory in a .json format.)
- [ ] Windows notification bar alerts
- [ ] Various optimisations e.g. Platform.runLater()
- [x] Physical monitor resolution dependant scaling / scrolling

## Main subject viewer screen
![Main viewer screen](/assets/cst_main_view_screen.png)

## Dependencies
* OpenJFX [JavaFX 16](https://mvnrepository.com/artifact/org.openjfx/javafx/16)
* Google's [Gson](https://mvnrepository.com/artifact/com.google.code.gson/gson) - a Java serialization/deserialization library to convert Java Objects into JSON and back
* Google's [Guava](https://mvnrepository.com/artifact/com.google.guava/guava) - a suite of core and expanded libraries that include utility classes, Google's collections, I/O classes

## License
Licensed under [GPLv3](https://www.gnu.org/licenses/gpl-3.0.html)
