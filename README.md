# Bakhyt Diyar Student API

Author: Diyar Bakhyt (group IT2-2301, student ID 38310)
Status: CRUD implemented and documentation prepared by Diyar Bakhyt

Spring Boot CRUD application for managing students, with PostgreSQL,
Docker Compose and a Jenkins CI pipeline.

## API

- GET /api/students - list all students
- GET /api/students/{id} - get one student (404 if not found)
- POST /api/students - create a student
- PUT /api/students/{id} - update a student (added by Diyar Bakhyt)
- DELETE /api/students/{id} - delete a student (added by Diyar Bakhyt)

CI check: automatic build triggered by Diyar Bakhyt
