# Checkout
Online retail checkout counter

# WorkFlow

    Create Category ----->  Create Product -----> Purchase Items -----> Get Bill for purchased Items				
    NOTE : All validations are placed according to above workflow.

# API Versioning

Used **URI versioning** technique to version the API's

# POSTMAN Collection

**Retail Online checkout Counter.postman_collection.json** (attached)

# API Security
API's are secured. Use **retail** as username and password with role **USER**

# API Details
   
- **_Create Category : This API allows us to create to categories with all necessary validation_**

   API  : {{url}}/retail/categories/v1
   
   TYPE : POST
   
   Response : HTTP 201
   
   Request payload : 
   
                     {
	                  "name":"B"
		             }
   

- **_Create Product : This API allows us to create product with all necessary valiadation_**
   
   API  : {{url}}/retail/products/v1
   
   TYPE : POST
   
   Response : HTTP 201
   
   Request payload : 
   
                     {
						"barCodeId":"2",
						"name":"prod2",
						"category":"A",
						"cost":50
		             }
                     
   
   
- **_Create Bill : This API allows us to create product bill with all necessary valiadation_**

   API  : {{url}}/retail/bills/v1
   
   TYPE : POST
   
   Request payload :
   
                 {
		 
	"purchaseItems":
	[
	
		{
			"barCodeId": "1",
			"quantity": 2.3
		}	
	]
	
                       }


   Response : HTTP 201 with 
   
                 {
				   "billindId": 1
			   }
				 
   
                     
   
- **_Get complete Bill detail : This API allows us to get all the related details of bill with all necessary validation_**

   API  : {{url}}/retail/bills/v1/17
   
   TYPE : GET
   
   Response : HTTP 200 with 
   
                    {
					"billingId": 9,
					"totalCost": 200,
					"totalSalesTax": 20,
					"totalBillAmount": 220,
					"purchaseItems": [
										{
											"productName": "prod2",
											"costPerItem": 50,
											"salesTaxPerItem": 5,
											"quantity": 2,
											"purchasedCost": 100,
											"purchasedSalesTax": 10
										},
										{
											"productName": "prod1",
											"costPerItem": 50,
											"salesTaxPerItem": 5,
											"quantity": 2,
											"purchasedCost": 100,
											"purchasedSalesTax": 10
										}
									 ]
		             }
				 

# Technology Stack
  - Java 1.7/8
  - Maven
  - Spring Boot
  - Spring JPA
  - REST
  - GIT
  - MySQL

# How to run the application locally 

- **Pre-requisites to run application**
   - Java 
   - Maven 
   - GIT
   - MySQL

- **Steps to Build and run locally**
   - Create a directory with whatever name you want
   - Clone the project using git command **git clone https://github.com/maheshmaykarkar1988/checkout.git**
   - Intall MySQL on your machine if its not there.
   - Create database
   - Update information like database name, username and password in application.properties file(Currently database name is set to retail_management with username is root and no password)
   - **mvn -Plocal clean install** Run this command through command prompt. It will executes the clean build life cycle and the install build phase in the default build life cycle
   - Go to target folder => cd target  and Run following command to start the server on port 8080=> java -jar checkout-0.0.1-SNAPSHOT.jar
        - OR
   - Go to CheckoutApplication.java file and run it. It will initilise all the necessary things required to run the project.
   - Use POSTMAN collection to invoke API's in the order of workflow given above

- **NOTE : There is no pre setup for the required data in tables hence its must to call the API's(use POSTMAN collection attached) in the workflow order**
