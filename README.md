# ithildin-jfx
JavaFX / Spring Boot JPA / SQLite client application for Eldamo-DB (1)

UPDATE dd. February 19, 2021

This is the second implementation of the Ithildin application, picking up where that one was left. This is what we have in mind for now:

##1: technical update

The first goal is to refactor the codebase to work with Spring Boot. There are now some approaches available to use JavaFX in a Spring Boot application. I settled on [FXWeaver](https://rgielen.net/posts/2019/introducing-fxweaver-dependency-injection-support-for-javafx-and-fxml/) by Rene Gielen.

Using Spring Boot offers a couple of advantages, like the support of the JPA framework for repository classes. It makes the whole thing a lot simpler and easier to maintain.

##2: simplify database schema and update contents to the latest version of Eldamo

As soon as the application is working with the existing SQLite version of Eldamo v.0.5.6.1, the idea is to simplify the still rather complex DB schema and update the contents with the most recent version of the Eldamo data.

##3: develop functionality to allow users to add and share their own words

The original idea behind Ithildin is to offer a dictionary tool for practical usage rather than to aim for academic thoroughness (which is what Eldamo already offers anyhow). We want to make it possible for users to add their own (reconstructed) words to the application and to share those with other users.

##4: explore other use cases
Because the data model allows for other languages besides those described by JRR Tolkien, there could be other areas where this application might prove useful.

##5: create versions for mobile platforms
 