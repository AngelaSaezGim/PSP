@echo off
:: Cambia al directorio del repositorio
cd /d "C:\Users\angel\OneDrive\Escritorio\DAM\PSP"

:: Descarga y fusiona los cambios del repositorio remoto
git pull origin main

:: Mensaje final
echo Pull completado con éxito.
pause
