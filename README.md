App42_BPaaS_JAVA_SDK
==============

App42 BPaaS Cloud API Client SDK JAR files for JAVA

- Download the App42 BPaaS JAVA SDK
- Unzip the file and open **App42 JAVA Sample** project.
- Add the **App42_BPaaS_JAVA_SDK_x.x.x.jar** into library path. **Properties -> Java Build Path -> Liabraries -> Add External JARs**
- Initialize the library using :-

```
ServiceAPI sp = new ServiceAPI("<YOUR_API_KEY>","<YOUR_SECRET_KEY>");
sp.setBaseURL("<YOUR_API_SERVER_URL>");
```

- Instantiate the service that one wants to use in the App, e.g. using User service one has to do the following :-

```
UserService userService = sp.buildUserService();
```

- Now one can call associated method of that service e.g. user creation can be done with the following snippet :-

```
String userName = "Nick";
String pwd = "********";
String emailId = "nick@shephertz.co.in";    
User user = userService.createUser(userName, pwd, emailId); 
System.out.println("userName is " + user.getUserName());
System.out.println("emailId is " + user.getEmail());
```

- Build the project and run.
