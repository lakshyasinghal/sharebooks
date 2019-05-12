const staticRouter = require("./static/staticRouter");
const userRouter = require("./dynamic/userRouter");
const bookRouter = require("./dynamic/bookRouter");
const notificationRouter = require("./dynamic/notificationRouter");
const fileUploader = require("./dynamic/fileUploader");
const miscRouter = require("./dynamic/miscRouter");
const resultRouter = require("./dynamic/resultRouter");
const quoteRouter = require("./dynamic/quoteRouter");

module.exports = {staticRouter,userRouter,bookRouter,notificationRouter,fileUploader,miscRouter,resultRouter,quoteRouter};