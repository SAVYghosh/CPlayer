var express = require("express");
var app = express();
app.use(express.static(__dirname + '/dist/CPlayer/'));
app.listen(4200, function(request, response){
});