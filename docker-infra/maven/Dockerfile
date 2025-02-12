# Use an official Maven image to build the project
FROM maven:3.8.4-openjdk-11-slim AS build

COPY maven/entrypoint.sh /entrypoint.sh
RUN chmod u+x /entrypoint.sh

ENTRYPOINT ["sh", "/entrypoint.sh"]