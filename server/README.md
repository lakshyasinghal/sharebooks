#command for replacing text
find . -name '*.java' -print0 | xargs -0 sed -i "" "s/java.util.logging.Logger/org.apache.log4j.Logger/g"





#Next tasks
1. Create uuid in entities
2. Add SHA-512 protection for password
3. Use prepared statements
4. Use specific custom queries
