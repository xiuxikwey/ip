# Oliver User Guide
![Screenshot of product user interface](/Ui.png)

Oliver keeps a list of tasks that you need to do,
and will keep the list safe when you close the program.
You can mark tasks as done and delete them once you are finished.

## Tabs

Use the different tabs to learn how to use Oliver.
- Console: Prints success and error messages
- Command List: Holds the full list of commands
- Task List: Holds your current task list

## Adding tasks

Oliver keeps track of three different types of tasks:
- Todo, tasks you need to do
- Deadline, tasks with a deadline
- Event, tasks with a start and end time

Commands to add tasks can be found in the 'Command list' tab.
For example,

`todo Feed the dog`

creates a new todo task with \<task name\>.

```
Next task is to "[T][ ] Feed the dog"!
```

## Viewing tasks

Click on the 'Task List' tab to see all your tasks.
Alternatively, type `list` and the task list will be
printed in the 'Console' tab.

## Deleting tasks

Each task has a task number. Type `delete 0` to delete task number 0.

## Marking tasks

You can also mark tasks as done or undone using their task number.
Type `mark 0` or `unmark 0`.

## Finding tasks

You can find tasks and their numbers with `search`.

## Closing the program

Type `bye` to close the program. Your task list will be saved.

## Syntax checker

The input bar will light up red if your command is likely to fail.
Check the 'Command list' or submit it and check the feedback in the console.

## Undo and redo

Oliver can undo up to 50 commands, and can undo the undoes with redo.