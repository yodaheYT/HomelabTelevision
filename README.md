# Homelab Television (HLTV)
## A simple tool made in Java using VLCJ to randomly play files in a directory
![Github Total Downloads](https://img.shields.io/github/downloads/yodaheYT/HomelabTelevision/total?style=for-the-badge&logo=github)
![GitHub last commit](https://img.shields.io/github/last-commit/yodaheYT/HomelabTelevision?style=for-the-badge)
![GitHub Issues or Pull Requests](https://img.shields.io/github/issues/yodaheYT/HomelabTelevision?style=for-the-badge)

![GitHub forks](https://img.shields.io/github/forks/yodaheYT/HomelabTelevision?style=for-the-badge)
![GitHub Repo stars](https://img.shields.io/github/stars/yodaheYT/HomelabTelevision?style=for-the-badge)
![GitHub Release](https://img.shields.io/github/v/release/yodaheYT/HomelabTelevision?sort=date&style=for-the-badge)



| Release | Link                                                                                  | Status                                                                                                                                                                                                             |
|---------|---------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Stable  | [GitHub Releases](https://github.com/yodaheYT/HomelabTelevision/releases/tag/Stable)  | [![GitHub Stable Releases](https://img.shields.io/github/downloads/yodaheYT/HomelabTelevision/Stable/total?style=for-the-badge&logo=github)](https://github.com/yodaheYT/HomelabTelevision/releases/tag/Stable)    |
| Testing | [GitHub Releases](https://github.com/yodaheYT/HomelabTelevision/releases/tag/Testing) | [![GitHub Testing Releases](https://img.shields.io/github/downloads/yodaheYT/HomelabTelevision/Testing/total?style=for-the-badge&logo=github)](https://github.com/yodaheYT/HomelabTelevision/releases/tag/Testing) |

# What is HLTV?
Homelab Television is a Java app that takes in a path, then randomizes the order, and plays them in order.
It was designed to be run on a small server such as a raspberry pi and play content through the output.
# Usage
1. Install a version of JDK-21 ([Recommended 21.02](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html))
2. Download the latest release [Here](https://github.com/yodaheYT/HomelabTelevision/releases) (stable recommended)
3. Run the .jar with args
```bash
# Replace (release) with your downloaded file name and (path) with the path to your directory of videos
java -jar (release).jar (path)
```
##### OR
Edit the Main.java "DefaultPath" variable to your path and run the following
```bash
# Replace (release) with your downloaded file name
java -jar (release).jar
```