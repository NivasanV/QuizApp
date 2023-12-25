A Simple Quiz Application Web Service

API URIs

---- Question Service ----

GET		- http://localhost:8080/question/allQuestions
GET		- http://localhost:8080/question/category/:category
POST	- http://localhost:8080/question/add	[Response Body]
PUT		- http://localhost:8080/question/update	[Response Body]
DELETE	- http://localhost:8080/question/delete/:id

---- Quiz Service ----

POST	- http://localhost:8080/quiz/create?category=&numQ=&title= [Response Body]
GET		- http://localhost:8080/quiz/get/:id
POST 	- http://localhost:8080/quiz/submit/:id [Response Body]