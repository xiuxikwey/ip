@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL1.TXT del ACTUAL1.TXT
if exist ACTUAL2.TXT del ACTUAL2.TXT
if exist toAdd.txt del toAdd.txt

REM compile the code into the bin folder
javac -cp ..\src\ -Xlint:none -d ..\bin ..\src\main\java\chatbot\*.java ..\src\main\java\tasks\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin\ chatbot/Oliver < input1.txt > ACTUAL1.TXT
java -classpath ..\bin\ chatbot/Oliver < input2.txt > ACTUAL2.TXT

REM compare the output to the expected output
FC ACTUAL1.TXT EXPECTED1.TXT
FC ACTUAL2.TXT EXPECTED2.TXT
