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


Coding conventions:
1. Use the code-format.xml in the project root.  (Right-click the project name, select "Properties",
   expand "Java Code Style", select "Formatter", enable "Enable Project Specific Settings", click "Import...",
   and select the code-format.xml file.)  To format your code with the formatter, press Ctrl-Shift-F.

2. Make names descriptive.  Don't abbreviate names to acronyms.  It costs nothing to have a fully
   descriptive name and makes the code much more readable.  For example, instead of naming a variable "AST",
   name it "abstractSyntaxTree".  (The name "AST" also violates capitalization conventions, but that's another 
   issue.)  If you find it an onerous task to type out long variable names, learn to make use of Eclipse's 
   auto-completion feature, activated by Ctrl-space. 

3. Make use of private default constructors.  A lot of times you only want a class to be constructed using a
   parameterized constructor, or via a factory method.  In that case you want to prevent anyone from creating 
   the class using the default constructor (the one that has no parameters.)  To do that, just define it as 
   private and give it an empty body.  (e.g., "private MyClass() {}")
  
4. Use the domain package.  This is where you should place classes that implement app-specific objects: "things"
   as opposed to "functionality".  For example, in our app, the Attribute, Entity and Rule classes go here.  The 
   use of a domain package is a common paradigm when you're working with frameworks list Struts, but I've found 
   it makes sense just about anywhere.

 