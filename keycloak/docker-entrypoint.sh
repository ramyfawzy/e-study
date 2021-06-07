#!/bin/bash
set -e
/opt/jboss/keycloak/bin/standalone.sh -b 0.0.0.0 -Djboss.socket.binding.port-offset=100 -Dkeycloak.profile.feature.upload_scripts=enabled -Dkeycloak.migration.action=import -Dkeycloak.migration.provider=singleFile -Dkeycloak.migration.realmName=SpringBootKeycloak -Dkeycloak.migration.file=./keycloak_export.json
exit 1