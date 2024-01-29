# spring-security-custom-auth-provider
This repo works as a personal guide and playground for learning about Spring Security.

## How custom Auth works?

### SecurityFilterChain

Each request made to our Spring Security application has to be processed by the SecurityFilterChain.
The SecurityFilterChain is made up several Filter each of them with a specific purpose like 
CSRF protection, CORS, different authentication methods, authorization and many others.

The SecurityFilterChain can be configured by creation a class annotated with @Configuration 
and a @Bean that returns a SecurityFilterChain instance.

## Notes for later

- Each authentication method starts with the implementation of OncePerRequestFilter.
In there we parse the request in order to create the AuthenticationToken we are going to send into
the AuthenticationManager to authenticate. Then the AuthenticationManager will return an new
Authorization instance, but now it will contain the user data and permissions, this Authorization 
will be saved in the SecurityContext and the authentication process will be finished.


- Explain how we get raw data from the Database and parse it to be usable by spring security
(from AWetUser to AWetUserDetails).


- What is the SecurityContext and how it works in detail. 