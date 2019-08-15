# mydictionary
Its a maven based springboot project using thymeleaf. This allows to create a dictionary in embedded MongoDB. Steps to use this
* clone the repo
* mvn spring-boot:run
* Access http://localhost:8080
* It will open a page which will allow you to upload a file (initial dictionary).
- Assumption: The file should contain word and their meanings (comma seperated) seperated by one space for example
**good good,likeable**
**bad bad,nonlikeable**

* Rest api to search is
http://localhost:8080/search/good

Sope of improvements:
Testcases
Better UI
Dockerization
