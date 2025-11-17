# Personal Finance Manager

A personal finance management REST API built with Spring Boot and Java to help users track expenses with proper authentication and categorization.

## 🚀 Features

### ✅ Implemented
- **User Authentication & Authorization**
  - User registration with validation
  - JWT-based login system
  - Secure password encryption
  - Token-based API access

- **Expense Management**
  - Create, read, update, delete expenses
  - Expense categorization
  - Date-based filtering
  - Category-based filtering
  - Pagination and sorting support

- **API Documentation**
  - Interactive Swagger UI
  - OpenAPI 3.0 specification
  - Comprehensive endpoint documentation

### 🔄 In Progress
- Budget management and tracking
- Income tracking
- Monthly/yearly financial reports
- Expense analytics and insights

## 🛠️ Tech Stack

- **Backend:** Java 17, Spring Boot 3.4.0
- **Database:** MySQL 8.0
- **Security:** Spring Security 6.x + JWT
- **Build Tool:** Maven
- **API Documentation:** SpringDoc OpenAPI (Swagger)
- **Libraries:** Lombok, Jakarta Validation, JJWT

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+

## 🔧 Installation & Setup

### 1. Clone the repository
```bash
git clone https://github.com/sawantpaddy/finance-manager.git
cd finance-manager
```

### 2. Create MySQL Database
```sql
CREATE DATABASE finance_db;
```

### 3. Configure Environment Variables

Set the following environment variables (recommended) or update `application.yaml`:

```bash
# Database Configuration
export DB_URL=jdbc:mysql://localhost:3306/finance_db
export DB_USERNAME=root
export DB_PASSWORD=your_password

# JWT Configuration
export JWT_SECRET=your-secret-key-at-least-256-bits-long
export JWT_EXPIRATION=86400000

# Server Configuration (optional)
export SERVER_PORT=8080
```

### 4. Build the Project
```bash
mvn clean install
```

### 5. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080/api`

### 6. Access Swagger UI
Open your browser and navigate to:
```
http://localhost:8080/api/swagger-ui.html
```

## 📚 API Endpoints

### Authentication
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | /api/auth/register | Register new user | No |
| POST | /api/auth/login | User login | No |

### Expenses
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | /api/expenses | Get all expenses (paginated) | Yes |
| POST | /api/expenses | Create new expense | Yes |
| PUT | /api/expenses/{id} | Update expense | Yes |
| DELETE | /api/expenses/{id} | Delete expense | Yes |
| GET | /api/expenses/category/{categoryId} | Filter by category | Yes |
| GET | /api/expenses/date-range | Filter by date range | Yes |

### Request/Response Examples

#### Register User
```bash
POST /api/auth/register
Content-Type: application/json

{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "SecurePass123"
}
```

#### Login
```bash
POST /api/auth/login
Content-Type: application/json

{
  "username": "john_doe",
  "password": "SecurePass123"
}

Response:
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer"
}
```

#### Create Expense
```bash
POST /api/expenses
Authorization: Bearer {your-jwt-token}
Content-Type: application/json

{
  "amount": 50.00,
  "description": "Grocery shopping",
  "expenseDate": "2024-11-17",
  "categoryId": 1
}
```

#### Get All Expenses (with pagination)
```bash
GET /api/expenses?page=0&size=10&sortBy=expenseDate&direction=DESC
Authorization: Bearer {your-jwt-token}
```

## 🏗️ Project Structure

```
src/main/java/com/finance/
├── config/              # Configuration classes (Security, etc.)
├── controller/          # REST API controllers
├── dto/                 # Data Transfer Objects
├── entity/             # JPA entities (User, Expense, Category)
├── exception/          # Custom exceptions and global handler
├── repository/         # Spring Data JPA repositories
├── security/           # JWT and security components
├── service/            # Business logic layer
└── util/               # Utility classes (JWT utils, etc.)
```

## 🔐 Security

- Passwords are encrypted using BCrypt
- API endpoints (except auth) require JWT authentication
- JWT tokens expire after 24 hours (configurable)
- Custom authentication filter validates tokens on each request

## 🧪 Testing

Run tests with:
```bash
mvn test
```

## 🚀 Deployment

### Database Setup
Ensure your production database is created and accessible. Update environment variables accordingly.

### Build JAR
```bash
mvn clean package -DskipTests
```

### Run JAR
```bash
java -jar target/personal-finance-manager-0.0.1-SNAPSHOT.jar
```

## 📝 Development Notes

### Known Issues / TODOs
- [ ] getUserIdFromToken() currently uses placeholder - needs JWT claim extraction
- [ ] Budget management endpoints to be implemented
- [ ] Income tracking module pending
- [ ] Add comprehensive unit and integration tests
- [ ] Add expense analytics endpoints (monthly summaries, category breakdowns)

### Future Enhancements
- Export expenses to CSV/Excel
- Email notifications for budget alerts
- Multi-currency support
- Recurring expense tracking
- Mobile app integration
- Data visualization dashboard

## 🤝 Contributing

This is a personal learning project, but suggestions and feedback are welcome!

## 👤 Author

**Paddy Sawant**
- GitHub: [@sawantpaddy](https://github.com/sawantpaddy)
- LinkedIn: [LinkedIn]
- Email: [pandurang.a.sawant@gmail.com]

## 📄 License

This project is open source and available under the MIT License.

---

**Note:** This project was built as part of my continuous learning journey to stay updated with modern Spring Boot development practices and demonstrate hands-on experience with building secure REST APIs.
