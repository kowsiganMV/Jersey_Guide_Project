# Jersey Guide Project

A simple REST API demo built with Jersey to help you learn Jakarta REST fundamentals. This project shows you how to build a complete API with books and users using an easy-to-understand in-memory database.

---

## 📚 What This Project Does

This is a learning-friendly REST API that manages a library system with books and users. It demonstrates:
- Creating, reading, updating, and deleting data (CRUD operations)
- Validating user input
- Handling errors gracefully
- Processing requests asynchronously
- Logging requests and customizing responses

Perfect for beginners who want to understand how REST APIs work!

---

## 🛠️ Tech Stack

Here are the technologies used in this project:

- **Java 11+** - The programming language
- **Jersey** - Framework for building REST APIs (implements Jakarta REST/JAX-RS)
- **Jackson** - Converts Java objects to JSON and vice versa
- **Jakarta Bean Validation** - Validates incoming data (checks if email is valid, fields aren't empty, etc.)
- **Apache Ant** - Build automation tool that compiles your code, packages it into a WAR file, and handles deployment
- **Tomcat/Jetty** - Web servers that run your application

---

## 📁 Project Structure Explained

Here's what each important file does:

### Configuration
- **`JerseyConfig.java`** - The main setup file that tells Jersey where to find your API code and which features to use (JSON conversion, validation)

### API Endpoints
- **`BookUserResource.java`** - Contains all your API endpoints (the URLs your app responds to). Handles requests for books and users.

### Data Models
- **`Book.java`** - Defines what a book looks like (ID, name, author, category, number of copies)
- **`User.java`** - Defines what a user looks like (ID, name, email, role, password) with validation rules

### Request Processing
- **`RequestLogger.java`** - Prints information about each incoming request (helpful for debugging)
- **`ResponseHeaderFilter.java`** - Adds a custom header to every response

### Error Handling
- **`NotFoundMapper.java`** - When something isn't found, returns a nice JSON error message instead of a generic error page

---

## 🚀 API Endpoints

Your API runs at `http://localhost:8080/jersey-guide/library`

### Book Endpoints

| Method | URL | What it does |
|--------|-----|--------------|
| GET | `/books` | Get all books (can filter by category with `?category=Tech`) |
| GET | `/books/{id}` | Get one specific book by its ID |
| GET | `/books/async` | Get books with a simulated delay (shows async handling) |
| POST | `/books` | Add a new book |
| PUT | `/books/{id}` | Update an entire book |
| PATCH | `/books/{id}` | Update just some fields of a book |
| DELETE | `/books/{id}` | Delete a book |

### User Endpoints

| Method | URL | What it does |
|--------|-----|--------------|
| GET | `/users` | Get all users |
| GET | `/users/{id}` | Get one specific user |
| POST | `/users` | Add a new user |

---

## ⚙️ How to Build and Run

### Prerequisites
- Java 11 or higher installed
- Apache Ant 1.10+ installed ([Download here](https://ant.apache.org/bindownload.cgi))
- Tomcat or Jetty web server

### Understanding Ant Build Targets

This project uses Apache Ant for build automation. Here are the available targets:

| Ant Target | What it does |
|------------|--------------|
| `ant clean` | Deletes the `build/` and `dist/` directories (fresh start) |
| `ant compile` | Compiles Java source files into `.class` files |
| `ant war` | Creates a deployable WAR file (calls `compile` first) |
| `ant deploy-to-tomcat` | Builds the WAR and copies it to Tomcat's webapps folder |

### Build Steps

**1. Clean previous builds (optional but recommended):**
```bash
ant clean
```
This removes old compiled files and ensures a fresh build.

**2. Build the WAR file:**
```bash
ant war
```
This will:
- Compile all Java source files
- Package everything into `dist/jersey-guide.war`
- Include all dependencies from the `lib/` folder

**3. Deploy to Tomcat:**

**Option A - Automatic deployment:**
```bash
# Set your Tomcat location (Linux/Mac)
export TCAT_HOME=/path/to/tomcat

# Or on Windows
set TCAT_HOME=C:\path\to\tomcat

# Deploy
ant deploy-to-tomcat
```

**Option B - Manual deployment:**
```bash
# Copy the WAR file manually
cp dist/jersey-guide.war $TOMCAT_HOME/webapps/

# Start Tomcat
cd $TOMCAT_HOME/bin
./startup.sh    # Linux/Mac
startup.bat     # Windows
```

**4. Access your API:**
Open your browser or use curl to visit:
```
http://localhost:8080/jersey-guide/library/books
```

### Quick Run with Jetty Runner

If you prefer a simpler approach without installing Tomcat:

**1. Build the WAR:**
```bash
ant war
```

**2. Download Jetty Runner:**
```bash
wget https://repo1.maven.org/maven2/org/eclipse/jetty/jetty-runner/9.4.51.v20230217/jetty-runner-9.4.51.v20230217.jar
```

**3. Run:**
```bash
java -jar jetty-runner-9.4.51.v20230217.jar --port 8080 dist/jersey-guide.war
```

**4. Access:**
```
http://localhost:8080/library/books
```

### Troubleshooting Ant Build

**Problem: "ant: command not found"**
- Solution: Install Apache Ant and add it to your PATH
  ```bash
  # Verify installation
  ant -version
  ```

**Problem: "Cannot find lib directory"**
- Solution: Ensure all Jersey, Jackson, and validation JAR files are in the `lib/` folder

**Problem: Compilation errors**
- Solution: Check that you're using Java 11 or higher
  ```bash
  java -version
  ```

**Problem: Deploy fails**
- Solution: Verify `TCAT_HOME` environment variable points to your Tomcat installation
  ```bash
  echo $TCAT_HOME    # Linux/Mac
  echo %TCAT_HOME%   # Windows
  ```

---

## 💡 Try It Out (Examples)

### Get all books:
```bash
curl http://localhost:8080/jersey-guide/library/books
```

### Get a specific book:
```bash
curl http://localhost:8080/jersey-guide/library/books/1
```

### Add a new book:
```bash
curl -X POST -H "Content-Type: application/json" \
  -d '{"bookName":"Java Basics","author":"John Doe","category":"Tech","noOfBooks":5}' \
  http://localhost:8080/jersey-guide/library/books
```

### Add a new user:
```bash
curl -X POST -H "Content-Type: application/json" \
  -d '{"name":"Alice","email":"alice@example.com","password":"pass123","role":"user"}' \
  http://localhost:8080/jersey-guide/library/users
```

---

## ✨ Key Features Explained

### 1. **JSON Support**
Everything automatically converts between Java objects and JSON. You send JSON, you get JSON back.

### 2. **Data Validation**
When you create a user, the API checks:
- Name isn't empty
- Email is in a valid format
- Password meets minimum length requirements
- All required fields are provided

### 3. **Error Handling**
If you request a book that doesn't exist, you get a clean JSON error:
```json
{"error": "Book not found: 42"}
```

### 4. **Request Logging**
Every request is logged so you can see what's happening (helpful for debugging).

### 5. **Async Processing**
The `/books/async` endpoint shows how to handle requests that take time without blocking.

---

## ⚠️ Important Notes

- **Data is temporary** - This uses in-memory storage, so data disappears when you restart the app. For a real application, you'd connect to a database.
- **Simple and educational** - This project prioritizes clarity over production features. It's designed for learning!

---

## 📖 What You Can Learn From This

- How to structure a REST API
- How to handle different HTTP methods (GET, POST, PUT, DELETE, PATCH)
- How to validate user input
- How to convert between JSON and Java objects
- How to handle errors properly
- How to add filters to process requests
- How to build and deploy Java web applications

---

## 🎯 Next Steps

Once you understand this project, consider:
- Adding a real database (PostgreSQL, MySQL)
- Adding authentication and authorization
- Writing unit tests
- Adding API documentation with Swagger
- Improving logging with a proper logging framework

---

## 🤝 Contributing

Feel free to fork this project and experiment! If you find ways to improve it or add features that would help others learn, pull requests are welcome.

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin feature/your-feature`
5. Open a pull request

---

## 📄 License

This is an educational project. Feel free to use it for learning!

---

## 📬 Questions?

If you have questions or run into issues, feel free to open an issue in the repository.

Happy Learning! 🚀
