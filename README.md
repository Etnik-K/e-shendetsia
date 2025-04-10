# eShëndetsia – Backend

Ky është backend-i i projektit të lëndës **Sistemet e Shpërndara**, zhvilluar duke përdorur **Java Spring Boot** dhe **PostgreSQL**.

## Si të ekzekutoni projektin

Ndiqni hapat më poshtë në varësi të sistemit tuaj operativ:

### Linux / macOS

#### - Për herë të parë
```bash
git clone https://github.com/Etnik-K/e-shendetsia
cd e-shendetsia
./setup
```
#### - Përndryshe ``./run``

### Windows
#### - Për herë të parë
1. Sigurohuni që virtualizimi është i aktivizuar në BIOS (e nevojshme për Docker).
2. Hapni **Command Prompt (cmd)** si administrator.
3. Ekzekutoni komandat më poshtë:
```cmd
git clone https://github.com/Etnik-K/e-shendetsia
cd e-shendetsia
./setup.cmd
```
#### - Përndryshe ``./run.cmd``

## Verifikimi

Pasi backend-i të jetë ngritur me sukses, hapni aplikacionin React (frontend) dhe vizitoni:
https://localhost:8080/api/hello
Nëse merrni një përgjigje (Hello World!), atëherë backend-i është funksional!