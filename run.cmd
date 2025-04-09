docker compose down -v --remove-orphans
docker system prune -f -a --volumes
docker compose up --build