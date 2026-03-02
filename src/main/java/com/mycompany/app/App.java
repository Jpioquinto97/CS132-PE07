package com.mycompany.app;

/**
 * PE07 - Task Management System with Priority Filter
 * Extended from HOS07A with ability to filter tasks by priority
 * 
 * @author Jonathon Pioquinto
 */
public class App {
    public static void main(String[] args) {
        // Create a TaskManager with capacity of 5
        TaskManager<Task> taskManager = new TaskManager<>(5);
        
        // Add tasks with different priorities
        taskManager.addTask(new Task("Submit project", TaskPriority.HIGH));
        taskManager.addTask(new Task("Reply to email", TaskPriority.MEDIUM));
        taskManager.addTask(new Task("Organize desk", TaskPriority.LOW));
        
        // Display all tasks
        System.out.println("\nAll Tasks:");
        taskManager.showAllTasks();
        
        // Display only HIGH priority tasks
        System.out.println("\nTasks with Priority: High");
        taskManager.showTasksByPriority(TaskPriority.HIGH);
    }
}

/**
 * TaskPriority enum - defines priority levels for tasks
 * Overrides toString() for user-friendly output
 */
enum TaskPriority {
    LOW, MEDIUM, HIGH;
    
    /**
     * Override toString to format priority nicely
     * Converts "LOW" to "Low", "MEDIUM" to "Medium", etc.
     */
    @Override
    public String toString() {
        String name = name();
        return name.charAt(0) + name.substring(1).toLowerCase();
    }
}

/**
 * Task class - represents a single task
 * Contains task name and priority level
 */
class Task {
    private String name;
    private TaskPriority priority;
    
    /**
     * Constructor to create a task
     * @param name Task description
     * @param priority Task priority level
     */
    public Task(String name, TaskPriority priority) {
        this.name = name;
        this.priority = priority;
    }
    
    /**
     * Get task name
     * @return Task name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get task priority
     * @return Task priority
     */
    public TaskPriority getPriority() {
        return priority;
    }
    
    /**
     * Override toString for readable task display
     */
    @Override
    public String toString() {
        return "Task: " + name + " | Priority: " + priority;
    }
}

/**
 * TaskManager - Generic class to manage tasks
 * Uses fixed-size array to store tasks
 * Now includes ability to filter by priority
 * @param <T> Type of tasks to manage
 */
class TaskManager<T> {
    private Object[] tasks;  // Array to store tasks
    private int size;        // Current number of tasks
    
    /**
     * Constructor to initialize TaskManager with capacity
     * @param capacity Maximum number of tasks
     */
    public TaskManager(int capacity) {
        tasks = new Object[capacity];
        size = 0;
    }
    
    /**
     * Add a task to the manager
     * @param task Task to add
     */
    public void addTask(T task) {
        if (size < tasks.length) {
            tasks[size] = task;
            size++;
            System.out.println("Added: " + task);
        } else {
            System.out.println("Task manager is full. Cannot add more tasks.");
        }
    }
    
    /**
     * Display all tasks in the manager
     */
    @SuppressWarnings("unchecked")
    public void showAllTasks() {
        if (size == 0) {
            System.out.println("No tasks to display.");
            return;
        }
        
        for (int i = 0; i < size; i++) {
            T task = (T) tasks[i];
            System.out.println(task);
        }
    }
    
    /**
     * Display only tasks that match the specified priority
     * This is the NEW method added for PE07
     * @param priority The priority level to filter by
     */
    @SuppressWarnings("unchecked")
    public void showTasksByPriority(TaskPriority priority) {
        boolean found = false;
        
        for (int i = 0; i < size; i++) {
            T task = (T) tasks[i];
            
            // Check if the task is a Task object with matching priority
            if (task instanceof Task) {
                Task t = (Task) task;
                if (t.getPriority() == priority) {
                    System.out.println(t);
                    found = true;
                }
            }
        }
        
        if (!found) {
            System.out.println("No tasks found with priority: " + priority);
        }
    }
}