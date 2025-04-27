# eShëndetsia – Backend

Ky është backend-i i projektit të lëndës **Sistemet e Shpërndara**, zhvilluar duke përdorur **Java Spring Boot** dhe **PostgreSQL**.

## Si të ekzekutoni projektin

Ndiqni hapat më poshtë në varësi të sistemit tuaj operativ:

### Linux / macOS

#### Klononi kete Repository ne Pajisjen tuaj:
```bash
git clone https://github.com/Etnik-K/e-shendetsia
cd e-shendetsia
```

#### Instaloni Docker: (nese nuk e keni)
```bash
./docker-install
```

#### Nisni projektin:
```bash
./run
```

## Verifikimi

Pasi backend-i të jetë ngritur me sukses, hapni aplikacionin React (frontend) dhe vizitoni:
https://localhost:8080/api/test
Nëse merrni një përgjigje (Hello World!), atëherë backend-i është funksional!