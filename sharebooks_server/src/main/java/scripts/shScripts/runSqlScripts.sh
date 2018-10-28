applicationPath="/Users/lakshya.singhal/Desktop/Development/Personal/Java_Apps/sharebooks"
sourcePath="$applicationPath/src/main/java/scripts/com/sharebooks/scripts/sqlDBScripts"

dbName="Sharebooks"

sqlFiles[0]="States.sql"
sqlFiles[1]="Cities.sql"
sqlFiles[2]="Users.sql"
sqlFiles[3]="Books.sql"
sqlFiles[4]="BookRequests.sql"
sqlFiles[5]="Orders.sql"
sqlFiles[6]="Feedbacks.sql"
sqlFiles[7]="Complaints.sql"
sqlFiles[8]="Subscriptions.sql"
sqlFiles[9]="BookCategories.sql"
sqlFiles[10]="Subjects.sql"


function runSqlScripts {
	echo "Running scripts"
	
	for t in "${sqlFiles[@]}"
	do
		echo
		echo "running $t ..."
		mysql -u root -proot $dbName < $sourcePath/$t
	done
		echo "process completed."
		echo
}


runSqlScripts










