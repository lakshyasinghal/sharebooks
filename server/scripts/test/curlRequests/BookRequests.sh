#!/bin/sh

domain="http://localhost:8050"
apiPath="$domain/api/book-requests"
outputPath="/Users/lakshya.singhal/Desktop/Personal/Tech/Apps/sharebooks/server/scripts/test/output/BookRequests.txt"


next(){
	echo "\n\n" >> $outputPath 
	echo $1 >> $outputPath
}

create(){
	bookRequest='{"id":-1,"type":1,"status":1,"bookUid":"wleneoih23oii32p0je1j","bookOwnerUid":"wnwoeoi2eieoinowien","requesterUid":"jdbweh2h8028dh02h8d","requiredPeriod":24,"comments":"No_comments"}'
	next "Create Book Request"
	curl -X PUT -d $bookRequest -H 'Content-Type: application/json' $apiPath >> $outputPath
}


update(){
	updatedBookRequest='{"id":-1,"uid":"da445b06-10e4-41de-967c-92d406a9de09","type":1,"status":2,"bookUid":"wleneoih23oii32p0je1j","bookOwnerUid":"wnwoeoi2eieoinowien","requesterUid":"jdbweh2h8028dh02h8d","requiredPeriod":24,"comments":"No_comments"}'
	next "Update Book Request"
	curl -X POST -d $updatedBookRequest -H 'Content-Type: application/json' "$apiPath/accept" >> $outputPath
}





#login
#getAll
#getById
#create
update
