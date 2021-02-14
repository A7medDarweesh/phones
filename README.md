There are two parts for the application, backend and frontend.

# Backend

## Testing

To run the unit tests and ensure everything is OK, run the command:


./mvnw verify


## Packaging

To package the application run:

./mvnw package

# Backend

Check the readme file under the frontend folder for more information

# Packaging as Docker

Both the backend and the front end has a docker file to build them as images, to package and run both you need to use the docker compose file as follows:

docker-compose up

you can then navigate to http://localhost:9090/ to see the application in action.