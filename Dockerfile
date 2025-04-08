FROM ubuntu:latest
LABEL authors="gentzhushi"

ENTRYPOINT ["top", "-b"]