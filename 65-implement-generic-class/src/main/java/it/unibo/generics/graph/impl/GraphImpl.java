package it.unibo.generics.graph.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N>{

    private final Map<N, Set<N>> edges = new LinkedHashMap<>();
    
    public GraphImpl(){
        
    }

    @Override
    public void addNode(N node) {
        edges.putIfAbsent(node, new HashSet<>());
    }

    @Override
    public void addEdge(N source, N target) {
        for (final N node : edges.get(source)) {
            if (!edges.containsKey(node)) {
                throw new IllegalArgumentException("No such node: " + node);
            }
        }
        edges.get(source).add(target);
    }

    @Override
    public Set<N> nodeSet() {
        return new HashSet<>(edges.keySet());
    }

    @Override
    public Set<N> linkedNodes(N node) {
        return edges.get(node);
    }

    @Override
    public List<N> getPath(N source, N target) { 
        List<N> reversePath = new LinkedList<>();
        List<N> path = new LinkedList<>();
        Map<N, N> predecessor = new HashMap<>();
        predecessor = bfs(source);
        if(source != target){
            for (N current = target; !current.equals(source); current = predecessor.get(current)){
            reversePath.add(current);
            }
            reversePath.add(source);
            int size = reversePath.size() - 1;
            for(int i = 0; i <= size; i++){
                path.add(i, reversePath.get(size-i));
            }
        }
        else{
            path = null;
        }
        return path;
    }

    public Map<N,N> bfs(N source){
        Deque<N> queue = new ArrayDeque<>();
        Map<N, Boolean> nodes = new HashMap<>();
        Map<N, N> predecessor = new HashMap<>();
        for(N node : edges.keySet()){
            nodes.put(node, false);
            predecessor.put(node, null);
        }
        queue.addFirst(source);
        N current;
        while (!queue.isEmpty()){
           current = queue.removeFirst();
           for(N node : edges.get(current)){
                if(!nodes.get(node)){
                    nodes.put(node, true);
                    predecessor.put(node, current);
                    queue.addFirst(node);
                }
           }
        }
        return predecessor;
    }
    
    
}
