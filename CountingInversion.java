/*
 * Counting Inversions Algorithm:
 * // Purpose: To count the inversions for a given list L(a1,a2,…,an)
 * // Input: An unsorted list of distinct numbers L(a1,a2,…,an)
 * // Output: The number of inversions r for the list L(a1,a2,…,an)
 * Sort-and-Count(L)
 * If the list has one element then
 * there are no inversions
 * Else
 * Divide the list into two halves:
 * A contains the first ⌈n/2⌉ elements
 * B contains the remaining ⌊n/2⌋ elements
 * (r_A, A) = Sort-and-Count(A)
 * (r_B, B) = Sort-and-Count(B)
 * (r, L) = Merge-and-Count(A, B)
 * Endif
 * Return r = r_A + r_B + r, and the sorted list L
 * 
 */