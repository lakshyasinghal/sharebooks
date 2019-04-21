#command for replacing text
find . -name '*.java' -print0 | xargs -0 sed -i "" "s/java.util.logging.Logger/org.apache.log4j.Logger/g"



#Improvements
1. When sql server is not started the application needs to throw an exception

#Next tasks
1. Create uuid in entities
2. Add SHA-512 protection for password
3. Use prepared statements
4. Use specific custom queries
5. Use transactions



#Extra utilities
1. Authentication 
2. Authorization
3. HikariCP
4. Server side pagination


#Authentication Link
https://javapapers.com/web-service/restful-services-http-basic-authentication/


#Database links
http://tutorials.jenkov.com/jdbc