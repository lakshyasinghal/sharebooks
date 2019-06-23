#!/bin/sh

username='lakshya'
pwd='lakshya'

db_dir="/home/lakshya/Tech/Apps/sharebooks/server/sql/schema/dev/databases/"

#Sharebooks_Analytics
#Sharebooks_AWS
#Sharebooks_Config
Core[0]="BookRequests.sql"
Core[1]="Books.sql"
Core[2]="Notifications.sql"
Core[3]="Orders.sql"
Core[3]="Quotes.sql"

#Sharebooks_Master=("BookCategories.sql" "Cities.sql" "States.sql")
#Sharebooks_SMS 
#Sharebooks_SystemTracking
#Sharebooks_UserAccounts=("Users.sql")
#Sharebooks_UserExperience

createDatabases(){
	mysql -u $username -p$pwd < create_databases.sql
}

create_tables(){
	create_core_tables
}



create_core_tables(){
	echo "creating core tables...\n"
	for table_script in "${Core[@]}"
	do
		echo "creating $table_script...\n"
		mysql -u $username -p$pwd Sharebooks_Core < "$db_dir/Core/$table_script"
	done
}



#createDatabases
create_tables
#test