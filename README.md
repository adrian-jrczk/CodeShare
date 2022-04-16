# CodeShare

## Table of contents
- [About](#about)
- [Features](#features)
- [Usage](#usage)
  - [Registration](#registration)
  - [Posting code](#posting-code)
  - [Accessing and updating code](#accessing-and-updating-code)
  - [Deleting code](#deleting-code)
  - [API](#api)
- [Installation](#installation)
- [Technologies used](#technologies-used)
- [Screenshots](#screenshots)


## About

CodeShare is a simple web service which main goal is to improve your workflow by providing an easy way to share text/code with others in local environments.

## Features

- Use this service with your browser or through API
- Post code with different types of restrictions
- Access other people's code/text with universally unique identifier (uuid)
- Edit posted code directly through browser if it's set as editable
- Store your private code with 'private' restriction ensuring nobody will access it 
- Quickly access and manage every code you posted

## Usage

After running this service with command line you can use it through browser (by default by opening `https://localhost:8389/` page) or through API.
By default, only home page and api page are accessible without logging in so in order to access all features you must be registered.

### Registration

- To register go to register page and specify your username and password.

### Posting code

- To post code go to post page and set code's: name, number of views allowed (0 unrestricted), time availability (0 unrestricted), private and editable status.
- Name and code field must not be empty.
- Restrictions are optional.
- By default code is set as non-editable non-private.
- After successful operation you will receive generated unique code.

### Accessing and updating code

- To access code go to access page and enter code's uuid.
- If you are code's creator you can access it directly from my-code page.
- If code is set as editable anyone registered can make changes and save them.
- If code is set as non-editable it cannot be modified.
- Code set as private can be accessed only by its creator.

### Deleting code

- To delete code go to my code page and press delete button next to chosen code uuid.

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
2. Open this folder and install with `mvn clean install`
3. In `target` folder there will be executable jar file `codeshare.jar` which you can move freely and run with `java -jar codeshare.jar`

## Technologies used

- Java 17
- Spring Boot 2.6.4
- Spring Security 5.6.2
- H2 database 1.4.200
- Thymeleaf 3.0.15

## Screenshots

![screenshot 1](images/screenshot01.png?raw=true "Login page")
***
![screenshot 2](images/screenshot02.png?raw=true "My code page")
***
![screenshot 3](images/screenshot03.png?raw=true "Post page")
***
![screenshot 4](images/screenshot04.png?raw=true "Post page side panel behavior")
***
![screenshot 5](images/screenshot05.png?raw=true "Post page side panel hidden")
***
![screenshot 6](images/screenshot06.png?raw=true "Post page posting code response")
***
![screenshot 7](images/screenshot07.png?raw=true "My code page with many code entries")
***
![screenshot 8](images/screenshot08.png?raw=true "Access code entry page")
