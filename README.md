# eShëndetsia – Backend

Ky është backend-i i projektit të lëndës **Sistemet e Shpërndara**, zhvilluar duke përdorur **Java Spring Boot** dhe **PostgreSQL**.

## Si të ekzekutoni projektin
 - Se pari, sigurohuni qe e keni **Docker** te instaluar ne pajisjen tuaj.
   - nese nuk e keni, per sistemet operative **Ubuntu** dhe **Kali Linux** mund te ekzekutoni skriptat ```./docker-install-ubuntu``` dhe ```./docker-install-kali```, respektivisht, ne kushte normale skripta e dyte duhet te funksionoj per cdo Linux sistem te bazuar ne Debian.
 - Pas instalimit te **Docker**, pavarsisht sistemit tuaj operativ, ekzekutoni skripten më poshtë:

```bash
  ./run
```

## Verifikimi
Pasi backend-i të jetë ngritur me sukses, hapni shfletuesin tuaj te internetit dhe vizitoni:

- https://localhost:8080/api/test

Nëse merrni një përgjigje pershendetese (Pershendetje nga Backend), atëherë backend-i është funksional!

Tani mund te nisni projektin tjeter (frontend) dhe te perdorni web aplikacionin **e-shendetsia**.