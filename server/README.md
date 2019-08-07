#command for replacing text
find . -name '*.java' -print0 | xargs -0 sed -i "" "s/java.util.logging.Logger/org.apache.log4j.Logger/g"



#Improvements
1. When sql server is not started the application needs to throw an exception

#Next tasks
1. Create uuid in entities                 -----------    done
2. Add SHA-512 protection for password     -----------    done
3. Use prepared statements				   
4. Use specific custom queries
5. Use transactions                        -----------    done


#Extra utilities
1. Authentication 
2. Authorization
3. HikariCP
4. Server side pagination


#Authentication Link
https://javapapers.com/web-service/restful-services-http-basic-authentication/


#Database links
http://tutorials.jenkov.com/jdbc


#Today's Tasks
1. Search sql query
2. Integrate notifications
3. Redis cache
4. Book redis cache
5. Search proposal
6. Fetch results















#WEEKEND TASKS
1. Select book request
2. Fetch results
3. Confirm book request
4. Finalize requirements
5. Place Order
6. Feedback (including tables and everything else)


#Steps
BookViewQuote
BookProposal
BookOrder



#Current Status
Completed quote and book request api's. Need integration with client side.


Jar command
java -jar xyz.jar arg0 arg1


API Links

Geocoding => https://rapidapi.com/googlecloud/api/google-maps-geocoding

Sending sms => http://api.textlocal.in/docs/sendsms

Url shortener => https://dev.bitly.com/links.html

Git merge => https://git-scm.com/book/en/v2/Git-Branching-Basic-Branching-and-Merging

NoSql => https://beginnersbook.com


JUnit => https://www.vogella.com/tutorials/JUnit/article.html
Redis => https://auth0.com/blog/introduction-to-redis-install-cli-commands-and-data-types/

Mongo CRUD => https://www.journaldev.com/3963/mongodb-java-crud-example-tutorial

Mongo => https://docs.mongodb.com/manual/core/transactions/

JMeter => https://www.blazemeter.com/blog/getting-started-jmeter-basic-tutorial/
