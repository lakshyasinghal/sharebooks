#!/bin/sh

domain="http://localhost:8050"
apiPath="$domain/api/books"
outputPath="/Users/lakshya.singhal/Desktop/Personal/Tech/Apps/sharebooks/server/scripts/test/output/books.txt"

#function to insert gap between output
next(){
	echo "\n\n" >> $outputPath 
	echo $1 >> $outputPath
}

create(){
	book='{"ownerId":1,"title":"Data%20Structures%20And%20Algorithms","authorName":"Narsimha","category":"Computer%20Science","subcategory":"Data%20Structures","pages":382,"imgSrc":"/static/book7.jpg","available":1,"buy":1,"rent":1,"buyAmount":400,"rentAmount":5}'
	next "Create Book Request"
	curl -X PUT -d $book -H 'Content-Type: application/json' $apiPath >> $outputPath
}

getAllRequest(){
	next "Get All Books Request"
	curl -X GET $apiPath >> $outputPath
}


getBookById(){
	next "Get Book By Id"
	curl -X GET "$apiPath/9" >> $outputPath
}

update(){

}


create
getAllRequest
getBookById