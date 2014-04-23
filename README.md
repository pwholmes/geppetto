geppetto
========

This is an implementation of a compiler for a new programming language called Geppetto,
which is a term project for a class called Programming Languages and Translators at Columbia University
in Spring 2013.

This project builds the Geppetto interpreter, which executes Geppetto programs contained in a separate file.
For details on the Geppetto language itself, see the language reference in docs folder. 

The project is intended to be used in Eclipse, so the Ant build.xml included is only necessary to run
the code generators used by the project.

For Eclipse's built-in Ant to recognize the <jflex> build task, you need to add JFlex.jar to Eclipse's Ant classpath:
  a. In the Navigator or Project Explorer view, right-click the project's build.xml file and select "Properties".
  b. Depending on whether and how you've previously tried to build the project, a "launch configuration" for this
     file may already exist.  If so it will be called "geppetto build.xml".  If it exists, select it and click "Edit...".
     If it doesn't exist yet, click "New..." to create it, then on the ensuing dialog select "Ant Build" and click "OK".
  c. On the ensuing Edit Configuration dialog, select the "Classpath" tab.
  d. Select "User Entries" and click "Add JARs..."
  e. Browse to the geppetto project's tools/jflex folder, select JFlex.jar, and click OK.
  f. Click OK on each of the parent dialogs.
  There will still be a warning on build.xml about an unrecognized jar file, but the build will work.
