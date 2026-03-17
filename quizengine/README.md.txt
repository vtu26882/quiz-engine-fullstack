🚀 Automated Quiz Engine (Full-Stack Project)

A full-stack quiz platform built using Spring Boot, React, and MySQL where users can:

Register & login

Take quizzes with a timer

Submit answers

View leaderboard rankings

Download PDF certificates

This project demonstrates full-stack development with REST APIs, database integration, and modern frontend UI.

📌 Project Overview

This system allows users to participate in quizzes and receive results instantly. If a user scores above the passing mark, the system generates a PDF certificate automatically.

The project includes:

✔ User authentication
✔ Quiz creation
✔ Random question generation
✔ Quiz timer with auto submit
✔ Leaderboard ranking system
✔ PDF certificate generation
✔ React frontend interface

🏗 System Architecture

The application follows a 3-layer architecture.

React Frontend
       ↓
Spring Boot REST API
       ↓
Service Layer
       ↓
Repository Layer (JPA)
       ↓
MySQL Database
🛠 Tech Stack
Backend

Java

Spring Boot

Spring Data JPA

Hibernate

REST APIs

Frontend

React.js

Axios

CSS

Database

MySQL

Libraries

iText (PDF generation)

📂 Project Structure
Backend (Spring Boot)
quizengine
 ├── controller
 │      AuthController
 │      QuizController
 │      CertificateController
 │
 ├── service
 │      QuizService
 │      CertificateService
 │
 ├── repository
 │      QuestionRepository
 │      QuizRepository
 │      LeaderboardRepository
 │      UserRepository
 │
 ├── entity
 │      Question
 │      Quiz
 │      Leaderboard
 │      Response
 │      User
 │      QuestionWrapper
Frontend (React)
quiz-frontend
 ├── pages
 │     Login.js
 │     Register.js
 │     Quiz.js
 │     Leaderboard.js
 │
 ├── services
 │     api.js
 │
 ├── App.js
 ├── index.js
📊 Database Design
Users Table
Column Type
id	int
username	varchar
password	varchar
Questions Table
Column	Type
id	int
questionTitle	text
option1	varchar
option2	varchar
option3	varchar
option4	varchar
correctAnswer	varchar
category	varchar
Quiz Table
Column	Type
id	int
title	varchar
questionIds	text
startTime	long
Leaderboard Table
Column	Type
id	int
quizId	int
username	varchar
score	int
⚙️ Step-by-Step Development Process
1️⃣ Creating Spring Boot Project

We created the backend using Spring Initializr.

Dependencies used:

Spring Web

Spring Data JPA

MySQL Driver

Lombok

DevTools

This generates the base Spring Boot structure.

2️⃣ Database Configuration

We connected MySQL in:

application.properties

Example configuration:

spring.datasource.url=jdbc:mysql://localhost:3306/quizdb
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
3️⃣ Creating Entity Classes

Entities represent database tables.

Example:

Question
Quiz
User
Leaderboard
Response

Example entity:

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private String correctAnswer;
    private String category;
}
4️⃣ Creating Repository Layer

Repositories communicate with the database.

Example:

public interface QuestionRepository
extends JpaRepository<Question,Integer>{
}

This allows:

Save questions

Fetch questions

Custom queries

5️⃣ Creating Service Layer

Service layer contains business logic.

Example tasks:

Create quiz

Fetch quiz questions

Calculate score

Save leaderboard

Example:

public Map<String,Integer> submitQuiz(...)

Responsibilities:

✔ Validate responses
✔ Calculate score
✔ Save leaderboard
✔ Generate certificate

6️⃣ Creating REST Controllers

Controllers expose APIs for the frontend.

Example APIs:

POST /auth/register
POST /auth/login

POST /quiz/create
GET  /quiz/get/{id}
POST /quiz/submit/{id}

GET /quiz/leaderboard/{quizId}

GET /certificate/download
🧠 Quiz Workflow
User Register
      ↓
User Login
      ↓
Quiz Created
      ↓
Load Questions
      ↓
Start Timer
      ↓
User Selects Answers
      ↓
Submit Quiz
      ↓
Score Calculated
      ↓
Leaderboard Updated
      ↓
Certificate Generated
⏱ Quiz Timer Feature

The frontend uses React state + useEffect.

const [timeLeft,setTimeLeft] = useState(300)

Timer decreases every second.

If timer reaches 0 → auto submit quiz.

🏆 Leaderboard Feature

When a user submits quiz:

Leaderboard leaderboard = new Leaderboard();

leaderboard.setQuizId(id);
leaderboard.setUsername(username);
leaderboard.setScore(score);

leaderboardRepo.save(leaderboard);

Leaderboard API:

GET /quiz/leaderboard/{quizId}

Displays top scores.

📜 Certificate Generation

If user passes quiz:

certificateService.generateCertificate(...)

The certificate is generated using iText PDF library.

Example certificate:

Certificate of Completion

This certifies that
Tarun

has successfully completed

Java Quiz

Score: 8 / 10
🖥 Frontend Development

React was used to build UI pages.

Main pages:

Register Page
Login Page
Quiz Page
Leaderboard Page

API communication done using:

Axios

Example:

axios.post("/quiz/submit/"+id,responses)
🧪 Testing APIs

All APIs were tested using:

Postman

Example request:

POST /quiz/submit/1?username=tarun

Body:

[
 { "id":8, "response":"boolean" },
 { "id":6, "response":"Sun Microsystems" }
]
📸 Screenshots

(Add images here)

Login Page
![Login Page](screenshots/login.png)
Quiz Page
![Quiz Page](screenshots/quiz.png)
Leaderboard
![Leaderboard](screenshots/leaderboard.png)
Certificate
![Certificate](screenshots/certificate.png)
🚀 Future Improvements

Possible enhancements:

JWT Authentication

Admin dashboard

Quiz analytics

Category filters

Dark mode UI

Online deployment

👨‍💻 Author

Tarun

Full-Stack Developer Project

⭐ Project Outcome

This project demonstrates knowledge of:

✔ Java
✔ Spring Boot
✔ REST API Development
✔ React Frontend
✔ MySQL Database
✔ Full-Stack Architecture