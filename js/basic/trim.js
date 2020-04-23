
//供使用者调用  
function trim(s){  
    return trimRight(trimLeft(s));  
}  

//去掉左边的空白  
function trimLeft(s){  
    if(s == null) {  
        return "";  
    }  
    var whitespace = new String(" 　\t\n\r");  
    var str = new String(s);  
    if (whitespace.indexOf(str.charAt(0)) != -1) {  
        var j=0, i = str.length;  
        while (j < i && whitespace.indexOf(str.charAt(j)) != -1){  
            j++;  
        }  
        str = str.substring(j, i);  
    }  
    return str;  
}  

//去掉右边的空白 
function trimRight(s){  
    if(s == null) return "";  
    var whitespace = new String(" 　\t\n\r");  
    var str = new String(s);  
    if (whitespace.indexOf(str.charAt(str.length-1)) != -1){  
        var i = str.length - 1;  
        while (i >= 0 && whitespace.indexOf(str.charAt(i)) != -1){  
           i--;  
        }  
        str = str.substring(0, i+1);  
    }  
    return str;  
}


var s = "　中国\t";
var s_trim = trim(s);
console.log(s_trim)
console.log("aaa")