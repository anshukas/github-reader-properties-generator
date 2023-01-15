# github-reader-properties-generator
Utility Project - Read the GitHub K8 config-map file and generate application properties 

#Example
If you have config-map yml file on Github, which looks like 

```apiVersion: v1
kind: ConfigMap
metadata:
  name: ms-configs
  namespace: live
data:
  configuration_store_path: "/config-server/appconfig"
  config_server_uri: "http://configserver-v3-svc:1010/config/v3/app-configs"
  config_server_uri_v2: "http://configserver-svc:1010/config/v2/app-configs"
  cms_url: "http://drupal-svc"
  cms_basic_auth_header_value: "Basic  <Basic-64-encode>"
  site_config_default_envinstance: "live"
  bcc_server_endpoint_base_domain: "https://104.196.53.219"
  redis_host: "redis-12000.dev01.rds.est4-app.com"
  redis_port: "12000"
```

Wanted to convert it as application.properties, so that you can use in directly in LOCAL development.

```
configuration.store.path=/config-server/appconfig
config.server.uri=http://configserver-v3-svc:1010/config/v3/app-configs
config.server.uri.v2=http://configserver-svc:1010/config/v2/app-configs
cms.url=http://drupal-svc
...

```
#Remove all unnecessary
It also remove all unnecessary thing which are not required for local such as javaoptions-configs.

```
apiVersion: v1
kind: ConfigMap
metadata:
    name: javaoptions-configs
    namespace: live
data:
  config_server_java_opts: "-server -Xms2048m -Xmx2048m -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError"
  bazaarvoice_java_opts: "-server -Xms2048m -Xmx2048m -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp"

```

