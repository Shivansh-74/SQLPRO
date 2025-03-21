CREATE TABLE Employee (
    id INT PRIMARY KEY, 
    name VARCHAR(50), 
    dept VARCHAR(50),
    location VARCHAR(50),
    company VARCHAR(50),
    managerName VARCHAR(50)
);


CREATE TABLE EmployeeDetails (
    emp_id INT PRIMARY KEY, 
    age INT, 
    salary DECIMAL(10,2),
    FOREIGN KEY (emp_id) REFERENCES Employee(id) ON DELETE CASCADE
);

INSERT INTO Employee (id, name, dept, location, company, managerName) VALUES
(1, 'Aarav Sharma', 'IT', 'Mumbai', 'TechCorp', 'Rajesh Kumar'),
(2, 'Vivaan Mehta', 'HR', 'Delhi', 'BizGroup', 'Meena Sharma'),
(3, 'Aditya Patel', 'Finance', 'Bangalore', 'FinSolutions', 'Amit Verma'),
(4, 'Riya Gupta', 'Marketing', 'Pune', 'MarketPros', 'Sunita Desai'),
(5, 'Kavya Verma', 'IT', 'Hyderabad', 'TechCorp', 'Rajesh Kumar'),
(6, 'Ishaan Khanna', 'HR', 'Delhi', 'BizGroup', 'Meena Sharma'),
(7, 'Neha Singh', 'Finance', 'Bangalore', 'FinSolutions', 'Amit Verma'),
(8, 'Rohan Das', 'Operations', 'Chennai', 'LogiCo', 'Suresh Nair'),
(9, 'Meera Joshi', 'IT', 'Mumbai', 'TechCorp', 'Rajesh Kumar'),
(10, 'Kunal Tiwari', 'Sales', 'Kolkata', 'RetailX', 'Vikram Das'),
(11, 'Ananya Roy', 'IT', 'Hyderabad', 'TechCorp', 'Rajesh Kumar'),
(12, 'Kabir Nair', 'Finance', 'Bangalore', 'FinSolutions', 'Amit Verma'),
(13, 'Sanya Iyer', 'Marketing', 'Pune', 'MarketPros', 'Sunita Desai'),
(14, 'Devansh Malhotra', 'Operations', 'Chennai', 'LogiCo', 'Suresh Nair'),
(15, 'Priya Sen', 'Sales', 'Kolkata', 'RetailX', 'Vikram Das'),
(16, 'Rahul Bhatia', 'HR', 'Delhi', 'BizGroup', 'Meena Sharma'),
(17, 'Tanya Goel', 'Finance', 'Bangalore', 'FinSolutions', 'Amit Verma'),
(18, 'Aakash Rao', 'IT', 'Hyderabad', 'TechCorp', 'Rajesh Kumar'),
(19, 'Simran Kapoor', 'HR', 'Delhi', 'BizGroup', 'Meena Sharma'),
(20, 'Varun Saxena', 'Marketing', 'Pune', 'MarketPros', 'Sunita Desai');

INSERT INTO EmployeeDetails (emp_id, age, salary) VALUES 
(1, 28, 70000.00),
(2, 32, 65000.00),
(3, 29, 72000.00),
(4, 26, 60000.00),
(5, 30, 75000.00),
(6, 27, 63000.00),
(7, 35, 80000.00),
(8, 31, 69000.00),
(9, 25, 58000.00),
(10, 33, 77000.00),
(11, 29, 73000.00),
(12, 34, 82000.00),
(13, 28, 61000.00),
(14, 32, 71000.00),
(15, 26, 67000.00),
(16, 30, 76000.00),
(17, 31, 79000.00),
(18, 27, 72000.00),
(19, 33, 68000.00),
(20, 29, 64000.00);
