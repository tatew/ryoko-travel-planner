FROM amazoncorretto:21.0.6

EXPOSE 8000
EXPOSE 8443
EXPOSE 30000

COPY --chown=appuser:appuser ./.docker/*.sh /home/appuser/bin/
WORKDIR /app

ENTRYPOINT ["bash", "/home/appuser/bin/entrypoint-local.sh"]