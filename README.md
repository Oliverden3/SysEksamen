In order for the startcode to work you have to do the following steps:
1. Add a tomcat local configuration version 9. 
2. go to the pom.xml file and change the name of the project, artifact id and db name so that it alligns with the ones you'll be using.
3. Check that everything is in order in the mavenworkflow file, make sure you're using the right branch and that the passwords are correct 
4. Go into the persistence.xml file and check that it uses the correct database and set up the right passwords