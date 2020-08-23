MeetRoom
========
 MeetRoom is an application for booking conference rooms.  The main page looks like a table where the columns are days of a week and the rows are day time. Booking events look like light rectangles where you can see a title and participants. Clicking on the events you'll see a page with a detail decription of the event.

SYSTEM REQUIREMENTS.

1. Java runtime environment version 11 or above.
2. DBMS Postgresql version 10 or above.

INSTALLATION

1. Install JRE and Postgresql.
2. Unpackage the archive. All the commands should be performed from the main directory of the unpackaged archive.
3. Create and initialize a database for the application
		createdb meetroom
		psql -d meetroom -f scheme.sql
You have 3 users at start - admin:admin, filipov:123, stratonov:123. Registration is not provided. You should add other users to the database manually.
4. Open the file `/src/main/resources/application.properties` and change the properties:
	- spring.datasource.url
	- spring.datasource.username
	- spring.datasource.password
	
on your own.

5. Create the JAR using the command:
	`mvnw.cmd install` (for Windows) or
	`mvn install` (for Linux)
	
		