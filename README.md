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

The user will choose a location, select a radius, start and end time, type of locations
they want to visit, and the program will return a list of
the most popular spots around based on your filters. The user may choose which locations specifically
they'd like to visit, which gets returned to the program. Based on this list, the program will plan an itinerary 
for the user’s day around visiting these spots, and will include times and routes to 
take. The program will plan the route based on whichever path is the most efficient 
(meaning which paths require the least amount of travel or are conveniently close to 
each other). This program also allows the user to save their itinerary as a markdown file to open
at a later time.

Note that this program is best run on a device with screen size 13" x 9". This entire program was
built using Java 17.

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
- Finally, an itinerary is returned in the format of time of arrival : location : address. 
  - The user has the option to save the itinerary as a .md file to their device

## Run Instructions
1. Clone the repository
```angular2html
git clone https://github.com/gabe-mc/GLASY.git
cd GLASY
```
2. Get the API keys from Google Maps, Foursquare and IPAPI and put them into a `resources` folder in `main`.
3. Build and run the application
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
you also have the option to save it as a .md file  using the "save" button.

## License
Feel free to use this program as you see fit, but please credit us if you use our code.

## Credits
*Built by Alan Su, Gabriel McFadyen, Lily Phan, Steven Lin, Yiping Chen as the final 
project for the University of Toronto course CSC207: Software Design.*
