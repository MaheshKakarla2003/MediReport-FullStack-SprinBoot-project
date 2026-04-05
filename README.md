# MediReport - Medical Records Management System

A Spring Boot medical records app with JWT authentication, role-based authorization, and hospital patient management.
Live URL: [https://medi-report-frontend-l4yqwl2vy-maheshkakarla2003s-projects.vercel.app/login.html
]
## What’s included

- JWT-based login and security with Spring Security
- Role-based access using granted authorities
- Hospital and customer user roles
- Patient record management, visit logging, and document upload
- Hospital dashboard with pagination and search for patient data
- RESTful APIs for easy frontend integration

## Tech stack

- Java 17
- Spring Boot 3.x
- Spring Security
- Maven 3.9+
- PostgreSQL 12+
- Docker
- Render

## Quick start

```bash
git clone <your-repo>
cd medi-report
mvn clean install
mvn spring-boot:run
```

Set database config in `application.properties` or environment variables.

## Core APIs

- `POST /api/login` - Authenticate and get JWT token
- `POST /api/register-hospital` - Register hospital
- `POST /api/register-customer` - Register customer
- `GET /api/patients` - List patients (supports pagination + search)
- `POST /api/patients` - Create patient
- `POST /api/visits` - Add patient visit
- `POST /api/documents/upload` - Upload medical document

## Notes

- Hospital users can manage patients and view dashboard data
- Customer users can access allowed records based on roles
- Pagination and search are available for hospital patient lists

## Docker

```bash
docker build -t medi-report .
docker run -p 8080:8080 -e DB_URL=jdbc:postgresql://localhost:5432/medi_report -e DB_USER=postgres -e DB_PASSWORD=your_password medi-report
```

## Support

Open issues on GitHub for bugs or feature requests.
