# Personal Finance Manager

A comprehensive personal finance management system built with Spring Boot and Java to help users track expenses, manage budgets, and analyze spending patterns.

## 🚀 Features

- User authentication and authorization
- Expense tracking and categorization
- Budget management
- Income tracking
- Financial reports and analytics
- Transaction history

## 🛠️ Tech Stack

- **Backend:** Java 17, Spring Boot 3.x
- **Database:** MySQL/PostgreSQL
- **Build Tool:** Maven
- **Security:** Spring Security
- **API Documentation:** Swagger/OpenAPI

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+ (or PostgreSQL)

## 🔧 Installation & Setup

1. Clone the repository
```bash
git clone https://github.com/sawantpaddy/finance-manager.git
cd finance-manager
```

2. Configure database in `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/finance_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Build the project
```bash
mvn clean install
```

4. Run the application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 📚 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/auth/register | Register new user |
| POST | /api/auth/login | User login |
| GET | /api/expenses | Get all expenses |
| POST | /api/expenses | Add new expense |
| GET | /api/budget | Get budget details |

## 🔮 Future Enhancements

- Mobile app integration
- AI-powered spending insights
- Multi-currency support
- Export to PDF/Excel
- Investment tracking

## 👤 Contact

- GitHub: [@sawantpaddy](https://github.com/sawantpaddy)
- LinkedIn: [Your LinkedIn Profile]
- Email: your.email@example.com

## 📄 License

This project is open source and available under the MIT License.
