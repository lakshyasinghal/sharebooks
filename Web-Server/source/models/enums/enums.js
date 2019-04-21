

const REQUEST_TYPE = {
	POST: "POST",
	GET: "GET",
	PUT: "PUT",
	DELETE: "DELETE"
}

const CONTENT_TYPE = {
	JSON:"application/json",
	FORM:"application/x-www-form-urlencoded",
	PLAIN_TEXT:"text/plain"
};

const REQUEST_ERROR_TYPE = {
	CONNECTION_ERROR:"CONNECTION_ERROR",
	UNKNOWN:"UNKNOWN"
};


const STATUS_CODES = {
	SESSION_EXPIRED:1300
};
//the error codes should be in sync with the backend error codes and must not override them as it will lead to an inconsistent state
const ERROR_CODES = {
	SOMETHING_WRONG:1200,
	CONNECTION_ERROR: 1100,
	UNKNOWN: 1101,
	MYSTERIOUS:1102
};

module.exports = {REQUEST_TYPE,CONTENT_TYPE,REQUEST_ERROR_TYPE,ERROR_CODES};