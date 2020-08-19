Interview question
==================


This is a very basic spring-boot app. Run it (using `mvn spring-boot:run`) or your favorite IDE.
Try the url `http://localhost:5000/greeting?name=David`, it should return the string: "Hello David".

# Requirements
### Part one - Basic local service
We would like to create 2 APIs, 
* one to store an array of numbers, 
* one that returns a random permutation of that array.

Storage should be in memory without using any database.

### Acceptance criteria
* Sending `http://localhost:5000/store?numbers=2,1,3,4,6,5,7` should 
return an ID of the array (e.g., 1)
* Sending `http://localhost:5000/permutation?id=1` should return 
a random permutation of the array (e.g., `2,3,6,7,1,3,5`)

Implementation:

- ArrayPermutationController
It maps the request url to the corresponding service method and validate the query parameters if necessary.

1. storeArray:
Receives numbers as a string of comma separated values, a 400 response will be returned 
if numbers parameter is not present. Numbers param will be passed in and validated
before we store it into memory, a 400 response will be returned if any non-integer value found in numbers.

2. getPermutationById:
Receives ID as a long type if it's not present or its value is not valid, a 400 response will be returned.
The value is passed into the service method which returns a random permutation of the array with that ID.


- ArrayNotFoundException
Custom exception mapped to a 404 http response.


- InvalidNumbersException
Custom exception mapped to a 400 http response.


- MissingNumbersException
Custom exception mapped to a 400 http response.


- NumbersList
A model containing the list to store and its string value.


- ArrayPermutationService
See implementation details in ArrayPermutationServiceImpl.


- ArrayPermutationServiceImpl
We use maps to store the arrays and ids as it gives us constant access time (O(1)) by key.

1. storeArray
Receives numbers as input, then calls the method in NumbersUtil to convert it into an NumberList
which contains the list to store and its string value.
It adds an extra service level validation to make sure numbers parameter is present (just in case 
it's used in classes other than ArrayPermutationController).
In order to avoid storing duplicates into memory, we check the string value of the obtained array 
against arrayToId map and retain the corresponding ID if it exists already.
In the end, we increment the value of the ID and put the pair into idToList and listStringToId maps respectively.

2. getPermutationById
Receives ID as input, then checks if that ID exists in idToArray map, an ArrayNotFoundException will be thrown
if the given id cannot be found. 
After we retrieve array from the map, we create a copy of that array 
then calls Collections.shuffle() (performance O(n) with n being the size of the array) to randomize it.
In the end, we return that array.


- NumbersUtil
1. convertToList
Receives numbers as input, convert it into a string array first by splitting it using comma.
We parse each string in the array into an integer with Integer.parseInt() and store the integers into a list.
If any of those strings is not an integer, we throw an InvalidateNumbersException. 
We calculate the stringValue which can represent the list in a string format at the same time, using toString(O(n)) 
on the arraylist afterward will result in iterating through the same list twice.
In the end, we return the NumberList which contains both the list and its string value.

2. isPermutation
checks if a list is a permutation of another list. It's used in the tests to verify the permutations.


### Part two - Adding persistence layer
We would like to have persistence of the data in case the server drops.
`application.yml` is configured for H2 database, but feel free to use any other relational DB you are comfortable with to save the data.
Make sure that your app will work with H2 as well as it will be tested with H2 (integartion-tests can help here).

#### Acceptance criteria
* Sending `http://localhost:5000/store?numbers=2,1,3,4,6,5,7` should 
return an ID of the array (e.g., 1)
* Sending `http://localhost:5000/permutation?id=1` should return 
a random permutation of the array (e.g., `2,3,6,7,1,3,5`)
Restarting the spring-boot app and Sending `http://localhost:5000/permutation?id=1` 
should give back a random permutation of the array (e.g., `2,3,6,7,1,3,5`)

## Guidelines
* Fork this repository and push your commits
* Use the spring-boot template given
* Write unit-tests, integration-tests 
  * Write in javadocs what scenarios are in test
  * Higher coverage is better
* Write code documentation
* All classes given are meant to used as reference - once they are not needed, they can be removed.
* This project uses [lombok](https://projectlombok.org/) - use it when possible
* Properly organize your project with `.gitignore` file, `readme` file explaining how to run the project, etc.
* Do all 2 parts, and use git tags to mark the commit fulfilling part 1 and part 2.

## Deliverables
* Send us a link to a repository fulfilling the requirements with two tags to check part 1 and 2.
* Your code will be tested using different tests.
* Successful implementation will move to interview.
