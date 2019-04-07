#command for replacing text
find . -name '*.java' -print0 | xargs -0 sed -i "" "s/java.util.logging.Logger/org.apache.log4j.Logger/g"