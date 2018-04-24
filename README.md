# Revolut Money Transfer

##### Application Developed by Biniam Asnake Kefale

# Workflow
1. User creates account
2. User checks account information
3. User can edit and delete account
4. User can transfer money if they have enough balance
5. User can view money transfers
6. User can edit and delete transfer

# Out of Scope
* Implementing Security/Authentication
* Connect to real Database
* UI design

# Run the application
You can run the already build and generated `money-transfer.jar` which can be found at the main directory of the repository.

# If you want to do the build and running yourself
## Build using Jetty and create UberJar
`
mvn clean package
`

## Run the UberJar
`
java -jar target/money-transfer.jar
`

# Roadmap
* Increase test coverage
* Implement security
* Integrate to a persistence datastore

# Documentations 
1. [Postman API documenation and sample API calls](https://documenter.getpostman.com/view/72049/revolut-money-transfer/RW1YoLPj#82bcb755-a175-43fc-99e5-d282bec2035f)
2. Swagger API documentation can be found at
- `swagger.json` file in the main directory of the repository
- By running the application and accessing `http://localhost:8080/api/swagger.json`
- By running the application and accessing `http://localhost:8080/` and exploring for `api/swagger` from the Swagger UI.
3. Code level documentation



