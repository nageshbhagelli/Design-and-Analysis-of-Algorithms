/*
 * Suppose You are developing a task scheduler for a project management tool.
 * The project consists of several tasks, and each task may depend on other
 * tasks to be completed before it can start. The tasks and their dependencies
 * are represented as a directed acyclic graph (DAG). Your task is to write a
 * program that schedules the tasks in such a way that each task starts only
 * when all of its dependencies have been completed.
 */

// To compute a topological ordering of G:
// Find a node v with no incoming edges and order it first
// Delete v from G
// Recursively compute a topological ordering of G−{v} and append this order
// after v
