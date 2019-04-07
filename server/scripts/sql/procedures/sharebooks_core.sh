#!/bin/sh

appPath="/Users/lakshya.singhal/Desktop/Personal/Tech/Apps/sharebooks"
sqlPath="$appPath/server/sql/procedures"
sqlFile="sharebooks_core.sql"


runSqlScript() {
	echo "moving to sql file path $1"
	cd $1
	echo "running file $2"
	mysql -u lakshyasinghal33 -p sharebooks < $2
}




runSqlScript $sqlPath $sqlFile 