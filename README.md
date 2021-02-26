# ithildin-jfx
JavaFX / Spring Boot JPA / H2 client application for Eldamo-DB (1)

UPDATE dd. February 19, 2021

This is the second implementation of the Ithildin application, picking up where that one was left. 
This is what we have in mind for now:

## 1: technical update

The first goal is to refactor the codebase to work with Spring Boot. There are now some approaches 
available to use JavaFX in a Spring Boot application. 
I settled on [FXWeaver](https://rgielen.net/posts/2019/introducing-fxweaver-dependency-injection-support-for-javafx-and-fxml/) 
by Rene Gielen.

Using Spring Boot offers a couple of advantages, like the support of the JPA framework for 
repository classes. It makes the whole thing a lot simpler and easier to maintain.

I switched from SQLite to H2 for database engine, because the latter is written in Java and very easy
to use with Spring Boot.

SQLite is written in C and probably faster, but it being platform dependent might complicate packaging the 
application which is already more than daunting as it is. Besides, nobody would notice the difference 
in database performance with this app.

## 2: simplify database schema and update contents to the latest version of Eldamo

As soon as the application is working with the existing SQLite version of Eldamo v.0.5.6.1, 
the idea is to simplify the still rather complex DB schema and update the contents with the most 
recent version of the Eldamo data.

## 3: develop functionality to allow users to add and share their own words

The original idea behind Ithildin is to offer a dictionary tool suited for both practical / creative 
and academic usage.
We want to make it possible for users to add their own (reconstructed) words. The idea is to store
these separate from the Eldamo data, so that there would essentially be three categories:

* the original attested material from the work of JRR Tolkien (available in Eldamo)
* Neo-eldarin words generally accepted by the community (also available in Eldamo)
* newly added material for personal use or to share with those interested (not in Eldamo)
  
We've had some discussions on how material from the third category could eventually be moved to the 
second one, but this is still very much open.

## 4: explore other use cases
Because the data model allows for other languages besides those described by JRR Tolkien, 
there could be other areas where this application might prove useful.

## 5: create versions for mobile platforms
Candidate platform: [Gluonhq]( https://gluonhq.com/products/mobile/)
 
