# Spring File Mutation
Build a Spring Boot 2+ Service using Maven that consumes a xml file(see `data.xml`) and returns a response containing information about the individual entries in the xml file.

## Information 
The `data.xml` is broken into individual sets of information identified by the `</PersAutoPolicyQuoteInqRq>`tag.   Assume this solution will be "production ready" and should contain propper logging and test coverage. Keep in mind that xml file sizes may varry so scalability should be considered.

The xpaths to build the responses can be found bellow : \
Policy Number - `//PolicyNumber`\
Customer Name - `//InsuredOrPrincipal[InsuredOrPrincipalInfo/InsuredOrPrincipalRoleCd='Insured']/GeneralPartyInfo/NameInfo/CommlName/CommercialName`\
Policy Type - `//PersPolicy/LOBCd`\
Total Premium - `//PersPolicy/CurrentTermAmt/Amt` \
Vehicle Collection - `//PersAutoLineBusiness/PersVeh`\
Driver Collection - `//PersAutoLineBusiness/DriverInfo`


## Response Format 
The response should contain an array of the following information extracted from the file and a count of the number of entries: 
```json
{
"policyNumber": String,
"customerName": String,
"policyType": String,
"totalPremium": Float,
"vehicles": [{
"make" : String,
"model": String,
"modelYear": String
}...],
"drivers" : [{
"driverName": String,
"age": Integer
}...]
```


## Run service  
`mvn spring-boot:run`

## References
* [Stax tutorial](https://www.baeldung.com/java-stax)  

## Install Certs
1. Get certificates
	* Use a browser to go to  https://repo.maven.apache.org/
	* Click on lock icon and choose "View Certificate"
	* Go to the "Details" tab and choose "Save to File"
	* Choose type "Base 64 X.509 (.CER)" and save it somewhere
	* Now open a command prompt and type (use your own paths):  
	`"$JAVA_HOME"/bin/keytool -import -noprompt -trustcacerts -alias mavenroot -file mavenCert.cer -keystore "$JAVA_HOME"/jre/lib/security/cacerts`