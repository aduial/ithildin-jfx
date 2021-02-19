# ithildin-jfx
JavaFX / Spring Boot JPA / SQLite client application for Eldamo-DB (1)

UPDATE dd. February 19, 2021

This is the second implementation of the Ithildin application, picking up where that one was left. This is what we have in mind for now: 

The first goal is to refactor the codebase to work with Spring Boot. At the time of the previous version there was no good way to combine JavaFX and Spring Boot, 
but in the meantime a few techniques have been developed to do this. I settled on [FXWeaver](https://rgielen.net/posts/2019/introducing-fxweaver-dependency-injection-support-for-javafx-and-fxml/) by Rene Gielen.

Using Spring Boot offers a couple of advantages, like the support of the JPA framework for repository classes. It makes the whole thing a lot simpler and easier to maintain.
  
As soon as the application is working with the existing SQLite version of Eldamo v.0.5.6.1, the idea is to simplify the (quite complex) schema and update the contents with the most recent version of the Eldamo data.


First goal is to refactor the codebase to work with Spring Boot. At the time of the previous version there was no good way to combine JavaFX and Spring Boot, but in the meantime

The application can search the word forms of all of the Tolkienian languages available in Eldamo OR in the English glosses.

(Partial) matches are displayed in the table under the search field, with both the form and the gloss.

Entries marked as private constructions are shown in green.

When a row in the match table is clicked, the detailed data associated with that particular word entry are fetched from the database and displayed in the area to the right. 

By défault only the headline (language indicator, form, speech & gloss) are displayed plus the Glosses block. With the toggle buttons below, the visibility of all these blocks can be controlled : 

* References
* Glosses
* Derivations
* Inflections
* Elements
* Cognates

_(the Variations, Related and Changes blocks are still TBD)_

- In the menu, under Edit => Edit / Manage Dataset is a mocked-up screen to illustrate how we think the application could also be used to:

-- administer the (curated) central reference database: edit the data, add new entries or correct existing ones (2); 

-- functionality to synchronise the user's database with the central reference database; 

-- functionality to upload suggestions to the central reference database (to be reviewed before adding);

-- for the local user to add their own private entries (reconstructions etc.); 

-- functionality to export / share such private entries with other users;

-- functionality to import such private entries received from others

-- etc (open for suggestions ...)


If you want to build this project:
though there's still an .idea subdirectory in here, I switched to using Netbeans because Intellij is payed software for which I happen to have a license, but not everyone does, while Netbeans is free. 

If you issue a "clean and build" command, the libraries are packaged in the _Ithildin-dist.jar_ under the dist directory. 
The _eldamo.sqlite_ database file is not packaged in there because that would render it impossibe to write to it. To distribite the application, put a copy of the _eldamo.sqlite_ file together with the _Ithildin-dist.jar_ the same directory as the Ithildin.jar file; open your command line / terminal program, navigate to that directory and enter

*java -jar Ithildin-dist.jar*

If you want to join this development let me know via the Skype discussion group or on the Languages of Middle-Earth group.

Enjoy!


= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

(1) it is not clear at this point how to implement this central database, or whom should curate this. The easiest possibility 
would be to stick to the Eldamo data set and keep using that as the reference set, but possibly people should want to fork it
and create a separate central database. It's not clear at this point what everybody wants, or what even would be doable. 
Suggestions are more than welcome here as well, and it's probably best to discuss this on the Languages of Middle-Earth Google 
group instead of, say, adding comments here on Github. 
