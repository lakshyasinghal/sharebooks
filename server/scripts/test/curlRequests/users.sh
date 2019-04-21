#!/bin/sh

domain="http://localhost:8050"
apiPath="$domain/api/users"
outputPath="/Users/lakshya.singhal/Desktop/Personal/Tech/Apps/sharebooks/server/scripts/test/output/users.txt"


next(){
	echo "\n\n" >> $outputPath 
	echo $1 >> $outputPath
}


login(){
	username='lakshyasinghal333@gmail.com'
	password='champion'
	next "Login"
	curl -X POST -d 'username=lakshyasinghal333@gmail.com&password=champion' "$apiPath/login" >> $outputPath
}

getAll(){
	next "Get All Users"
	curl -X GET "$apiPath" >> $outputPath
}


getById(){
	id=5
	next "Get User By Id"
	curl -X GET "$apiPath/$id" >> $outputPath
}

create(){
	newUser='{"id":-1,"username":"lakshyasinghal3@gmail.com","password":"laksh","name":"LakshyaSinghal","dob":"1990-03-02","address":"houseno.%2074%20Block%20B%20sec-11","city":"faridabad","state":"Haryana","pincode":"121006","age":29,"contactNo":"8826387791","preferences":"[{\"category\":\"science\"},{\"category\":\"politics\"}]","active":1}'
	next "Create User"
	curl -X PUT -d $newUser -H 'Content-Type: application/json' $apiPath >> $outputPath
}


update(){
	updatedUser='{"id":1,"username":"lakshyasinghal3@gmail.com","password":"laksh","name":"LakshyaSinghal","dob":"1991-03-02","age":28,"contactNo":"8448054935","active":0}'
	next "Update User"
	curl -X POST -d $updatedUser -H 'Content-Type: application/json' $apiPath >> $outputPath
}





#login
#getAll
#getById
create
#update






