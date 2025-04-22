/*
 * 
 * Initially all m∈M and w∈W are free
 * While there is a man m who is free and hasn’t proposed to every woman
 * Choose such a man m
 * Let w be the highest-ranked woman in m's preference list
 * to which m has not yet proposed
 * If w is free then
 * (m,w) become engaged
 * Else w is currently engaged to m′
 * If w prefers m′ to m then
 * m remains free
 * Else w prefers m to m′
 * (m,w) become engaged
 * m′ becomes free
 * Endif
 * Endif
 * Endwhile
 * Return the set S of engaged pairs
 * 
 */