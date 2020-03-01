#Description Project
This project provides the funcions that are described in the WAES Assignment document and my own assumptions
#Assignment Functions 
	-Expose two end point to store a JSON Encode Base 64
	-Expose one end point to get the difference between two JSONs looked by ID and return it in a JSON
#Assumptions
	-I suppose you want a explanation about my code, that's why my service class is commented line by line
	-I suppose is basic have a fourth end point to get all of my stored differences in one service
	-The response JSON to get the differences will just return the not null properties
	-We can receive in the Storage services any kind of JSON Structures
	-If I receive a JSON structure in a not null side, the new structure will overwrite the last structure
	-If I receive a JSON structure equal in the same side, the store process will be omitted
	-The Rest services are closed just to receive application/json content type
	-The application will generate a log file "MsDecoder.log"
	-Empty or null Id are not allowed
	-If the structure isn't decodeable the service will return a Bad Request error
	-There's not limit to the Structures that we can store
	-The stored structures just will remain during the Run Time
	-Any kind of DB or Storage service won't be implemented
	-The default port of the Sping Boot is the 9999
#Development Tools
	-Java 8
	-Gradle 6
	-Eclipse
#Libraries
	-Spring CORE
	-Spring Boot
	-Junit
	-Google GSON #to manipulate JSON Structures
	-Lombok
	-Swagger
#Deployment
	To deploy this project is necessary do the following steps:
	-Clone the project from my GitHub Repository https://github.com/ChapoJR/MsDecoder.git
	-Import the Project to you favorite IDE as a Gradle Project
	-Download de lombok library https://projectlombok.org/download
	-Execute the lombok.jar
	-Select the path of your IDE
	-Build the project
#Other
	-Swagger URL: http://localhost:9999/swagger-ui.html
	-Postman Collection: In the root of the project you can find a Postman Test Collection for the services, WAES.postman_collection.json