## GLASY Accessibility Report
### Question
1. For each Principle of Universal Design, write 2-3 sentences — or point form notes — explaining which features of your program adhere to that principle. If you do not have any such features, you can either:

   - Describe features that you could implement in the future that would adhere to the principle or

   - Explain why the principle does not apply to a program like yours.

### Answer
- Principle 1: Equitable Use
  - We could implement text-to-speech functionality to allow users who are dyslexic or have poor sight to use our software.
  - To go along with the last point, we could implement voice commands to allow blind users to use our software, or users who have poor fine motor skills
  - Lastly, we could add a new selection criteria and a new routing mode to only show routes that are wheelchair accessible, or have no stairs.
 
  - Additionally, we can improve equitable use by having the option to travel via public-transit, cycling, or walking. If tourists are visiting the city, they would most likely be taking public transit within the city.
  - At the moment, the itinerary is created and travel time is calculated using travel using the car.It would also be more equitable for people in urban areas (who would prefer walking or public transit), the sight-impared, or people at an economic disadvantage. We can add an option for the user to select which method of transport they would like to take or tell them what is the best way to get to each destination.

- Principle 2: Flexibility in Use
  - We allow the user to regenerate their route, so that they could avoid certain areas of town or walk by a certain part of town on their way to their locations.
  - In our “Save Itinerary” use case, we could print a map and detailed route description as well as their locations they are supposed to visit, so users without access to wifi or cellular data could use the result of our app while they are out.
  - We could allow the user to choose their route to take when deciding their itinerary for the day.

- Principle 3: Simple and Intuitive Use
  - Our program can implement a more simple interface, where default values are auto-filled if users do not enter them, to make the user experience most simple
  - We could implement an “advanced search” function to allow users to only see the most simple parameters, and then use advanced search if they want a more detailed set of parameters
  - Our results could give an interactive map instead of the static map, so the user could pan around and zoom into the route, which would be more intuitive than our static map we have currently.
  - We could also display the attractions in different orders, depending on user needs. For example, sorting the locations from closest to farthest, or sorting by ratings. 

- Principle 4: Perceptible Information
  - We could use both text and symbols within our program to give users who have trouble reading ability to better use the GUI
  - We could add the option to read the text in different languages
  - Another feature we could add is alt-text for the images that the user is displayed with the attractions, so if the user is on a poor connection, or has trouble making out the images, they can see the alt-text

- Principle 5: Tolerance for Error
  - Adding default values to our paramers will help with this, but alternatively, we can also give detailed error messages written for a variety of different input error cases
  - Adding undo and back buttons throughout our program, such as “reset to default” buttons within our search parameter selection would allow for easier correction to user error
  - We added the back buttons which allows the user to remove locations they don’t want to visit and regenerate the itinerary

- Principle 6: Low Physical Effort
  - Allowing users to choose their route based on their activity preferences, with a new filter introduced for walking distance, where right now we only have activity radius. This will let users choose their physical effort they want to expend.
  - Users can access their itineraries later too if they want to, as they have the option to save it in a place of their choice
  - We could package our app for samsung and apple phones, so that users are able to use their mobile phones to use our app, instead of needing to get out their computer and run our java program.

- Principle 7: Size and Space for Approach and Use
  - Allowing our users to customize what they are shown on screen at once, allowing them to make their layout fit to their finger size, for tapping (on cell phone), or their window size to fit different computer display sizes.
  - Change our file writing for our SaveItinerary 	to work for all operating systems, since they use different file writing protocols, so users on all OS can use it
  - Currently, the default size of the program is too large on smaller screen sizes, so we should change the program to be responsive based on the view width

### Question
2. Write a paragraph (3-6 sentences) about who you would market your program towards, if you were to sell or license your program to customers. This could be a specific category of users, such as "students", or more vague, such as "people who like games". Try to give a bit more detail along with the category.

### Answer
If GLASY were sold or licensed to customers, it would be targeted towards people looking 
to take a day trip, especially those who are short on time and need a quick, efficient 
way to plan an itinerary. This program would appeal to individuals who are visiting new 
cities or areas and prefer to explore but don’t want to spend too much time on planning.
GLASY would be particularly useful for busy professionals, tourists, or spontaneous 
travelers who are seeking a well-organized day trip without the hassle of extensive 
research. Additionally, it would cater to those who enjoy discovering new places but 
might feel overwhelmed by too many options or a lack of local knowledge.


### Question
3. Write a paragraph about whether or not your program is less likely to be used by certain demographics. Your discussion here should be informed by the content of our embedded ethics modules this term.

### Answer
GLASY, while useful for many travelers, may be less likely to appeal to certain demographics, particularly those who face challenges with accessibility or have physical disabilities. Since GLASY is primarily targeted towards those looking to travel, it may exclude individuals who struggle with traveling, especially since there is no differentiation between accessible and non-accessible locations. The program currently doesn’t account for accessibility needs, meaning that individuals with mobility impairments or limitations may face barriers in understanding which locations they can safely or comfortably visit. Furthermore, GLASY assumes the user is traveling by car, which can lead to an inequitable user experience, as the shortest path and travel time calculations may be inaccurate for other modes of transportation. As a result, users who don’t have access to a car may experience systemic exclusion and find the app less useful, highlighting a gap in fairness and equal access to services.
