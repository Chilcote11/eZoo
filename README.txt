***************************************************
* Project: 		eZoo 
* Last Updated: August 2019
***************************************************
*
* Contents:	
*	- Application Description
*	- Tools and Frameworks used
*	- Deployment Instructions
		Maven
* 	- Licenses
*
***************************************************

1.  Application Description: 

	This is the eZoo application!  A JavaEE MVC application designed to manage a virtual zoo’s animals and events with an easy-to use user interface for employees and customers

	Revature, a company committed to training entry-level software developers, provided the original template for this project (now making up around 10% of the overall application).  A few instructional videos and several hundred Google searches later, the first version of this application was birthed (see branch `eZoo100`).  From there the application has been, and is still in the process of being upgraded to utilize the tools and frameworks listed below.

	Though unlikely to ever benefit a real zoo, this project has served me well as a thorough introduction to Java development.


2. Tools and Frameworks used

	Developed using Eclipse, Maven, PostgreSQL, HTML & CSS (bootstrap, mostly) JSPs, and JDBC for data access objects
	Recently upgraded to use Spring and Hibernate instead of JDBC, greatly simplifying the data access layer
	Currently implementing Spring Framework’s MVC and security features, Log4J for logging, and JavaScript to improve JSPs


3. 	Deployment Instructions (unfinished, more to come!)

	To run this application, you'll need Jav8 or later, Eclipse, Wildly server, and PostgreSQL.
		Instructions for installing these technologies can be found in SETUP.docx
		
	You'll also need Maven 3 if running anything after version 1 (branch: eZoo100)
		Be aware that version 2 is still in development, so some functionality may not yet be implemented
			

3.1	Maven 3 installation
	
	Linux Installation with `apt`:
	- sudo apt install maven
	
	Windows Installation
	- Download the binary zip archive from: https://maven.apache.org/download.cgi
	- Extract files to some known location, could be C:\Program Files\Maven\
	- Add `C:\Program Files\Maven\apache-maven-3.*.*\bin` to the system `PATH` variable
	- mvn --version in a new terminal window should now show the Maven 3 version installed as well as the Java version it recognizes


4	Licenses

4.1	eZoo
	
	eZoo is available under the Revature license. You are free to use eZoo for educational purposes.

4.2	DataTables
	
	DataTables is available under the MIT license. In short, this means that you are free to use DataTables as you wish, including modifying and redistributing the code, as long as the original copyright notice is retained.

	MIT license
	Copyright (C) 2008-2015, SpryMedia Ltd.

	Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
