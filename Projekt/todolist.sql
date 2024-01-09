CREATE SCHEMA todolist;

CREATE TABLE todolist.User (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE todolist.Group (
    group_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES todolist.User(user_id),
    group_name VARCHAR(255) NOT NULL
);

CREATE TABLE todolist.Task (
    task_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES todolist.User(user_id),
    title VARCHAR(255) NOT NULL,
    description VARCHAR(16384),
    due_date DATE,
    group_id INT REFERENCES todolist.Group(group_id),
    is_completed BOOLEAN,
	subtasks JSON
);

CREATE TABLE todolist.Subtask (
    subtask_id SERIAL PRIMARY KEY,
    task_id INT REFERENCES todolist.Task(task_id),
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    due_date DATE,
    is_completed BOOLEAN
);

CREATE TABLE todolist.TaskSubTask (
    subtask_id,
    task_id INT REFERENCES todolist.Task(task_id),
);

CREATE TABLE todolist.Note (
    note_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES todolist.User(user_id),
    title VARCHAR(255) NOT NULL,
    text VARCHAR(16384)
);

CREATE TABLE todolist.Archive (
    archive_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES todolist.User(user_id),
    task_id INT REFERENCES todolist.Task(task_id),
    is_completed BOOLEAN,
    completion_date DATE
);

CREATE TABLE todolist.Statistics (
    stat_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES todolist.User(user_id),
    total_tasks INT,
    completed_tasks INT,
    incomplete_tasks INT,
    completion_rate DECIMAL(5,2),
    productivity_score DECIMAL(5,2)
);
