# Revolut Money Transfer

##### Application Developed by Biniam Asnake Kefale

# Workflow
1. User creates account
2. User checks account information
3. User can edit or delete account
4. User can transfer money if they have enough balance
5. User can view money transfers

# Out of Scope
* Implementing Security/Authentication
* Connect to real Database
* UI design

# Build using Jetty and create UberJar
`
mvn clean package
`

# Run the UberJar
`
java -jar target/money-transfer.jar
`

# Roadmap
* Increase test coverage
* Implement security
* Integrate to a persistence datastore
* Generate Swagger API documentation