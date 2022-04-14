# CodeShare

## Table of contents
* [About](#about)
* [Features](#features)
* [Usage](#usage)
* [Installation](#installation)
* [Technologies used](#technnologies-used)
* [Screenshots](#screenshots)


## About

CodeShare is a simple web service which main goal is to improve your workflow by providing an easy way to share text/code with others in local environments.

## Features

- use this service with your browser or through API
- post code with different types of restrictions
- access other people's code/text with universally unique identifier (uuid)
- edit posted code directly through browser if it's set as editable
- store your private code with 'private' restriction ensuring nobody will access it 
- easily access every code you posted

## Usage

After running this service with command line/terminal you can use it through browser (by default by opening `https://localhost:8389/` page) or through API.

### Registration

- In order to use this service you must be registered
- By default, only home page and api page are accessible without logging in
- To register you only need to specify your username and password

### Posting code

- You can post code and set it's: name, number of views allowed, time availability, private and editable status
- Name and code field must not be empty
- Restrictions are optional
- By default, code is set as non-editable

### Accessing/editing code

- To access code you need to have it's uuid
- If you are code's creator you can also access it directly from my-code page
- If code is set as editable anyone with uuid can make changes and save them
- If code is set as non-editable only it's creator can make changes
- Code set as private can be accessed only by its creator


### API

Every endpoint returns Response object. Apart from `/api/register` every request should be authenticated with username and password.

- /api/code
	* Return code with given uuid
	* Request parameters: String uuid
	* Request body: none

- /api/post
	* Post given code
	* Request parameters: none
	* Request body: Code object

- /api/delete
	* Delete code with given uuid
	* Request parameters: String uuid
	* Request body: none

- /api/update
	* Update given code
	* Request parameters: none
	* Request body: Code object

- /api/my-code
	* Return list of code posted by me
	* Request parameters: none
	* Request body: none

- /api/register
	* Register given user
	* Request parameters: none
	* Request body: User object

#### API Objects:

- Response
	* String result
	* String message
	* String uuid
	* Code code

- Code
	* String name (required)
	* String code (required)
	* boolean setAsPrivate
	* boolean editable
	* int viewsAllowed
	* long minutesAllowed

- User
	* String name (required)
	* String password (required)


## Installation

1. Import this repository to some folder with `git clone repourl`
2. Open this folder and install with `mvn clean install`
3. In `target` folder there will be executable jar file `codeshare.jar` which you can move freely and run with `java -jar codeshare.jar`

## Technologies used

- Java 17
- Spring Boot 2.6.4
- Spring Security 5.6.2
- H2 database 1.4.200
- Thymeleaf 3.0.15

## Screenshots

![screenshot 1](images/screenshot01.png?raw=true "Using service with browser 1")
![screenshot 2](images/screenshot02.png?raw=true "Using service with browser 2")
![screenshot 3](images/screenshot03.png?raw=true "Using service with browser 3")
![screenshot 4](images/screenshot04.png?raw=true "Using service with browser 4")
![screenshot 5](images/screenshot05.png?raw=true "Using service with browser 5")
![screenshot 6](images/screenshot06.png?raw=true "Using service with browser 6")
![screenshot 7](images/screenshot07.png?raw=true "Using service with browser 7")
![screenshot 8](images/screenshot08.png?raw=true "Using service with browser 8")