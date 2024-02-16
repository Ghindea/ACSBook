# ACSBook — Mini Social Media Platform
Homework realized for OOP course, second year, FACC-CSIT

made by: _[Ghindea Daniel - Teodor](https://github.com/Ghindea) 325CB_
[requirements](./Tema1-2023.pdf)[&description](./Tema1-DocumentațieComenzi-2023.pdf)

---

### Synopsis:

This program consists of five important classes that define the properties of the objects used and their functionality. 
- `App class` : Being the core of this program, App class has three important roles:
  
  - it parses command line arguments to the program;
  - according to the given arguments, it calls a specific function to perform the required task;
  - it has a unique static member `acsBook` that holds all the information of the platform (hence `Database` class). 
- `User class` : 
  - Defines all the properties of platform users like their userName, password and a series or lists for their followers, followed users, posts, comments & liked posts.
  - Defines behaviors for the users, like creating posts, adding or removing users to their following list, determining the total number of likes received or sorting their posts by date descending
- `Post class`

  - Defines the properties of the posts like their content, owner, date, number of likes and holds a list of comments
  - Has methods that determine if a post is owned or liked by a certain user, add or remove comments or increment/decrement the number of likes and comments  
  - Has a static int member that counts the `numberOfPosts` to give every post a unique ID
- `Comment class`

    - Similar to a post, a comment has properties like content, owner, date, an associated post and the number of likes
    - Has similar methods to Post class for liking action
    - It also has a static int member, `numberOfComments`, for ID identification purposes
- `Database class`

    - Its purpose is to define how data is stored for processing, similar to a querry sistem 
    - It has three main lists: `usersList`, `postsList`, `commentsList` that holds all the required information
    - It has methods that operate on these lists which can determine the existence of any type of object in database, sort posts or rank posts/users on different criteria 
