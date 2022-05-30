# CodeShare


![screenshot 1](images/screenshot01.png?raw=true "Posting html code")


## About

CodeShare is a simple web platform whose primary purpose is to improve your workflow by providing an easy way to edit and share your code with others.


## Features

- Post code with different types of restrictions
- Access other people's code with universally unique identifier (uuid)
- Work on your code with embedded feature-rich Ace Editor
- Store your private code with 'private' restriction ensuring nobody will access it 
- Quickly access and manage every code you posted
- Perform basic operation using API


## Usage

After running this service with command line you can use it through browser (by default by opening `https://localhost:8389/` page) or through API.
By default, only home page is accessible without logging in so in order to access all features you must be registered.

### Web interface

#### Registration

- To register go to register page and set your username and password.

#### Posting code

- To post code go to post page and set code's: name, number of views allowed (0 unrestricted), time availability (0 unrestricted), private and editable status.
- Name and code field must not be empty.
- Restrictions are optional.
- By default code is set as non-editable and non-private.
- After successful operation you will receive generated unique code.

#### Accessing code

- To access code go to access page and enter code's uuid.
- If you are code's creator you can access it directly from my-code page.
- If code is set as editable anyone registered can make changes and save them.
- If code is set as non-editable it cannot be modified.
- Code set as private can be accessed only by its creator.

#### Deleting code

- To delete code go to my code page and use delete button.

### API

Every endpoint returns Response object. Apart from `/api/register` every request should be authenticated with username and password.

#### Endpoints

- `/api/code` - returns code wrapped in Response object
	* Request parameters: String uuid
	* Request body: none

- `/api/post` - posts code
	* Request parameters: none
	* Request body: Code

- `/api/delete` - deletes code
	* Request parameters: String uuid
	* Request body: none

- `/api/update` - updates code
	* Request parameters: none
	* Request body: Code

- `/api/my-code` - returns list of all code uuid's posted by request sender
	* Request parameters: none
	* Request body: none

- `/api/register` - registers user
	* Request parameters: none
	* Request body: User

#### Objects:

- Response
	* String result
	* String message
	* String uuid
	* Code code

- Code
	* String name
	* String code
	* boolean setAsPrivate
	* boolean editable
	* int viewsAllowed
	* long minutesAllowed

- User
	* String name
	* String password


## Installation

1. Import this repository to some folder with `git clone https://github.com/adrian-jrczk/CodeShare.git`
2. Open this folder and install with `mvn clean package`
3. In `target` folder there will be executable jar file `codeshare.jar` which you can move freely and run with `java -jar codeshare.jar`

The first time you run this program, it creates .codeshare folder (containing database file) inside your home directory.


## Technologies and tools

- Java
- HTML
- CSS
- JavaScript
- Maven
- Spring Boot
- H2 database
- Thymeleaf
- Ace
- Lombok


## Screenshots

![screenshot 2](images/screenshot02.png?raw=true "Posting css code")
***
![screenshot 3](images/screenshot03.png?raw=true "Code posted response")
***
![screenshot 4](images/screenshot04.png?raw=true "Post page side panel hidden")
***
![screenshot 5](images/screenshot05.png?raw=true "My code page with few entries")
***
![screenshot 6](images/screenshot06.png?raw=true "Log in page")
***
![screenshot 7](images/screenshot07.png?raw=true "Access code entry page")
***
![screenshot 8](images/screenshot08.png?raw=true "Accessing non-editable code")

