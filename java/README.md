
# Vanta Rides Backend

## Database

Inside the `<project-root>/database/` directory, you'll find an executable Bash script (`.sh` file) and several SQL scripts (`.sql` files). These are used to create and manage the PostgreSQL database for the Vanta Rides application.

From a terminal session, run:

```bash
cd <project-root>/database/
./create.sh
```

This script drops the existing database (if any), creates a new database named `vantarides`, and executes the SQL scripts in the correct order.

### SQL Scripts

| File Name     | Description                                                                 |
|---------------|-----------------------------------------------------------------------------|
| `data.sql`    | Populates the database with static or demo data.                            |
| `dropdb.sql`  | Drops the existing database and users.                                      |
| `schema.sql`  | Creates the database tables and sequences.                                  |
| `user.sql`    | Creates application users and grants appropriate privileges.                |

---

### Database Users

PostgreSQL's `postgres` superuser is for administrative use only. This application uses two dedicated users:

| Username             | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| `vantarides_owner`   | The schema owner. Has full privileges to all objects in the database.       |
| `vantarides_appuser` | Used by the application. Has standard CRUD access to all relevant tables.   |

---

## Spring Boot

This application uses Spring Boot and is configured to run on port `9000` (instead of the default `8080`) to avoid conflicts with the frontend Vue server.

---

### Datasource Configuration

In `src/resources/application.properties`, the database connection is configured like this:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/vantarides
spring.datasource.name=vantarides
spring.datasource.username=vantarides_appuser
spring.datasource.password=finalcapstone
```

---

### JdbcTemplate Setup

In `src/main/java/com/vantarides/dao`, you'll find `JdbcUserDao`. Here's how dependency injection is used:

```java
@Service
public class JdbcUserDao implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
```

---

### CORS Setup

Any controller accessible from the Vue frontend requires the `@CrossOrigin` annotation:

```java
@RestController
@CrossOrigin
public class AuthenticationController {
    // endpoints here
}
```

---

## Security

Authentication and JWT logic live inside `src/main/java/com/vantarides/security`.

---

### Authentication Controller

Authentication is handled via the `/login` and `/register` endpoints inside:

```
com.vantarides.controller.AuthenticationController.java
```

It uses `JdbcUserDao` to manage user credentials and roles.

---

## Testing

DAO integration testing is supported.

### Example Tests

- `com.vantarides.dao.BaseDaoTest`: Sets up test environment and handles rollback.
- `com.vantarides.dao.JdbcUserDaoTest`: Provides an example of DAO testing.

Test schema and seed data live in:
- `database/schema.sql`
- `src/test/resources/test-data.sql`

---

Let me know when you're ready and I’ll start renaming all Java packages and class references from `techelevator` to `vantarides`.