package algorithms;

import java.util.*;

import dataStructure.*;
import utils.Point3D;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{
	private static class DijkstraResult {
		private final double[] dist;
		private final node_data[] prev;

		public DijkstraResult(double[] dist, node_data[] prev) {
			this.dist = dist;
			this.prev = prev;
		}

		public double[] getDist() {
			return dist;
		}

		public node_data[] getPrev() {
			return prev;
		}
	}
	private graph graph;
	@Override
	public void init(graph g) {
		this.graph = g;
	}

	@Override
	public void init(String file_name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(String file_name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isConnected() {

		return false;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		double[] distances = dijkstra(src).getDist();
		return distances[this.graph.getNode(dest).getTag()];
	}

	private DijkstraResult dijkstra(int src) {
		double[] dist = new double[this.graph.nodeSize()];
		node_data[] prev = new node_data[dist.length];

		SortedSet<node_data> set = new TreeSet<>(new Comparator<node_data>() {
			@Override
			public int compare(node_data n1, node_data n2) {
				if(dist[n1.getTag()] < dist[n2.getTag()]) {
					return - 1;
				} else if(dist[n1.getTag()] > dist[n2.getTag()]) {
					return 1;
				} else {
					return 0;
				}
			}
		});

		int i =  0;
		for(node_data nodeData : this.graph.getV()) {
			nodeData.setTag(i);
			dist[i] = Double.MAX_VALUE;
			prev[i] = null;
			set.add(nodeData);
			i++;
		}

		dist[graph.getNode(src).getTag()] = 0;

		while(!set.isEmpty()) {
			node_data u = set.first();
			set.remove(u);

			for (edge_data edgeData : this.graph.getE(u.getKey())) {
				node_data v = this.graph.getNode(edgeData.getDest());
				double alt = dist[u.getTag()] + edgeData.getWeight();

				if (alt < dist[v.getTag()]) {
					dist[v.getTag()] = alt;
					prev[v.getTag()] = u;
				}
			}
		}

		return new DijkstraResult(dist, prev);
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		node_data[] prev = dijkstra(src).getPrev();

		if(prev[this.graph.getNode(dest).getTag()] == null) {
			return null;
		} else {
			List<node_data> path = new ArrayList<>();

			node_data curNode = this.graph.getNode(dest);

			while(curNode != this.graph.getNode(src)) {
				path.add(0, curNode);
				curNode = prev[curNode.getTag()];
			}

			path.add(0, curNode);

			return path;
		}
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		DGraph dGraph = new DGraph();

		for(node_data nodeData : this.graph.getV()) {
			Node node = new Node(nodeData.getKey(), new Point3D(nodeData.getLocation()), nodeData.getWeight(), nodeData.getInfo(),
					nodeData.getTag());
			dGraph.addNode(node);
			Collection<edge_data> edges = dGraph.getE(nodeData.getKey());

			for(edge_data edgeData : edges) {
				dGraph.connect(nodeData.getKey(), edgeData.getDest(), edgeData.getWeight());
			}
		}

		return dGraph;
	}
}
