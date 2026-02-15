# Oliver User Guide
![Screenshot of product user interface](/Ui.png)

Oliver keeps a list of tasks that you need to do,
and will keep the list safe when you close him.
You can mark tasks as done and delete them once you are finished.

## Adding tasks

Oliver keeps track of three different types of tasks:
- Todo, tasks you need to do
- Deadline, tasks with a deadline
- Event, tasks with a start and end time

Commands to add tasks can be found in the 'Command list' tab.
An example is

`todo Wash the dog`

that creates a new todo task with \<task name\>.

```
Next task is to "[T][ ] Wash the dog"!
```

## Viewing tasks

Click on the 'Task List' tab to see all your tasks.
Alternatively, type `list` and the task list will be
printed in the 'Console' tab.

## Command list tab

The remaining commands can be found in the 'Command list' tab. Click on the tab
and imitate the command format.

## Syntax checker

The input bar will light up red if your command is likely to fail.
Check the 'Command list' or submit it and check the feedback in the console.

## Undo and redo

Oliver can undo up to 50 commands, and can undo the undoes with redo.

## Batch input

Multiple inputs can be run as long as they are on different lines.
Be careful with this, because undo will only undo one command at a time.