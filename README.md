# City List

Service to let user explore cities

---

# General info

In system by default for purposes of this project we have 2 users:

`userEdit` password `user` with permission `ALLOW_EDIT`

`userRead` password `user` with permission `ALLOW_READ`

You can pass them into postman as `Basic Auth`
after uncommenting specified lines in `SecurityConfig`
you can access `Http.UPDATE /api/v1/cities/{id}` only with
`userEdit` credentials else you will experience `status 403 forbidden`

Full API is available under SWAGGER UI endpoint.

### Database - H2

You can access h2-console under path `/api/v1/h2-console`

Initial CSV file is load by liquibase

### How to run

If you loaded application to your IDE you can run it using class `CityListApplication`

Using gradle `gradle bootRun`

After compiling with `gradle bootJar` you can run it with simple command `java -jar jarpath/jarname`

---

### Environment Variables

All default environment variables are in [.env.local](.env.local)

* City List variables
    * `APP_PORT` - port app starts on, default `8089`
    * `PROFILES` - enables features of service, default `local`
* Database
    * `H2_USERNAME` - Database username
    * `H2_PASSWORD` - Database password
* Logging
    * `LOGGING_LEVEL` - logging level of application, default `INFO`

### Health check endpoint

Health check endpoint is on `/api/v1/actuator/health`

---

### Metrics endpoint

Prometheus metrics endpoint is on `/api/v1/actuator/prometheus`

---

### Swagger endpoint

Swagger ui `/api/v1/swagger-ui.html`

Api docs   `/api/v1/v3/api-docs`
