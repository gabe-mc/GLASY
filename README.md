# GLASY

## Overview
GLASY is at its core an itinerary planner application, where we help plan the perfect route for someone’s outing by connecting different events and stores/restaurants into a walking path within a certain radius of their starting position.

## Table of Contents
- [Software Specification](#software-specification)
- [Features](#features)
- [Installation Instructions](#installation-instructions)
- [Usage](#usage)
- [License](#license)
- [Credits](#credits)

## Software Specification

The user will choose a location and select a radius, and the program returns a list of
the most popular spots around. Based on this list, the program will plan an itinerary 
for the user’s day around visiting these spots, and will include times and routes to 
take. The program will plan the route based on whichever path is the most efficient 
(meaning which paths require the least amount of travel or are conveniently close to 
each other). To expand the program, the machine can also show the hours of operation 
for each location and take that into consideration, as well as the genre of the 
attraction. Another potential expansion is allowing the user to choose how many or
specifically which locations they want to go to. 

## Features
This program will:
- Bring you to a Starting Screen and start by taking in the user's current location as the default starting location
- Bring you to a different screen where you can customize information like:
  - Distance willing to travel (radius in km)
  - Trip start time
  - Trip end time
  - Start location (if different from the default)
  - Types of attractions you'd like to see
- After you customize your information, it will take you to a list of recommended places, and you choose which ones to go through
- Finally, an itinerary is returned in the format of location: time of arrival
  - The user has the option to save the itinerary as a .txt file to their device

## Run Instructions
1. Clone the repository
```angular2html
git clone https://github.com/gabe-mc/GLASY.git
cd GLASY
```
2. Build and run the application
```angular2html
cd src/main/java/app
javac Main.java
java Main
```
Requirements:
- Java JDK 8 or higher
- Git

## Usage

When you start the program, press "Start". 

You should be taken to a new screen where you are prompted to enter in information
such as your maximum radius and start/end time. Please enter in all that information, and
leave nothing blank.

When you are finished, press the "Continue" button.

You will be brought to a new page where you are shown a bunch of locations based on the
filters you entered in earlier. The locations will be shown in the form of a scrollable
checkbox list, and you should select all the locations you wish to visit.

When you are finished, press the "Continue" button.

Finally, you will have your itinerary displayed. You can view it or screenshot it, but 
you also have the option to save it as a .txt file  using the "save" button.

## License
Feel free to use this program as you see fit, but please credit us if you use our code.

## Credits
*Built by Alan Su, Gabriel McFadyen, Lily Phan, Steven Lin, Yiping Chen as the final 
project for the University of Toronto course CSC207: Software Design.*
