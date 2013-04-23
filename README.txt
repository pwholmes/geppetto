This project has an Ant build.xml that uses Eclipse's build-in Ant builder.
It's kind of a half-measure: halfway between only using Eclipse's built-in builder,
and only using Ant.  It would be more portable and generic to use an external copy 
of Ant and eschew Eclipse's builder altogether (that way you could build the project 
without requiring Eclipse), but at the moment we have other fish to fry.
I'll fix it later.
  
So for now some manual configuration is necessary for the build.xml to work:

1. For Eclipse's built-in Ant to recognize the <jflex> build task, you need to add JFlex.jar to Eclipse's Ant classpath:
  a. In the Navigator or Project Explorer view, right-click the project's build.xml file and select "Properties".
  b. Depending on whether and how you've previously tried to build the project, a "launch configuration" for this
     file may already exist.  If so it will be called "geppetto build.xml".  If it exists, select it and click "Edit...".
     If it doesn't exist yet, click "New..." to create it, then on the ensuing dialog select "Ant Build" and click "OK".
  c. On the ensuing Edit Configuration dialog, select the "Classpath" tab.
  d. Select "User Entries" and click "Add JARs..."
  e. Browse to the geppetto project's tools/jflex folder, select JFlex.jar, and click OK.
  f. Click OK on each of the parent dialogs.
  There will still be a warning in build.xml about an unrecognized jar file, but the build will work.

