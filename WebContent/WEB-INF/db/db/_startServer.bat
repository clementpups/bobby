set HSQLDB_LIB=hsqldb.jar

"C:\Program Files\Java\jdk1.8.0_152\bin\java.exe" -classpath %HSQLDB_LIB% org.hsqldb.Server -database.0 file:blog -port 9093

pause