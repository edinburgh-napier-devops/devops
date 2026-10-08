FROM mysql
WORKDIR /tmp
RUN printf '[mysql]\ncommands=ON\n' > /etc/mysql/conf.d/enable-commands.cnf
COPY world_db/*.sql /docker-entrypoint-initdb.d/
ENV MYSQL_ROOT_PASSWORD=example
ENV MYSQL_ROOT_HOST=%