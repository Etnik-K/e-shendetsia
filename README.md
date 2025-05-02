
# eShëndetsia – Backend

Ky është backend-i i projektit të lëndës **Sistemet e Shpërndara**, zhvilluar duke përdorur **Java Spring Boot** dhe **PostgreSQL**.

## Si të ekzekutoni projektin
- ### Se pari, sigurohuni qe e keni **Docker** te instaluar ne pajisjen tuaj. 
  - Nese nuk e keni, mund ta instaloni permes skriptave  
  `./docker-install-ubuntu` dhe `./docker-install-kali`
- ### Pas instalimit te **Docker**:
  - Ne terminalin tuaj, ekzekutoni:\
      `docker compose up --build` ose `./run`

## Verifikimi

Pasi backend-i të jetë ngritur me sukses, hapni shfletuesin tuaj te internetit dhe vizitoni:

- https://localhost:8080/api/test

Nëse merrni një përgjigje pershendetese (Pershendetje nga Backend), atëherë backend-i është funksional!

Tani mund te nisni projektin tjeter ([frontend](http://github.com/gentzhushi/e-shendetsia-front-end)) dhe te perdorni web aplikacionin **e-shendetsia**.