----LOCALLY HOSTING DATABASE INSTRUCTIONS----
  1. download xampp
  2. in xampp, start apache and mySQL
  3. in browser, enter domain localhost/phpmyadmin
  4. create new database called "kingdump" without the quotes
  5. in database, click SQL tab.
  6. copy file tableBuilder.sql into the text box, then click go
  8. copy justBones directory into C:\xampp\htdocs
  9. open command prompt and navigate to C:\xammp\htdocs\justBones
  10. enter command "php -S localhost:8080 -t public public/index.php"
  11. in file, change <ipaddress> to your ip address
