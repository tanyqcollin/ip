# Carter User Guide

![Ui.png](/docs/Ui.png)

Carter is a desktop application that helps you store tasks, such as to-dos, deadlines, and events.

---
## Feature List
- [Add todo task](#adding-todos-todo-task)
- [Add deadline task](#adding-deadlines-deadline-task)
- [Add event task](#adding-events-event-task)
- [View all tasks](#view-tasks)
- [Delete a task](#delete-task)
- [Mark a task as done](#mark-task)
- [Unmark a task as done](#unmark-task)
- [Exit application](#exit-application)

---
## Adding todos `todo` task

Adds todo task to the list.

Format: `todo DESCRIPTION` <br/>
Example: `todo read book` <br/>
```
Expected Output:
Got it. I've added the task:
    [T][] read me
Now you have 1 tasks in the list.
```
---
## Adding deadlines `deadline` task
Adds deadline task to the list.

Format: `deadline DESCRIPTION /by YYYY-MM-DD HH:MM` <br/>
Example: `deadline return book /by 2019-10-15 18:00` <br/>
``` 
Expected Output:
Got it. I've added the task:
    [D][] return book (by: Oct 15 2019 18:00)
Now you have 2 tasks in the list.
```
---
## Adding events `event` task
Adds event task to the list.

Format: `event DESCRIPTION /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM` <br/>
Example: `event project meeting /from 2019-10-17 14:00 /to 2020-07-09 16:00` <br/>
``` 
Expected Output:
Got it. I've added the task:
    [E][] project meeting (from: Oct 17 2019 14:00 to Jul 09 2020 16:00)
Now you have 3 tasks in the list.
```
---
## View Tasks
List out all tasks in the list.

Format: `list` <br/>
Example: `list` <br/>
``` 
Expected Output:
Here are the task in your list:
    1. [T][] read me
    2. [D][] return book (by: Oct 15 2019 18:00)
    3. [E][] project meeting (from: Oct 17 2019 14:00 to Jul 09 2020 16:00)
```
---
## Delete Task
Delete a Task. 

Format: `delete INDEX` <br/>
Example: `delete 3` <br/>
``` 
Expected Output:
Noted. I've removed this task:
    [E][] project meeting (from: Oct 17 2019 14:00 to Jul 09 2020 16:00)
Now you have 2 task in the list.
```
---
## Mark Task
Mark a task as done.

Format: `mark INDEX` <br/>
Example: `mark 2` <br/>
``` 
Expected Output:
Nice! I've marked this task as done.
    [D][X] return book (by: Oct 15 2019 18:00)
```
---
## Unmark Task
Mark a task as not done.

Format: `unmark INDEX` <br/>
Example: `unmark 2` <br/>
``` 
Expected Output:
Ok, I've marked this task as not done yet.
    [D][] return book (by: Oct 15 2019 18:00)
```
## Exit application
Exit the application.

Format: `bye` <br/>
Example: `bye` <br/>
> [!NOTE]
> It will take 4 seconds to exit the application after enter the command.
``` 
Expected Output:
Bye! Hope to see you again soon.
```
    