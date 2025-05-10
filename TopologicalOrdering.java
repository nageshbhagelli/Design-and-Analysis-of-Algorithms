// To compute a topological ordering of G:
// Find a node v with no incoming edges and order it first
// Delete v from G
// Recursively compute a topological ordering of G−{v} and append this order
// after v
