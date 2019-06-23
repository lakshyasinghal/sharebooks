# !/bin/bash

username='lakshya'
pwd='lakshya'

db_dir="/home/lakshya/Tech/Apps/sharebooks/server/sql/schema/dev/databases/"

sharebooks_core=(BookRequests.sql Books.sql Orders.sql Quotes.sql)
#Sharebooks_Analytics
#Sharebooks_AWS
#Sharebooks_Config
sharebooks_master=(BookCategories.sql Cities.sql States.sql)
#Sharebooks_SMS 
#Sharebooks_SystemTracking
sharebooks_userAccounts=(Users.sql Subscriptions.sql)
sharebooks_userExperience=(Complaints.sql Feedbacks.sql)


# for name in "${sharebooks_core[@]}"
# do
# 	echo $name
# done


createDatabases(){
	mysql -u $username -p$pwd < create_databases.sql
}

create_tables(){
	create_core_tables
	create_master_tables
	create_uesr_account_tables
	create_user_experience_tables
}



create_core_tables(){
	echo "creating core tables...\n"
	for table_script in "${sharebooks_core[@]}"
	do
		echo "creating $table_script...\n"
		mysql -u $username -p$pwd Sharebooks_Core < "$db_dir/Core/$table_script"
	done
}

create_master_tables(){
	echo "\n\ncreating master tables...\n"
	for table_script in "${sharebooks_master[@]}"
	do
		echo "creating $table_script...\n"
		mysql -u $username -p$pwd Sharebooks_Master < "$db_dir/Master/$table_script"
	done
}

create_uesr_account_tables(){
	echo "\n\ncreating user accounts tables...\n"
	for table_script in "${sharebooks_userAccounts[@]}"
	do
		echo "creating $table_script...\n"
		mysql -u $username -p$pwd Sharebooks_UserAccounts < "$db_dir/UserAccounts/$table_script"
	done
}

create_user_experience_tables(){
	echo "\n\ncreating user experience tables...\n"
	for table_script in "${sharebooks_userExperience[@]}"
	do
		echo "creating $table_script...\n"
		mysql -u $username -p$pwd Sharebooks_UserExperience < "$db_dir/UserExperience/$table_script"
	done
}



#createDatabases
create_tables