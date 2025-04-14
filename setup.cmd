net session >nul 2>&1
if %errorlevel% NEQ 0 (
    echo Requesting admin privileges...
    powershell -Command "Start-Process '%~f0' -Verb RunAs"
    exit /b
)

@echo off
SETLOCAL ENABLEEXTENSIONS
SETLOCAL ENABLEDELAYEDEXPANSION

echo Updating system is not handled in CMD. Please make sure Windows is up to date manually.

echo.
echo Installing OpenJDK 21...
winget install --id Microsoft.OpenJDK.21 -e --silent

echo.
echo Testing Docker installation...
docker run hello-world

echo.
echo Installing the project...
call mvnw.cmd clean install


echo.
echo Running Docker Compose...
docker compose down -v --remove-orphans
docker compose up --build

echo.
echo All done!
pause
