# Simple Ticketing Service

A simple ticket service that facilitates the discovery, temporary hold, and final reservation of seats within a high-demand performance venue.

### Example Stage:
```aidl
        ----------[[  STAGE  ]]----------
        ---------------------------------
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
```

### Features 

1. Find the number of seats available within the venue
⋅⋅⋅Note: available seats are seats that are neither held nor reserved.
2. Find and hold the best available seats on behalf of a customer
⋅⋅⋅Note: each ticket hold should expire within a set number of seconds.
3. Reserve and commit a specific group of held seats for a customer.


# Building the project using Maven

1. First, Download the project from Github. Here is link. https://github.com/dhavallad/SimpleTicketService/archive/master.zip or clone the repo using this command       
       
        git clone https://github.com/dhavallad/SimpleTicketService.git
       
2. Go to project root folder and open terminal at this folder.
3. Now, change directory using cd command to directory which contain pom.xml file.
4. If project doesn't have built jar file, build the project using below command.     
       
           mvn clean install 
        
5. I have created a sample scenario for reserving and holding the seats.
6. Run the tests using following command
       
           mvn test
