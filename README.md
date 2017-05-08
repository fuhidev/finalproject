# Web-Services

This is a branch use PHP to write services

Services use php-cruid-api GNU.

#Features

##Get Json Data

  localhost/services.php?{table}/{id}

#Example:

##Get list jsondata with table usgold

```
localhost/services.php?usgold
```
Return
```
{"usgold":{"columns":["id","name","vnprice","usprice","datetime"],"records":[[1,"us",1842229.15654,408.6,"04\/12\/1989"],[2,"us",1812249.2624,403.75,"05\/12\/1989"],[3,"us",1801898.71943,402.85,"06\/12\/1989"],[4,"us",1817140.22006,406,"07\/12\/1989"],[5,"us",1826915.21182,409.35,"08\/12\/1989"],[6,"us",1862927.68799,415.5,"11\/12\/1989"],[7,"us",1846662.70577,415.5,"12\/12\/1989"],[8,"us",1845333.67921,413,"13\/12\/1989"].....................
```

##Get a jsondata with table usgold and id

```
localhost/services.php?usgold/1
```
Return

```
{"id":1,"name":"us","vnprice":1842229.15654,"usprice":408.6,"datetime":"04\/12\/1989"}
```
