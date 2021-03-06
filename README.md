
<h2 align="center"> College Subject Tracker </h2> 

<h3 align="center"> Offline java application for tracking the time of lectures and other subject specific info throughout the semester </h3>

<p align="center">
<img src="https://img.shields.io/badge/Support-Windows%20x64-blue?logo=Windows&style=flat">
<img src="https://img.shields.io/github/license/marko-milasinovic/College-Subject-Tracker?style=flat">
<img src="https://tokei.rs/b1/github/marko-milasinovic/College-Subject-Tracker?category=code">
</p>

# [💾Download Latest Stable Build](https://github.com/marko-milasinovic/College-Subject-Tracker/releases/latest)
Supported Languages: English

## Built and run with
* [Maven](https://mvnrepository.com/artifact/org.openjfx/javafx/16)
* [OpenJavaFX](https://openjfx.io/openjfx-docs/)
* [OpenJDK - runtime](https://www.openlogic.com/openjdk-downloads)

## Project core release status
- [x] Main subject viewer screen (Main view screen, where the user can look at all subjects)
    - [x] Auto updated current subject information
    - [x] Coloured current day's subjects in table
- [x] Read data from file
    - [x] Write data to file
- [x] Data models
    - [x] Framework for data manipulation
- [ ] Improved design (e.g. icons)
    - [ ] Tooltips
- [ ] First release

## Project feature release status
- [x] Subject editor screen (Edit view screen, where the user can add/edit/delete subjects and their info)
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
Code licensed under [GNU GPLv3](https://www.gnu.org/licenses/gpl-3.0.html)

### Credits
Some icons are licensed separately by Freepik from www.flaticon.com
