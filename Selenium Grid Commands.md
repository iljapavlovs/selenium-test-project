### Running Hub:
```
java -jar src/test/resources/server/selenium-server-standalone-3.4.0.jar -role hub -browserTimeout 1000 -timeout 1000 -port 4444
```

### Running and registering Node
```
java -Dwebdriver.gecko.driver=src/test/resources/drivers/geckodriver-v0.17.0-win32/geckodriver.exe -jar src/test/resources/server/selenium-server-standalone-3.4.0.jar -role node -browser browserName=firefox -browserTimeout 1000 -timeout 1000 -port 4445 -hub http://localhost:4444/grid/register/
```

### Opening Grid Console
````
http://localhost:4444/grid/console
````
